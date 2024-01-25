package org.sonarsource.plugins.mybatis.wang.parser;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.sql.MybatisParse;
import org.sonarsource.plugins.mybatis.utils.IOUtils;
import org.sonarsource.plugins.mybatis.wang.pojo.BaseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.wang.util.LogUtil;
import org.sonarsource.plugins.mybatis.wang.util.ResultUtil;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNode;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;
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
                logger.error(e.getMessage());
            }
        }
        // 根据文件来组织结果
        Map<String, List<BaseResult>> analyseResults = new HashMap<>();
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
                        mybatisParse.parseMyBatisXml(xmlNodeParserResult);
                    }
                    if (null != xmlNodeParserResult.getFormatSql()) {
                        result.setXmlNodeParserResult(xmlNodeParserResult);
                        int lineNumber = IOUtils.getLineNumber(xmlFilePath2, sqlId);
                        result.setLineNumber(lineNumber);
                        resultList.add(result);
                    }else{
                        // 指出解析失败的SQL，可能有问题

                    }
                }
            } catch (Exception e2) {
                logger.error(e2.getMessage(), e2);
            }
        }
        return resultList;
    }
    public List<BaseResult> parse(List<String> xmlFileList, LogBean logBean, String dbType) {
        if (xmlFileList == null || xmlFileList.isEmpty()) {
            return null;
        }
        int sqlNodeSum = 0;
        List<BaseResult> resultList = new ArrayList<>();
        List<BaseResult> ideaPluginResultList = new ArrayList<>();
        Map<String, List<Element>> xmlSqlTagGlobalMap = new LinkedHashMap<>();
        for (String xmlFilePath : xmlFileList) {
            try {
                XmlUtil.parseXmlNodeSqlTag(xmlFilePath, xmlSqlTagGlobalMap);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        // 根据文件来组织结果
        Map<String, List<BaseResult>> analyseResults = new HashMap<>();
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
                        mybatisParse.parseMyBatisXml(xmlNodeParserResult);
                    }
                    if (null != xmlNodeParserResult.getFormatSql()) {
                        result.setXmlNodeParserResult(xmlNodeParserResult);
                        ideaPluginResultList.add(result);
                    }
                    int lineNumber = IOUtils.getLineNumber(xmlFilePath2, sqlId);
                    result.setLineNumber(lineNumber);
                    resultList.add(result);
                }
            } catch (Exception e2) {
                logger.error(e2.getMessage(), e2);
            }
        }

        if (null != logBean) {
            try {
                List<BaseResult> resultListCopy = new ArrayList<>(resultList);
                List<BaseResult> ideaPluginResultListCopy = new ArrayList<>(ideaPluginResultList);
                ResultUtil.calcAcc(resultListCopy, sqlNodeSum);
                ResultUtil.doLog(ideaPluginResultListCopy, logBean);
                XmlPluginRuleResultAll xmlPluginRuleResultAll = ResultUtil.doRuleAll(ideaPluginResultListCopy);
                LogUtil.logForRuleResult(xmlPluginRuleResultAll, logBean);
            } catch (Exception ex) {
                logger.error("error:", ex);
            }
        }
        return ideaPluginResultList;
    }
}
