package org.sonarsource.plugins.mybatis.sql.parser;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.regular.util.StringUtil;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;
import org.sonarsource.plugins.mybatis.sql.pojo.RuleCheckResult;
import org.sonarsource.plugins.mybatis.sql.util.ParseUtil;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.exception.DruidParseException;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;
import org.sonarsource.plugins.mybatis.xml.node.commom.CdataNode;
import org.sonarsource.plugins.mybatis.xml.node.commom.TextNode;
import org.sonarsource.plugins.mybatis.xml.node.mybatis.ZIfNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;
import org.sonarsource.plugins.mybatis.xml.util.SqlFormatUtil;
import org.sonarsource.plugins.mybatis.xml.util.XmlUtil;

import java.io.StringReader;
import java.util.*;

/* sql/MybatisParse.class */
public class MybatisParser {
    private static final Logger log = LoggerFactory.getLogger(MybatisParser.class);
    private final String sqlNodeId;
    private final List<XmlNode> xmlNodeList;
    private final Map<String, List<Element>> xmlSqlTagGlobalMap;
    private String nodeAsXml;
    private boolean existSubSqlTagIdDuplicated;
    private String dbType;
    private boolean containIfTest;
    private boolean existSubSqlTagId = true;
    private boolean isDynamicSql = false;
    private boolean isIncludeSql = false;

    public MybatisParser(String sqlNodeId, List<XmlNode> xmlNodeList, Map<String, List<Element>> xmlSqlTagGlobalMap, String targetDbType) {
        this.dbType = null;
        this.sqlNodeId = sqlNodeId;
        this.xmlNodeList = xmlNodeList;
        this.xmlSqlTagGlobalMap = xmlSqlTagGlobalMap;
        this.dbType = targetDbType;
        this.nodeAsXml = this.xmlNodeList.get(0).getNodeAsXml();
    }

