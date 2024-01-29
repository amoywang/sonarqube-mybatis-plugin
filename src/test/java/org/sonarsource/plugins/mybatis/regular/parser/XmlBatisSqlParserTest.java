package org.sonarsource.plugins.mybatis.regular.parser;

import org.junit.Test;
import org.sonarsource.plugins.mybatis.rules.ErrorDataFromLinter;
import org.sonarsource.plugins.mybatis.regular.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.regular.pojo.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.regular.pojo.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.regular.util.FileUtil;
import org.sonarsource.plugins.mybatis.xml.XmlBatisSqlParser;
import org.sonarsource.plugins.mybatis.xml.XmlParseResult;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlBatisSqlParserTest {
    @Test
    public void execute() {
        try {
            XmlBatisSqlParser xmlBatisSqlParser = new XmlBatisSqlParser();
            List<String> xmlFileList = new ArrayList<>();
            URL data = XmlBatisSqlParserTest.class.getClassLoader().getResource("mapper");
            FileUtil.getFileList(data.getPath(), ".xml", xmlFileList);
            List<XmlParseResult> results = xmlBatisSqlParser.parseXml(xmlFileList, "mysql");
            System.out.printf("SQL表达式解析完成,请查看:  \n");
            for(XmlParseResult result:results){
                List temp = result.getXmlNodeParserResult().getRuleCheckResults();
                if (!temp.isEmpty()){
                    temp.forEach(item->{
//                        System.out.println(item.toString());
                    });
                }
            }
            XmlPluginRuleResultAll all = RegularRuleHandler.doRuleAll(results);
            Map<RuleCodeEnum, List<XmlPluginRuleResult>> map = all.getRuleMap();
            System.out.printf("正则表达式解析完成:\n");
            if (map != null && map.size() > 0) {
                for (Map.Entry<RuleCodeEnum, List<XmlPluginRuleResult>> entry : map.entrySet()) {
                    RuleCodeEnum ruleInfo = entry.getKey();
                    for (XmlPluginRuleResult result : entry.getValue()) {
                        ErrorDataFromLinter errorData = new ErrorDataFromLinter(ruleInfo.getName(), ruleInfo.getDesc(),
                                result.getFilePath(), result.getLineNumber());
                        System.out.println(errorData);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);

    }
}