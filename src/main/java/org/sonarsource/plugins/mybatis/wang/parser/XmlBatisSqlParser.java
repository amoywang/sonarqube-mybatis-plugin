package org.sonarsource.plugins.mybatis.wang.parser;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.sql.MybatisParse;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.util.IOUtils;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;
import org.sonarsource.plugins.mybatis.xml.util.DruidVisitorUtil;
import org.sonarsource.plugins.mybatis.xml.util.XmlUtil;

import java.io.File;
import java.util.*;

public class XmlBatisSqlParser {
    private static final Logger logger = LoggerFactory.getLogger(XmlBatisSqlParser.class);

    public List<XmlParseResult> parseXml(List<String> xmlFileList, LogBean logBean, String dbType) {
        if (xmlFileList == null || xmlFileList.isEmpty()) {
            return null;
        }
        int sqlNodeSum = 0;
        List<XmlParseResult> resultList = new ArrayList<>();
        Map<String, List<Element>> xmlSqlTagGlobalMap = new LinkedHashMap<>();
        for (String xmlFilePath : xmlFileList) {
            try {
                XmlUtil.parseXmlNodeSqlTag(xmlFilePath, xmlSqlTagGlobalMap);
            } catch (Exception e) {
                logger.error("Ignore File:" + xmlFilePath + "," + e.getMessage());
            }
        }
        for (String xmlFilePath2 : xmlFileList) {
            String mapperName = new File(xmlFilePath2).getName();
            Map<String, List<XmlNode>> xmlNodeMap = new HashMap<>();
            try {
                sqlNodeSum += XmlUtil.parseXmlNode(xmlFilePath2, xmlNodeMap, xmlSqlTagGlobalMap);
                for (String sqlId : xmlNodeMap.keySet()) {
                    XmlParseResult result = new XmlParseResult();
                    result.setMapperName(mapperName);
                    result.setMapperFilePath(xmlFilePath2);
                    List<XmlNode> xmlNodeList = xmlNodeMap.get(sqlId);
                    XmlNodeParserResult xmlNodeParserResult = new XmlNodeParserResult();
                    xmlNodeParserResult.setDbType(dbType);
                    if (xmlNodeList.get(0).getMapperType().equals(Constant.MAPPER)) {
                        MybatisParse mybatisParse = new MybatisParse(sqlId, xmlNodeList, xmlSqlTagGlobalMap, dbType);
                        try {
                            mybatisParse.parseMyBatisXml(xmlNodeParserResult);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        SchemaStatVisitor visitor = null;
                        if (null == xmlNodeParserResult.getException() && xmlNodeParserResult.getFormatSql() != null) {
                            try {
                                visitor = DruidVisitorUtil.getSqlParserVisitorSingle(xmlNodeParserResult.getFormatSql(), "mysql");
                            }catch (Exception e){
//                                e.printStackTrace();
                            }
                        } else {
                            // todo 指出解析失败的SQL，可能有问题

                        }
                        xmlNodeParserResult.setVisitor(visitor);
                        result.setXmlNodeParserResult(xmlNodeParserResult);
                        int lineNumber = IOUtils.getLineNumber(xmlFilePath2, sqlId);
                        result.setLineNumber(lineNumber);
                        resultList.add(result);
                    }

                }
            } catch (Exception e2) {
                logger.error(e2.getMessage(), e2);
                e2.printStackTrace();
            }
        }
        return resultList;
    }
}
