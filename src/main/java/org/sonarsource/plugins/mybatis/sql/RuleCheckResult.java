package org.sonarsource.plugins.mybatis.sql;

import com.alibaba.druid.sql.ast.SQLObject;

public class RuleCheckResult {


    @Override
    public String toString() {
        return "Result{" +
                "obj=[" + obj +
                "], rule='" + ruleId + '\'' +
                '}';
    }

    String obj;
    String ruleId;
    String message;

    public String getRuleId() {
        return ruleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}