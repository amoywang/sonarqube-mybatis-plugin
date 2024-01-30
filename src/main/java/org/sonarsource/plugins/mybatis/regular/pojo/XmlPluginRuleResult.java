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

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public void setLineNumber(Integer line) {
        this.lineNumber = line;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlPluginRuleResult) {
            XmlPluginRuleResult other = (XmlPluginRuleResult) o;
            if (other.canEqual(this)) {
                Object thismapperName = getMapperName();
                Object othermapperName = other.getMapperName();
                if (thismapperName == null) {
                    if (othermapperName != null) {
                        return false;
                    }
                } else if (!thismapperName.equals(othermapperName)) {
                    return false;
                }
                Object thissqlNodeId = getSqlNodeId();
                Object othersqlNodeId = other.getSqlNodeId();
                if (thissqlNodeId == null) {
                    if (othersqlNodeId != null) {
                        return false;
                    }
                } else if (!thissqlNodeId.equals(othersqlNodeId)) {
                    return false;
                }
                Object thissqlNodeIdOrg = getSqlNodeIdOrg();
                Object othersqlNodeIdOrg = other.getSqlNodeIdOrg();
                if (thissqlNodeIdOrg == null) {
                    if (othersqlNodeIdOrg != null) {
                        return false;
                    }
                } else if (!thissqlNodeIdOrg.equals(othersqlNodeIdOrg)) {
                    return false;
                }
                Object thissqlText = getSqlText();
                Object othersqlText = other.getSqlText();
                if (thissqlText == null) {
                    if (othersqlText != null) {
                        return false;
                    }
                } else if (!thissqlText.equals(othersqlText)) {
                    return false;
                }
                Object thisnodeOptType = getNodeOptType();
                Object othernodeOptType = other.getNodeOptType();
                if (thisnodeOptType == null) {
                    if (othernodeOptType != null) {
                        return false;
                    }
                } else if (!thisnodeOptType.equals(othernodeOptType)) {
                    return false;
                }
                Object thisdruidFormatSql = getDruidFormatSql();
                Object otherdruidFormatSql = other.getDruidFormatSql();
                if (thisdruidFormatSql == null) {
                    if (otherdruidFormatSql != null) {
                        return false;
                    }
                } else if (!thisdruidFormatSql.equals(otherdruidFormatSql)) {
                    return false;
                }
                Object thisparseResult = getParseResult();
                Object otherparseResult = other.getParseResult();
                if (thisparseResult == null) {
                    if (otherparseResult != null) {
                        return false;
                    }
                } else if (!thisparseResult.equals(otherparseResult)) {
                    return false;
                }
                Object thisruleCodeEnum = getRuleCodeEnum();
                Object otherruleCodeEnum = other.getRuleCodeEnum();
                if (thisruleCodeEnum == null) {
                    if (otherruleCodeEnum != null) {
                        return false;
                    }
                } else if (!thisruleCodeEnum.equals(otherruleCodeEnum)) {
                    return false;
                }
                Object thisdbType = getDbType();
                Object otherdbType = other.getDbType();
                if (thisdbType == null) {
                    if (otherdbType != null) {
                        return false;
                    }
                } else if (!thisdbType.equals(otherdbType)) {
                    return false;
                }
                Object thisfilePath = getFilePath();
                Object otherfilePath = other.getFilePath();
                return Objects.equals(thisfilePath, otherfilePath);
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof XmlPluginRuleResult;
    }

    public int hashCode() {
        Object mapperName = getMapperName();
        int result = (59) + (mapperName == null ? 43 : mapperName.hashCode());
        Object sqlNodeId = getSqlNodeId();
        int result2 = (result * 59) + (sqlNodeId == null ? 43 : sqlNodeId.hashCode());
        Object sqlNodeIdOrg = getSqlNodeIdOrg();
        int result3 = (result2 * 59) + (sqlNodeIdOrg == null ? 43 : sqlNodeIdOrg.hashCode());
        Object sqlText = getSqlText();
        int result4 = (result3 * 59) + (sqlText == null ? 43 : sqlText.hashCode());
        Object nodeOptType = getNodeOptType();
        int result5 = (result4 * 59) + (nodeOptType == null ? 43 : nodeOptType.hashCode());
        Object druidFormatSql = getDruidFormatSql();
        int result6 = (result5 * 59) + (druidFormatSql == null ? 43 : druidFormatSql.hashCode());
        Object parseResult = getParseResult();
        int result7 = (result6 * 59) + (parseResult == null ? 43 : parseResult.hashCode());
        Object ruleCodeEnum = getRuleCodeEnum();
        int result8 = (result7 * 59) + (ruleCodeEnum == null ? 43 : ruleCodeEnum.hashCode());
        Object dbType = getDbType();
        int result9 = (result8 * 59) + (dbType == null ? 43 : dbType.hashCode());
        Object filePath = getFilePath();
        return (result9 * 59) + (filePath == null ? 43 : filePath.hashCode());
    }

    public String toString() {
        return "XmlPluginRuleResult(mapperName=" + getMapperName() + ", sqlNodeId=" + getSqlNodeId()
                + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", sqlText=" + getSqlText() + ", nodeOptType="
                + getNodeOptType() + ", druidFormatSql=" + getDruidFormatSql() + ", parseResult=" + getParseResult()
                + ", ruleCodeEnum=" + getRuleCodeEnum() + ", dbType=" + getDbType() + ", filePath=" + getFilePath() + ")";
    }

    public String getMapperName() {
        return this.mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getSqlNodeId() {
        return this.sqlNodeId;
    }

    public void setSqlNodeId(String sqlNodeId) {
        this.sqlNodeId = sqlNodeId;
    }

    public String getSqlNodeIdOrg() {
        return this.sqlNodeIdOrg;
    }

    public void setSqlNodeIdOrg(String sqlNodeIdOrg) {
        this.sqlNodeIdOrg = sqlNodeIdOrg;
    }

    public String getSqlText() {
        return this.sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public String getDruidFormatSql() {
        return this.druidFormatSql;
    }

    public void setDruidFormatSql(String druidFormatSql) {
        this.druidFormatSql = druidFormatSql;
    }

    public String getParseResult() {
        return this.parseResult;
    }

    public void setParseResult(String parseResult) {
        this.parseResult = parseResult;
    }

    public RuleCodeEnum getRuleCodeEnum() {
        return this.ruleCodeEnum;
    }

    public void setRuleCodeEnum(RuleCodeEnum ruleCodeEnum) {
        this.ruleCodeEnum = ruleCodeEnum;
    }

    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSqlStr() {
        if (null == this.druidFormatSql || this.druidFormatSql.isEmpty()) {
            return this.sqlText;
        }
        return this.druidFormatSql;
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

        XmlPluginRuleResultBuilder() {
        }

        public Integer getLineNumber() {
            return this.lineNumber;
        }

        public void setLineNumber(Integer line) {
            this.lineNumber = line;
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
}
