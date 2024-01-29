package org.sonarsource.plugins.mybatis.regular.parser;

import org.junit.Assert;
import org.junit.Test;
import org.sonarsource.plugins.mybatis.regular.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.regular.pojo.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.regular.pojo.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.regular.util.FileUtil;
import org.sonarsource.plugins.mybatis.sql.pojo.RuleCheckResult;
import org.sonarsource.plugins.mybatis.xml.XmlBatisSqlParser;
import org.sonarsource.plugins.mybatis.xml.XmlParseResult;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlBatisSqlParserTest {
    @Test
    public void execute() {
        XmlBatisSqlParser xmlBatisSqlParser = new XmlBatisSqlParser();
        List<String> xmlFileList = new ArrayList<>();
        URL data = XmlBatisSqlParserTest.class.getClassLoader().getResource("mapper");
        FileUtil.getFileList(data.getPath(), ".xml", xmlFileList);
        List<XmlParseResult> results = xmlBatisSqlParser.parseXml(xmlFileList, "mysql");
        Assert.assertNotNull(results);
        Assert.assertEquals(7, results.size());
        for (XmlParseResult xmlParseResult : results) {
            if (xmlParseResult.getXmlNodeParserResult().getRuleCheckResults().size() > 0) {
                List<RuleCheckResult> sqlExpressCheckResults = xmlParseResult.getXmlNodeParserResult().getRuleCheckResults();
                for (RuleCheckResult temp : sqlExpressCheckResults) {
                    Assert.assertTrue(temp.getRuleId().startsWith("R000"));
                }
            }
        }
        // regular check
        XmlPluginRuleResultAll all = RegularRuleHandler.doRuleAll(results);
        Map<RuleCodeEnum, List<XmlPluginRuleResult>> map = all.getRuleMap();
        Assert.assertNotNull(map);
        Assert.assertEquals(2, map.size());
        Assert.assertNotNull(map.get(RuleCodeEnum.R1003));
        Assert.assertNotNull(map.get(RuleCodeEnum.R0003));
    }
}