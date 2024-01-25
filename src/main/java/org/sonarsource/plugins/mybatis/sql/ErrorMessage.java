package org.sonarsource.plugins.mybatis.sql;

public class ErrorMessage {
    private String ruleId;
    private String errorMessage;
    public ErrorMessage() {
    }

    public ErrorMessage(String ruleId, String errorMessage) {
        this.ruleId = ruleId;
        this.errorMessage = errorMessage;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
