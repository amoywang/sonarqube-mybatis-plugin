package org.sonarsource.plugins.mybatis.regular.pojo;

import org.sonarsource.plugins.mybatis.regular.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.xml.util.IOUtils;

import java.util.Objects;

public class XmlPluginRuleResult {
    private String mapperName;
    private String sqlNodeId;
    private String sqlNodeIdOrg;
    private String sqlText;
    private String nodeOptType;
    private String druidFormatSql;
    private String parseResult;
    private RuleCodeEnum ruleCodeEnum;
    private String dbType;
    private String filePath;
    private Integer lineNumber;

    public void setLineNumber(Integer line) {
        this.lineNumber = line;
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public static class XmlPluginRuleResultBuilder {
        private String mapperName;
        private String sqlNodeId;
        private String sqlNodeIdOrg;
        private String sqlText;
        private String nodeOptType;
        private String druidFormatSql;
        private String parseResult;
        private RuleCodeEnum ruleCodeEnum;
        private String dbType;
        private String filePath;
        private Integer lineNumber;

        public void setLineNumber(Integer line) {
            this.lineNumber = line;
        }

        public Integer getLineNumber() {
            return this.lineNumber;
        }

        XmlPluginRuleResultBuilder() {
        }

        public XmlPluginRuleResultBuilder mapperName(String mapperName) {
            this.mapperName = mapperName;
            return this;
        }

        public XmlPluginRuleResultBuilder sqlNodeId(String sqlNodeId) {
            this.sqlNodeId = sqlNodeId;
            return this;
        }

        public XmlPluginRuleResultBuilder sqlNodeIdOrg(String sqlNodeIdOrg) {
            this.sqlNodeIdOrg = sqlNodeIdOrg;
            return this;
        }

        public XmlPluginRuleResultBuilder sqlText(String sqlText) {
            this.sqlText = sqlText;
            return this;
        }

        public XmlPluginRuleResultBuilder nodeOptType(String nodeOptType) {
            this.nodeOptType = nodeOptType;
            return this;
        }

        public XmlPluginRuleResultBuilder druidFormatSql(String druidFormatSql) {
            this.druidFormatSql = druidFormatSql;
            return this;
        }

        public XmlPluginRuleResultBuilder parseResult(String parseResult) {
            this.parseResult = parseResult;
            return this;
        }

        public XmlPluginRuleResultBuilder ruleCodeEnum(RuleCodeEnum ruleCodeEnum) {
            this.ruleCodeEnum = ruleCodeEnum;
            return this;
        }

        public XmlPluginRuleResultBuilder dbType(String dbType) {
            this.dbType = dbType;
            return this;
        }

        public XmlPluginRuleResultBuilder filePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public XmlPluginRuleResultBuilder lineNumber(String filePath) {
            this.lineNumber = IOUtils.getLineNumber(filePath, this.sqlNodeId);
            return this;
        }

        public XmlPluginRuleResult build() {
            return new XmlPluginRuleResult(this.mapperName, this.sqlNodeId, this.sqlNodeIdOrg,
                    this.sqlText, this.nodeOptType, this.druidFormatSql, this.parseResult,
                    this.ruleCodeEnum, this.dbType, this.filePath, this.lineNumber);
        }

        public String toString() {
            return "XmlPluginRuleResult.XmlPluginRuleResultBuilder(mapperName=" + this.mapperName + ", sqlNodeId=" + this.sqlNodeId
                    + ", sqlNodeIdOrg=" + this.sqlNodeIdOrg + ", sqlText=" + this.sqlText
                    + ", nodeOptType=" + this.nodeOptType + ", druidFormatSql=" + this.druidFormatSql
                    + ", parseResult=" + this.parseResult + ", ruleCodeEnum=" + this.ruleCodeEnum
                    + ", dbType=" + this.dbType + ", filePath=" + this.filePath + ")";
        }
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public void setSqlNodeId(String sqlNodeId) {
        this.sqlNodeId = sqlNodeId;
    }

    public void setSqlNodeIdOrg(String sqlNodeIdOrg) {
        this.sqlNodeIdOrg = sqlNodeIdOrg;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public void setDruidFormatSql(String druidFormatSql) {
        this.druidFormatSql = druidFormatSql;
    }

    public void setParseResult(String parseResult) {
        this.parseResult = parseResult;
    }

    public void setRuleCodeEnum(RuleCodeEnum ruleCodeEnum) {
        this.ruleCodeEnum = ruleCodeEnum;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlPluginRuleResult) {
            XmlPluginRuleResult other = (XmlPluginRuleResult) o;
            if (other.canEqual(this)) {
                Object this$mapperName = getMapperName();
                Object other$mapperName = other.getMapperName();
                if (this$mapperName == null) {
                    if (other$mapperName != null) {
                        return false;
                    }
                } else if (!this$mapperName.equals(other$mapperName)) {
                    return false;
                }
                Object this$sqlNodeId = getSqlNodeId();
                Object other$sqlNodeId = other.getSqlNodeId();
                if (this$sqlNodeId == null) {
                    if (other$sqlNodeId != null) {
                        return false;
                    }
                } else if (!this$sqlNodeId.equals(other$sqlNodeId)) {
                    return false;
                }
                Object this$sqlNodeIdOrg = getSqlNodeIdOrg();
                Object other$sqlNodeIdOrg = other.getSqlNodeIdOrg();
                if (this$sqlNodeIdOrg == null) {
                    if (other$sqlNodeIdOrg != null) {
                        return false;
                    }
                } else if (!this$sqlNodeIdOrg.equals(other$sqlNodeIdOrg)) {
                    return false;
                }
                Object this$sqlText = getSqlText();
                Object other$sqlText = other.getSqlText();
                if (this$sqlText == null) {
                    if (other$sqlText != null) {
                        return false;
                    }
                } else if (!this$sqlText.equals(other$sqlText)) {
                    return false;
                }
                Object this$nodeOptType = getNodeOptType();
                Object other$nodeOptType = other.getNodeOptType();
                if (this$nodeOptType == null) {
                    if (other$nodeOptType != null) {
                        return false;
                    }
                } else if (!this$nodeOptType.equals(other$nodeOptType)) {
                    return false;
                }
                Object this$druidFormatSql = getDruidFormatSql();
                Object other$druidFormatSql = other.getDruidFormatSql();
                if (this$druidFormatSql == null) {
                    if (other$druidFormatSql != null) {
                        return false;
                    }
                } else if (!this$druidFormatSql.equals(other$druidFormatSql)) {
                    return false;
                }
                Object this$parseResult = getParseResult();
                Object other$parseResult = other.getParseResult();
                if (this$parseResult == null) {
                    if (other$parseResult != null) {
                        return false;
                    }
                } else if (!this$parseResult.equals(other$parseResult)) {
                    return false;
                }
                Object this$ruleCodeEnum = getRuleCodeEnum();
                Object other$ruleCodeEnum = other.getRuleCodeEnum();
                if (this$ruleCodeEnum == null) {
                    if (other$ruleCodeEnum != null) {
                        return false;
                    }
                } else if (!this$ruleCodeEnum.equals(other$ruleCodeEnum)) {
                    return false;
                }
                Object this$dbType = getDbType();
                Object other$dbType = other.getDbType();
                if (this$dbType == null) {
                    if (other$dbType != null) {
                        return false;
                    }
                } else if (!this$dbType.equals(other$dbType)) {
                    return false;
                }
                Object this$filePath = getFilePath();
                Object other$filePath = other.getFilePath();
                return Objects.equals(this$filePath, other$filePath);
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof XmlPluginRuleResult;
    }

    public int hashCode() {
        Object $mapperName = getMapperName();
        int result = (59) + ($mapperName == null ? 43 : $mapperName.hashCode());
        Object $sqlNodeId = getSqlNodeId();
        int result2 = (result * 59) + ($sqlNodeId == null ? 43 : $sqlNodeId.hashCode());
        Object $sqlNodeIdOrg = getSqlNodeIdOrg();
        int result3 = (result2 * 59) + ($sqlNodeIdOrg == null ? 43 : $sqlNodeIdOrg.hashCode());
        Object $sqlText = getSqlText();
        int result4 = (result3 * 59) + ($sqlText == null ? 43 : $sqlText.hashCode());
        Object $nodeOptType = getNodeOptType();
        int result5 = (result4 * 59) + ($nodeOptType == null ? 43 : $nodeOptType.hashCode());
        Object $druidFormatSql = getDruidFormatSql();
        int result6 = (result5 * 59) + ($druidFormatSql == null ? 43 : $druidFormatSql.hashCode());
        Object $parseResult = getParseResult();
        int result7 = (result6 * 59) + ($parseResult == null ? 43 : $parseResult.hashCode());
        Object $ruleCodeEnum = getRuleCodeEnum();
        int result8 = (result7 * 59) + ($ruleCodeEnum == null ? 43 : $ruleCodeEnum.hashCode());
        Object $dbType = getDbType();
        int result9 = (result8 * 59) + ($dbType == null ? 43 : $dbType.hashCode());
        Object $filePath = getFilePath();
        return (result9 * 59) + ($filePath == null ? 43 : $filePath.hashCode());
    }

    public String toString() {
        return "XmlPluginRuleResult(mapperName=" + getMapperName() + ", sqlNodeId=" + getSqlNodeId()
                + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", sqlText=" + getSqlText() + ", nodeOptType="
                + getNodeOptType() + ", druidFormatSql=" + getDruidFormatSql() + ", parseResult=" + getParseResult()
                + ", ruleCodeEnum=" + getRuleCodeEnum() + ", dbType=" + getDbType() + ", filePath=" + getFilePath() + ")";
    }

    XmlPluginRuleResult(String mapperName, String sqlNodeId, String sqlNodeIdOrg, String sqlText,
                        String nodeOptType, String druidFormatSql, String parseResult,
                        RuleCodeEnum ruleCodeEnum, String dbType, String filePath, Integer lineNumber) {
        this.mapperName = mapperName;
        this.sqlNodeId = sqlNodeId;
        this.sqlNodeIdOrg = sqlNodeIdOrg;
        this.sqlText = sqlText;
        this.nodeOptType = nodeOptType;
        this.druidFormatSql = druidFormatSql;
        this.parseResult = parseResult;
        this.ruleCodeEnum = ruleCodeEnum;
        this.dbType = dbType;
        this.filePath = filePath;
        this.lineNumber = lineNumber;
    }

    public static XmlPluginRuleResultBuilder builder() {
        return new XmlPluginRuleResultBuilder();
    }

    public String getMapperName() {
        return this.mapperName;
    }

    public String getSqlNodeId() {
        return this.sqlNodeId;
    }

    public String getSqlNodeIdOrg() {
        return this.sqlNodeIdOrg;
    }

    public String getSqlText() {
        return this.sqlText;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public String getDruidFormatSql() {
        return this.druidFormatSql;
    }

    public String getParseResult() {
        return this.parseResult;
    }

    public RuleCodeEnum getRuleCodeEnum() {
        return this.ruleCodeEnum;
    }

    public String getDbType() {
        return this.dbType;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getSqlStr() {
        if (null == this.druidFormatSql || this.druidFormatSql.isEmpty()) {
            return this.sqlText;
        }
        return this.druidFormatSql;
    }
}
