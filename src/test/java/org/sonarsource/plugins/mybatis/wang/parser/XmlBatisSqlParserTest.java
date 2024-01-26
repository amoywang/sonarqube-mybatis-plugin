package org.sonarsource.plugins.mybatis.wang.parser;

import org.junit.Test;
import org.sonarsource.plugins.mybatis.rules.ErrorDataFromLinter;
import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.wang.pojo.regular.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.wang.pojo.regular.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.wang.util.FileUtil;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlBatisSqlParserTest {
    @Test
    public void execute() {
        LogBean logBean = new LogBean("E:\\github_workspace\\sonar-mybatis-1.0.7\\src\\test");
        try {
            XmlBatisSqlParser xmlBatisSqlParser = new XmlBatisSqlParser();
            List<String> xmlFileList = new ArrayList<>();
            String xmlDirPath = "E:\\github_workspace\\sonar-mybatis-1.0.7\\src\\test\\resources\\mapper";
            FileUtil.getFileList(xmlDirPath, ".xml", xmlFileList);
            List results = xmlBatisSqlParser.parseXml(xmlFileList, logBean, "mysql");
            System.out.printf("解析完成,请查看: %s \n", logBean.getLogPath());
            XmlPluginRuleResultAll all = RegularRuleHandler.doRuleAll(results);
            Map<RuleCodeEnum, List<XmlPluginRuleResult>> map = all.getRuleMap();
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