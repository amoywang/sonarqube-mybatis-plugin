package org.sonarsource.plugins.mybatis.sql.rules;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLCaseStatement;
import org.sonar.api.rule.Severity;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import static org.sonarsource.plugins.mybatis.Constant.MYBATIS_MATTER_CHECK_RULE_PREFIX;

public class MybatisXmlSQLValidRule extends AbstractRule {
    @Override
    public boolean visit(SQLCaseStatement x) {
        // do nothing
        return super.visit(x);
    }
    @Override
    public String getRuleID() {
        return MYBATIS_MATTER_CHECK_RULE_PREFIX+this.getClass().getSimpleName();
    }

    @Override
    public String getSeverity() {
        return Severity.MAJOR;
    }

    @Override
    public String getName() {
        return "MyBatis XML SQL Check"+this.getClass().getSimpleName();
    }

    @Override
    public String getDescription() {
        return "Mybatis XML SQL解析异常，请检查此SQL是否正常";
    }
    @Override
    public String getSimpleDescription(){
        return "Mybatis XML SQL解析异常，请检查此SQL是否正常";
    }
}
