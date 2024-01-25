package org.sonarsource.plugins.mybatis.sql;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class AbstractRuleTest {
    @Test
    public void doCheck() {
        //USE DRUID SQL AST TO PARSER SQL
        String sql = "select * from dual " +
                "where  param in ($(params)) ";
//                " and 1= 1 " +
//                " and  '12' = '12' " +
//                " and id=${dollarParam} " +
//                " and name like '%${likeParam}%' " +
//                "  " +
//                " order by ${orderByParam} ";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, "mysql");
        //USE JAVA SPI TO GET RULE DEFINE IN META-INF/services
        ServiceLoader<AbstractRule> rules = ServiceLoader.load(AbstractRule.class, AbstractRule.class.getClassLoader());
        //RESULT
        List<RuleCheckResult> results = new ArrayList<>();
        for (SQLStatement statement : stmtList) {
            //DO CHECK
            for (AbstractRule rule : rules) {
                rule.initCheckResults(results);
                statement.accept(rule);
            }
        }
        for (RuleCheckResult r : results) {
            System.out.println(r);
        }
    }

}