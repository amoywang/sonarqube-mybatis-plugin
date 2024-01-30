package org.sonarsource.plugins.mybatis.sql.rules;

import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectOrderByItem;
import org.sonar.api.rule.Severity;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import static org.sonarsource.plugins.mybatis.sql.Constant.MYBATIS_MATTER_CHECK_RULE_PREFIX;

public class NoUseDollarOrderByRule extends AbstractRule {
    @Override
    public boolean visit(SQLSelectOrderByItem x) {
        if (x.getExpr() instanceof SQLVariantRefExpr) {
            SQLVariantRefExpr temp = (SQLVariantRefExpr) x.getExpr();
            String name = temp.getName() + "";
            if (name.contains("$")) {
                this.addCheckResult(temp);
            }
        }

        return super.visit(x);
    }

    @Override
    public String getRuleID() {
        return MYBATIS_MATTER_CHECK_RULE_PREFIX + this.getClass().getSimpleName();
    }

    @Override
    public String getSeverity() {
        return Severity.BLOCKER;
    }

    @Override
    public String getName() {
        return "Order By 参数必须使用#{}，不允许使用${}";
    }

    @Override
    public String getDescription() {
        return "禁止使用${param}方式进行参数预占，会造成SQL注入攻击";
    }

    @Override
    public String getSimpleDescription() {
        return "Order By语句参数必须使用#{}，不允许使用${}";
    }
}
