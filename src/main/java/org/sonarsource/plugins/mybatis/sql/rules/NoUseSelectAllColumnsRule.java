package org.sonarsource.plugins.mybatis.sql.rules;

import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import org.sonar.api.rule.Severity;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import static org.sonarsource.plugins.mybatis.sql.Constant.MYBATIS_MATTER_CHECK_RULE_PREFIX;

public class NoUseSelectAllColumnsRule extends AbstractRule {
    @Override
    public boolean visit(SQLAllColumnExpr x) {
        this.addCheckResult(x);
        return super.visit(x);
    }

    @Override
    public String getRuleID() {
        return MYBATIS_MATTER_CHECK_RULE_PREFIX + this.getClass().getSimpleName();
    }

    @Override
    public String getSeverity() {
        return Severity.MAJOR;
    }

    @Override
    public String getName() {
        return "禁止select星号";
    }

    @Override
    public String getDescription() {
        return "禁止使用select * 来查询所有字段，使用明确的column，若后续表结构表更，则对应的Column可能不存在，会造成潜在错误";
    }

    @Override
    public String getSimpleDescription() {
        return "禁止SELECT星号(select *)";
    }
}