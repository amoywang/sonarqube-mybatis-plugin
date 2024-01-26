package org.sonarsource.plugins.mybatis.sql;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;

import java.util.List;

public abstract class AbstractRule extends SQLASTVisitorAdapter {
    List<RuleCheckResult> results;

    public final void initCheckResults(List<RuleCheckResult> results) {
        this.results = results;
    }

//    protected abstract SQLObject getObject();

    protected final boolean addCheckResult(SQLObject object) {
        RuleCheckResult result = new RuleCheckResult();
        result.setRuleId(getRuleID());
        result.setObj(getSimpleDescription() + ",invalid sqlObject:[" + object.toString() + "]");
        result.setRuleId(getRuleID());
        return results.add(result);
    }

    public final List<RuleCheckResult> getCheckResults() {
        return results;
    }

    public abstract String getRuleID();

    public abstract String getSeverity();

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSimpleDescription();
}