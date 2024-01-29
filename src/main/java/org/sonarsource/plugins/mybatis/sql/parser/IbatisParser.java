package org.sonarsource.plugins.mybatis.sql.parser;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.regular.util.StringUtil;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.exception.DruidParseException;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;
import org.sonarsource.plugins.mybatis.xml.node.commom.CdataNode;
import org.sonarsource.plugins.mybatis.xml.node.commom.TextNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;
import org.sonarsource.plugins.mybatis.xml.util.DruidVisitorUtil;
import org.sonarsource.plugins.mybatis.xml.util.SqlFormatUtil;
import org.sonarsource.plugins.mybatis.xml.util.XmlUtil;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IbatisParser {
    private static final Logger log = LoggerFactory.getLogger(IbatisParser.class);
    private String nodeAsXml;
    private final XmlNode xmlNode;
    private String dbType;
    private final Map<String, Element> xmlIncludeNodeMap;
    private boolean containIfTest;
    private boolean isDynamicSql = false;
    private boolean isIncludeSql = false;
    private final Map<String, String> propertyCacheMap = new HashMap();

    public IbatisParser(XmlNode xmlNode, Map<String, Element> xmlIncludeNodeMap, String xmlFilePath, String dbType) {
        this.dbType = null;
        this.xmlNode = xmlNode;
        this.xmlIncludeNodeMap = xmlIncludeNodeMap;
        this.dbType = dbType;
        this.nodeAsXml = xmlNode.getNodeAsXml();
    }

    public XmlNodeParserResult parseIbatisXml() {
        String formatSql;
        String formatSql2;
        XmlNodeParserResult xmlNodeParserResult = new XmlNodeParserResult();
        xmlNodeParserResult.setNodeOptType(this.xmlNode.getNodeOptType());
        xmlNodeParserResult.setXmlType(this.xmlNode.getMapperType());
        xmlNodeParserResult.setXmlFilePath(this.xmlNode.getXmlFilePath());
        xmlNodeParserResult.setDbType(this.dbType);
        try {
            SAXReader saxReader = XmlUtil.createSAXReader();
            String rawNodeAsXml = getRawNodeAsXml();
            Document document = saxReader.read(new StringReader(rawNodeAsXml));
            Element rootElement = document.getRootElement();
            String nodeType = rootElement.getName();
            if (Constant.SELECT_INSERT_DELETE_UPDATE_LIST.contains(nodeType)) {
                String nodeIdName = rootElement.attributeValue("id");
                if (StringUtils.isEmpty(nodeIdName)) {
                    String errorMsg = "xml path:" + this.xmlNode.getXmlFilePath() + " 无法获取xml节点node的id";
                    log.error(errorMsg);
                    xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500003.getCode());
                    xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500003.getDesc() + ":" + errorMsg);
                    xmlNodeParserResult.setFormatSql(null);
                    return xmlNodeParserResult;
                }
                xmlNodeParserResult.setSqlNodeId(nodeIdName);
                List contentList = rootElement.content();
                List<INode> nodeParserResultList = parseIbatisXmlNode(contentList);
                StringBuilder parserSqlStringBuilder = new StringBuilder();
                for (INode nodeParserResult : nodeParserResultList) {
                    parserSqlStringBuilder.append(nodeParserResult.toString());
                }
                String formatSql3 = SqlFormatUtil.ibatisFormat(parserSqlStringBuilder.toString());
                if (formatSql3.toLowerCase().startsWith("insert")) {
                    formatSql = formatSql3.replaceAll("#", "'");
                } else {
                    formatSql = SqlFormatUtil.formatIbatisdruidSharp(formatSql3);
                }
                formatSql2 = formatSql.replaceAll("\\$", "#");
                xmlNodeParserResult.setSqlNodeId(nodeIdName);
                xmlNodeParserResult.setDynamicSql(this.isDynamicSql);
                xmlNodeParserResult.setIncludeSql(this.isIncludeSql);
                xmlNodeParserResult.setNodeAsXmlNoInclude(this.nodeAsXml);
                xmlNodeParserResult.setNameSpace(this.xmlNode.getNameSpace());
                xmlNodeParserResult.setXmlComment(this.xmlNode.getSqlComments());
                SchemaStatVisitor visitor = DruidVisitorUtil.getVisitor(formatSql2, this.dbType);
                Map<TableStat.Name, TableStat> tableStatMap = visitor.getTables();
                xmlNodeParserResult.setTableStatMap(tableStatMap);
                xmlNodeParserResult.setFormatSql(formatSql2);
                xmlNodeParserResult.setContainIfTest(this.containIfTest);
                xmlNodeParserResult.setStatusCode(ErrorCodeEnum.SUCCESS.getCode());
                xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.SUCCESS.getDesc());
                return xmlNodeParserResult;
            }
            String errorMsg2 = "xml path:" + this.xmlNode.getXmlFilePath() + "不支持" + nodeType;
            log.error(errorMsg2);
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500004.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500004.getDesc() + ":" + errorMsg2);
            return xmlNodeParserResult;
        } catch (DruidParseException ex) {
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500002.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500002.getDesc() + ":" + ("xml path:" + this.xmlNode.getXmlFilePath() + "中id:的sql语句无法被正确解析."));
            xmlNodeParserResult.setException(ex);
            xmlNodeParserResult.setFormatSql(null);
            return xmlNodeParserResult;
        } catch (Exception e) {
            log.error("error: ", e);
            DruidParseException exception = new DruidParseException(e);
            xmlNodeParserResult.setStatusCode(ErrorCodeEnum.E500001.getCode());
            xmlNodeParserResult.setErrorMsg(ErrorCodeEnum.E500001.getDesc() + ":" + e.getMessage());
            xmlNodeParserResult.setFormatSql(null);
            xmlNodeParserResult.setException(exception);
            return xmlNodeParserResult;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<INode> parseIbatisXmlNode(List contentList) throws Exception {
        INode element = null;
        List<INode> parserNodeResult = new ArrayList<>();
        for (Object content : contentList) {
            INode element2 = null;
            if (content instanceof DefaultText) {
                INode element3 = new TextNode();
                if (!StringUtils.isEmpty(((DefaultText) content).getStringValue())) {
                    ((TextNode) element3).setTextValue(((DefaultText) content).getStringValue());
                    element = element3;
                } else {
                    ((TextNode) element3).setTextValue(Constant.SPACE_CHAR);
                    element = element3;
                }
            } else if (content instanceof DefaultCDATA) {
                INode element4 = new CdataNode();
                ((CdataNode) element4).setStringValue(Constant.SPACE_CHAR + ((DefaultCDATA) content).getText().trim() + Constant.SPACE_CHAR);
                element = element4;
            } else if (content instanceof DefaultElement) {
                String nodeType = ((DefaultElement) content).getName();
                if (Constant.IBATIS_NODE_PACKAGE_MAP.containsKey(nodeType.toLowerCase())) {
                    String clazzName = Constant.IBATIS_NODE_PACKAGE_MAP.get(nodeType.toLowerCase());
                    if (!clazzName.equals("")) {
                        element2 = (INode) Class.forName(clazzName).newInstance();
                    }
                    if (((DefaultElement) content).attribute("property") != null) {
                        String property = ((DefaultElement) content).attribute("property").getValue().trim();
                        if (this.propertyCacheMap.containsKey(property)) {
                            element2.setConfilct(nodeType);
                        } else {
                            this.propertyCacheMap.put(property, nodeType);
                        }
                        element2.setProperty(((DefaultElement) content).attribute("property").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("prepend") != null) {
                        element2.setPrepend(((DefaultElement) content).attribute("prepend").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("compareProperty") != null) {
                        element2.setCompareProperty(((DefaultElement) content).attribute("compareProperty").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("compareValue") != null) {
                        element2.setCompareValue(((DefaultElement) content).attribute("compareValue").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("open") != null) {
                        element2.setOpen(((DefaultElement) content).attribute("open").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("close") != null) {
                        element2.setClose(((DefaultElement) content).attribute("close").getValue().trim());
                    }
                    if (((DefaultElement) content).attribute("conjunction") != null) {
                        element2.setConjunction(((DefaultElement) content).attribute("conjunction").getValue().trim());
                    }
                    if (nodeType.equalsIgnoreCase("ITERATE")) {
                        this.isDynamicSql = true;
                        List soncontents = ((DefaultElement) content).content();
                        List sonParseResult = parseIbatisXmlNode(soncontents);
                        element2.setSonParseResult(sonParseResult);
                        element = element2;
                    } else if (nodeType.equalsIgnoreCase("INCLUDE")) {
                        this.isIncludeSql = true;
                        String includeId = ((DefaultElement) content).attributeValue("refid");
                        if (!includeId.contains(".")) {
                            includeId = this.xmlNode.getNameSpace() + "." + includeId;
                        }
                        Element includeElement = this.xmlIncludeNodeMap.get(includeId);
                        if (includeElement != null) {
                            StringBuilder includeNodeXmlStringBuilder = new StringBuilder();
                            for (Object includeSonContent : includeElement.content()) {
                                if (includeSonContent instanceof DefaultText) {
                                    includeNodeXmlStringBuilder.append(((DefaultText) includeSonContent).asXML());
                                } else if (includeSonContent instanceof DefaultCDATA) {
                                    includeNodeXmlStringBuilder.append(((DefaultCDATA) includeSonContent).getText().trim() + Constant.SPACE_CHAR);
                                } else if (!(includeSonContent instanceof DefaultComment)) {
                                    includeNodeXmlStringBuilder.append(((Element) includeSonContent).asXML());
                                }
                            }
                            this.nodeAsXml = this.nodeAsXml.replace(((DefaultElement) content).asXML(), includeNodeXmlStringBuilder.toString());
                            List includeContents = includeElement.content();
                            List sonParseResult2 = parseIbatisXmlNode(includeContents);
                            element2.setSonParseResult(sonParseResult2);
                            element = element2;
                        } else {
                            log.error("<include refid='{}'>不存在该refid", includeId);
                            throw new Exception("id找不到对应的refid");
                        }
                    } else if (((DefaultElement) content).getName().equalsIgnoreCase("DYNAMIC")) {
                        this.isDynamicSql = true;
                        List soncontents2 = ((DefaultElement) content).content();
                        List sonParseResult3 = parseIbatisXmlNode(soncontents2);
                        element2.setSonParseResult(sonParseResult3);
                        element = element2;
                    } else if (!((DefaultElement) content).getName().equalsIgnoreCase("selectKey")) {
                        this.isDynamicSql = true;
                        List soncontents3 = ((DefaultElement) content).content();
                        List sonParseResult4 = parseIbatisXmlNode(soncontents3);
                        element2.setSonParseResult(sonParseResult4);
                        element = element2;
                    }
                } else {
                    throw new Exception(ErrorCodeEnum.E500006.toString());
                }
            } else {
                element = element2;
                if (!(content instanceof DefaultComment)) {
                    throw new Exception("解析xml元素类型失败");
                }
            }
            if (element != null) {
                parserNodeResult.add(element);
            }
        }
        return parserNodeResult;
    }

    public String getRawNodeAsXml() {
        String formatSql = StringUtil.delLineBreak(this.nodeAsXml);
        return StringUtil.mergeBlank(formatSql);
    }
}