    public void parseMyBatisXml(XmlNodeParserResult xmlNodeParserResult) {
        if (null == xmlNodeParserResult) {
            xmlNodeParserResult = new XmlNodeParserResult();
        }
        if (this.xmlNodeList.isEmpty()) {
            throw new RuntimeException("xml节点信息不能为空");
        }
        XmlNode xmlNode = this.xmlNodeList.get(0);
        xmlNodeParserResult.setSqlNodeId(xmlNode.getSqlNodeId());
        xmlNodeParserResult.setSqlNodeIdOrg(xmlNode.getSqlNodeIdOrg());
        xmlNodeParserResult.setNodeOptType(xmlNode.getNodeOptType());
        xmlNodeParserResult.setXmlType(xmlNode.getMapperType());
        xmlNodeParserResult.setXmlFilePath(xmlNode.getXmlFilePath());
        xmlNodeParserResult.setRuleCheckResults(new ArrayList<>());
        if (xmlNode.isHasDuplicatedSqlTagId()) {
            xmlNodeParserResult.setHasDuplicatedSqlTagId(true);
            xmlNodeParserResult.setFormatSql("重复<sql id>:" + xmlNode.getSqlNodeIdOrg());
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500007.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500007.getDesc());
            log.info("重复的<sql id>:" + xmlNode.getSqlNodeId());
        } else if (this.xmlNodeList.size() > 1) {
            xmlNodeParserResult.setHasDuplicated(true);
            xmlNodeParserResult.setFormatSql("重复id");
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500008.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500008.getDesc());
            if (this.xmlNodeList.size() >= 2) {
                for (int i = 1; i < this.xmlNodeList.size(); i++) {
                    log.error("忽略 重复id:" + xmlNode.getSqlNodeId() + "," + xmlNode.getXmlFilePath());
                }
            }
        } else {
            String nodeIdName = xmlNode.getSqlNodeId();
            try {
                SAXReader saxReader = XmlUtil.createSAXReader();
                String rawNodeAsXml = getRawNodeAsXml();
                Document document = saxReader.read(new StringReader(rawNodeAsXml));
                Element rootElement = document.getRootElement();
                String nodeOptType = rootElement.getName();
                boolean isValid = isValidNode(nodeOptType, nodeIdName, xmlNodeParserResult);
                if (!isValid) {
                    return;
                }
                List<Node> xmlNodeMetaList = rootElement.content();
                List<INode> nodeParserResultList = parseMybatisXmlNode(xmlNodeMetaList);
                if (!this.existSubSqlTagId) {
                    xmlNodeParserResult.setFormatSql("sql节点中refid的id不存在,无法格式化SQL");
                    xmlNodeParserResult.setExistSubSqlTagId(this.existSubSqlTagId);
                    xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500009.getCode());
                    xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500009.getDesc());
                } else if (this.existSubSqlTagIdDuplicated) {
                    xmlNodeParserResult.setExistSubSqlTagIdDuplicated(this.existSubSqlTagIdDuplicated);
                    xmlNodeParserResult.setFormatSql("sql节点中refid的id存在重复,无法格式化SQL");
                    xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500010.getCode());
                    xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500010.getDesc());
                } else {
                    StringBuilder sqlCombine = new StringBuilder();
                    ParseUtil.dealWithSequenceSetIf(nodeParserResultList);
                    ParseUtil.dealWithSequenceChoose(nodeParserResultList);
                    for (int i2 = 0; i2 < nodeParserResultList.size(); i2++) {
                        INode curNode = nodeParserResultList.get(i2);
                        String printSql = curNode.toString();
                        sqlCombine.append(printSql);
                    }
                    String formatSql = StringUtil.delLineBreak(SqlFormatUtil.mybatisFormat(sqlCombine.toString()));
                    // SQL 正则表达式检测
                    List<RuleCheckResult> results = new ArrayList<>();
                    List<SQLStatement> stmtList = SQLUtils.parseStatements(formatSql, this.dbType);
                    //USE JAVA SPI TO GET RULE DEFINE IN META-INF/services
                    ServiceLoader<AbstractRule> rules = ServiceLoader.load(AbstractRule.class, AbstractRule.class.getClassLoader());
                    // SQL 表达式检测
                    for (SQLStatement statement : stmtList) {
                        //DO CHECK
                        for (AbstractRule rule : rules) {
                            rule.initCheckResults(results);
                            statement.accept(rule);
                        }
                    }
                    xmlNodeParserResult.setFormatSql(formatSql);
                    xmlNodeParserResult.setStatusCode(ErrorCodeEnum.SUCCESS.getCode());
                    xmlNodeParserResult.setErrorMsg("");
                    xmlNodeParserResult.setRuleCheckResults(results);
                }
            } catch (DruidParseException ex) {
                xmlNodeParserResult.setFormatSql(null);
                xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500002.getCode());
                xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500002.getDesc());
                xmlNodeParserResult.setException(ex);
            } catch (Exception ex2) {
                String errorMsg = "Parse SQL :[" + xmlNodeParserResult.getXmlFilePath() + "],Sql Ref Id=" + xmlNodeParserResult.getSqlNodeId() + "error: " + ex2.getMessage();
                log.error(errorMsg);
                xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500001.getCode());
                xmlNodeParserResult.setErrorMsg(ex2.getMessage());
                xmlNodeParserResult.setException(new DruidParseException(ex2));
                xmlNodeParserResult.setFormatSql(null);
            }
        }
    }

    private List<INode> parseMybatisXmlNode(List<Node> xmlNodeMetaList) throws Exception {
        if (this.existSubSqlTagIdDuplicated || !this.existSubSqlTagId) {
            return null;
        }
        List<INode> iNodeList = new ArrayList<>();
        Iterator<Node> it = xmlNodeMetaList.iterator();
        while (it.hasNext()) {
            Node defaultText = it.next();
            INode nodeImpl = null;
            if (defaultText instanceof DefaultText) {
                nodeImpl = new TextNode();
                String str = defaultText.getStringValue();
                if (!StringUtils.isEmpty(str)) {
                    ((TextNode) nodeImpl).setTextValue(str);
                } else {
                    ((TextNode) nodeImpl).setTextValue(Constant.SPACE_CHAR);
                }
            } else if (defaultText instanceof DefaultCDATA) {
                nodeImpl = new CdataNode();
                String cdataText = defaultText.getText().trim();
                ((CdataNode) nodeImpl).setStringValue(Constant.SPACE_CHAR + cdataText + Constant.SPACE_CHAR);
            } else if (!(defaultText instanceof DefaultComment)) {
                if (defaultText instanceof DefaultElement) {
                    DefaultElement defaultElement = (DefaultElement) defaultText;
                    String nodeOptType = defaultElement.getName();
                    if (!Constant.MYBATIS_NODE_PACKAGE_MAP.containsKey(nodeOptType.toLowerCase())) {
                        log.error("{} 节点标签无对应的处理类", nodeOptType);
                        throw new Exception(ErrorCodeEnum.E500006.toString());
                    }
                    String className = Constant.MYBATIS_NODE_PACKAGE_MAP.get(nodeOptType.toLowerCase());
                    if (!className.isEmpty()) {
                        nodeImpl = (INode) Class.forName(className).newInstance();
                    }
                    if (!this.containIfTest && (nodeImpl instanceof ZIfNode)) {
                        this.containIfTest = true;
                    }
                    if (defaultElement.attribute("prefix") != null) {
                        nodeImpl.setPrefix(defaultElement.attribute("prefix").getValue().trim());
                    }
                    if (defaultElement.attribute("test") != null) {
                        nodeImpl.setTest(defaultElement.attribute("test").getValue().trim());
                    }
                    if (defaultElement.attribute("suffix") != null) {
                        nodeImpl.setSuffix(defaultElement.attribute("suffix").getValue().trim());
                    }
                    if (defaultElement.attribute("prefixOverrides") != null) {
                        nodeImpl.setPrefixOverrides(defaultElement.attribute("prefixOverrides").getValue().trim());
                    }
                    if (defaultElement.attribute("suffixOverrides") != null) {
                        nodeImpl.setSuffixOverrides(defaultElement.attribute("suffixOverrides").getValue().trim());
                    }
                    if (defaultElement.attribute("item") != null) {
                        nodeImpl.setItem(defaultElement.attribute("item").getValue().trim());
                    }
                    if (defaultElement.attribute("index") != null) {
                        nodeImpl.setIndex(defaultElement.attribute("index").getValue().trim());
                    }
                    if (defaultElement.attribute("collection") != null) {
                        nodeImpl.setCollection(defaultElement.attribute("collection").getValue().trim());
                    }
                    if (defaultElement.attribute("open") != null) {
                        nodeImpl.setOpen(defaultElement.attribute("open").getValue().trim());
                    }
                    if (defaultElement.attribute("close") != null) {
                        nodeImpl.setClose(defaultElement.attribute("close").getValue().trim());
                    }
                    if (defaultElement.attribute("separator") != null) {
                        nodeImpl.setSaparator(defaultElement.attribute("separator").getValue().trim());
                    }
                    if ("include".equalsIgnoreCase(nodeOptType)) {
                        this.isIncludeSql = true;
                        String includeId = defaultElement.attributeValue("refid");
                        if (!includeId.contains(".")) {
                            includeId = this.xmlNodeList.get(0).getNameSpace() + "." + includeId;
                        }
                        List<Element> elementList = this.xmlSqlTagGlobalMap.get(includeId);
                        if (null == elementList || elementList.isEmpty()) {
                            this.existSubSqlTagId = false;
                            return null;
                        } else if (elementList.size() > 1) {
                            this.existSubSqlTagIdDuplicated = true;
                            return null;
                        } else {
                            Element includeElement = this.xmlSqlTagGlobalMap.get(includeId).get(0);
                            if (includeElement != null) {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (Object includeSonContent : includeElement.content()) {
                                    if (includeSonContent instanceof DefaultText) {
                                        stringBuilder.append(((DefaultText) includeSonContent).asXML());
                                    } else if (includeSonContent instanceof DefaultCDATA) {
                                        stringBuilder.append(((DefaultCDATA) includeSonContent).getText().trim() + Constant.SPACE_CHAR);
                                    } else if (!(includeSonContent instanceof DefaultComment)) {
                                        stringBuilder.append(((Element) includeSonContent).asXML());
                                    }
                                }
                                this.nodeAsXml = this.nodeAsXml.replace(defaultElement.asXML(), stringBuilder.toString());
                                List includeContents = includeElement.content();
                                List<INode> sonParseResult = parseMybatisXmlNode(includeContents);
                                nodeImpl.setSonParseResult(sonParseResult);
                            } else {
                                log.error("<include refid='{}'>不存在,请核对refid", includeId);
                                throw new Exception("id找不到对应的refid");
                            }
                        }
                    } else if (!nodeOptType.equalsIgnoreCase("BIND") && !nodeOptType.equalsIgnoreCase("SELECTKEY")) {
                        this.isDynamicSql = true;
                        List soncontents = defaultElement.content();
                        List<INode> sonParseResult2 = parseMybatisXmlNode(soncontents);
                        nodeImpl.setSonParseResult(sonParseResult2);
                    }
                } else {
                    throw new Exception("无法识别xml标签:" + defaultText.getName());
                }
            }
            if (nodeImpl != null) {
                iNodeList.add(nodeImpl);
            }
        }
        return iNodeList;
    }

    public String getRawNodeAsXml() {
        String formatXmlText = StringUtil.delLineBreak(this.nodeAsXml);
        return StringUtil.mergeBlank(formatXmlText);
    }

    private boolean isValidNode(String nodeOptType, String nodeIdName, XmlNodeParserResult xmlNodeParserResult) {
        if (!Constant.SELECT_INSERT_DELETE_UPDATE_LIST.contains(nodeOptType)) {
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500004.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500004.getDesc());
            return false;
        } else if (null == nodeIdName || nodeIdName.isEmpty()) {
            String errorMsg = "忽略 空id, 无法获取xml节点node的id:" + (null == nodeIdName ? "null值" : "空值");
            log.error(errorMsg);
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500003.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500003.getDesc() + ":" + errorMsg);
            return false;
        } else {
            return true;
        }
    }
}
