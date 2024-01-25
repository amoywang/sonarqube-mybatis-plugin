package org.sonarsource.plugins.mybatis.sql.rules;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import org.sonar.api.rule.Severity;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import static org.sonarsource.plugins.mybatis.Constant.MYBATIS_MATTER_CHECK_RULE_PREFIX;

public class NoUseAlwaysEqualRule extends AbstractRule {
    @Override
    public boolean visit(SQLBinaryOpExpr x) {
        SQLExpr left = x.getLeft();
        SQLExpr right = x.getRight();
        if (left.equals(right)) {
            this.addCheckResult(x);
        }
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
        return "不要再where条件子句中使用恒等式条件，(eg. select 1 from dual where 2=2)";
    }
    @Override
    public String getSimpleDescription(){
        return "Statement should not include always equal in condition";
    }
}
