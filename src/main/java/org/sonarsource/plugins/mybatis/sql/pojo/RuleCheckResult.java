package org.sonarsource.plugins.mybatis.sql.pojo;

public class RuleCheckResult {
    String obj;
    String ruleId;
    String message;

    @Override
    public String toString() {
        return "Result{" +
                "obj=[" + obj +
                "], rule='" + ruleId + '\'' +
                '}';
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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
}