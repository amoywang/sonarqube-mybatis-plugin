package org.sonarsource.plugins.mybatis.wang.parser;

import org.junit.Test;
import org.sonarsource.plugins.mybatis.wang.util.FileUtil;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;


import java.util.ArrayList;
import java.util.List;

public class XmlBatisSqlParserTest {
    @Test
    public void execute() {
        LogBean logBean = new LogBean("E:\\github_workspace\\sonar-mybatis-1.0.7\\src\\test");
        try {
            XmlBatisSqlParser xmlBatisSqlParser = new XmlBatisSqlParser();
            List<String> xmlFileList = new ArrayList<>();
            String xmlDirPath = "E:\\github_workspace\\sonar-mybatis-1.0.7\\src\\test\\resources\\mapper";
            FileUtil.getFileList(xmlDirPath, ".xml", xmlFileList);
            xmlBatisSqlParser.parse(xmlFileList, logBean, "mysql");
            System.out.printf("解析完成,请查看: %s \n", logBean.getLogPath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);

    }
}