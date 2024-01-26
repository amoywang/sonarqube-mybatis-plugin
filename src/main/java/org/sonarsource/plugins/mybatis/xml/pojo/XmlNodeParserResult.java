package org.sonarsource.plugins.mybatis.xml.pojo;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import org.sonarsource.plugins.mybatis.sql.RuleCheckResult;
import org.sonarsource.plugins.mybatis.xml.exception.base.BaseException;

import java.util.List;
import java.util.Map;

public class XmlNodeParserResult {
    private String sqlNodeId;
    private String sqlNodeIdOrg;
    private String nodeOptType;
    private boolean containIfTest;
    private boolean dynamicSql;
    private boolean includeSql;
    private String nodeAsXmlNoInclude;
    private String nameSpace;
    private String xmlComment;
    private String formatSql;
    private String druidFormatSql;
    private SchemaStatVisitor visitor;
    private String xmlType;
    private String xmlFilePath;
    private String statusCode;
    private String errorMsg;
    private BaseException exception;
    Map<TableStat.Name, TableStat> tableStatMap;
    private String dbType;
    private boolean hasDuplicated;
    private boolean hasDuplicatedSqlTagId;
    private boolean existSubSqlTagId = true;
    private boolean existSubSqlTagIdDuplicated;
    private List<RuleCheckResult> ruleCheckResults;

    public void setRuleCheckResults(List<RuleCheckResult> ruleCheckResults) {
        this.ruleCheckResults = ruleCheckResults;
    }

    public List<RuleCheckResult> getRuleCheckResults() {
        return ruleCheckResults;
    }

    public void setSqlNodeId(String sqlNodeId) {
        this.sqlNodeId = sqlNodeId;
    }

    public void setSqlNodeIdOrg(String sqlNodeIdOrg) {
        this.sqlNodeIdOrg = sqlNodeIdOrg;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public void setContainIfTest(boolean containIfTest) {
        this.containIfTest = containIfTest;
    }

    public void setDynamicSql(boolean dynamicSql) {
        this.dynamicSql = dynamicSql;
    }

    public void setIncludeSql(boolean includeSql) {
        this.includeSql = includeSql;
    }

    public void setNodeAsXmlNoInclude(String nodeAsXmlNoInclude) {
        this.nodeAsXmlNoInclude = nodeAsXmlNoInclude;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setXmlComment(String xmlComment) {
        this.xmlComment = xmlComment;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    public void setDruidFormatSql(String druidFormatSql) {
        this.druidFormatSql = druidFormatSql;
    }

    public void setVisitor(SchemaStatVisitor visitor) {
        this.visitor = visitor;
    }

    public void setXmlType(String xmlType) {
        this.xmlType = xmlType;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setException(BaseException exception) {
        this.exception = exception;
    }

    public void setTableStatMap(Map<TableStat.Name, TableStat> tableStatMap) {
        this.tableStatMap = tableStatMap;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setHasDuplicated(boolean hasDuplicated) {
        this.hasDuplicated = hasDuplicated;
    }

    public void setHasDuplicatedSqlTagId(boolean hasDuplicatedSqlTagId) {
        this.hasDuplicatedSqlTagId = hasDuplicatedSqlTagId;
    }

    public void setExistSubSqlTagId(boolean existSubSqlTagId) {
        this.existSubSqlTagId = existSubSqlTagId;
    }

    public void setExistSubSqlTagIdDuplicated(boolean existSubSqlTagIdDuplicated) {
        this.existSubSqlTagIdDuplicated = existSubSqlTagIdDuplicated;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlNodeParserResult) {
            XmlNodeParserResult other = (XmlNodeParserResult) o;
            if (other.canEqual(this)) {
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
                Object this$nodeOptType = getNodeOptType();
                Object other$nodeOptType = other.getNodeOptType();
                if (this$nodeOptType == null) {
                    if (other$nodeOptType != null) {
                        return false;
                    }
                } else if (!this$nodeOptType.equals(other$nodeOptType)) {
                    return false;
                }
                if (isContainIfTest() == other.isContainIfTest() && isDynamicSql() == other.isDynamicSql() && isIncludeSql() == other.isIncludeSql()) {
                    Object this$nodeAsXmlNoInclude = getNodeAsXmlNoInclude();
                    Object other$nodeAsXmlNoInclude = other.getNodeAsXmlNoInclude();
                    if (this$nodeAsXmlNoInclude == null) {
                        if (other$nodeAsXmlNoInclude != null) {
                            return false;
                        }
                    } else if (!this$nodeAsXmlNoInclude.equals(other$nodeAsXmlNoInclude)) {
                        return false;
                    }
                    Object this$nameSpace = getNameSpace();
                    Object other$nameSpace = other.getNameSpace();
                    if (this$nameSpace == null) {
                        if (other$nameSpace != null) {
                            return false;
                        }
                    } else if (!this$nameSpace.equals(other$nameSpace)) {
                        return false;
                    }
                    Object this$xmlComment = getXmlComment();
                    Object other$xmlComment = other.getXmlComment();
                    if (this$xmlComment == null) {
                        if (other$xmlComment != null) {
                            return false;
                        }
                    } else if (!this$xmlComment.equals(other$xmlComment)) {
                        return false;
                    }
                    Object this$formatSql = getFormatSql();
                    Object other$formatSql = other.getFormatSql();
                    if (this$formatSql == null) {
                        if (other$formatSql != null) {
                            return false;
                        }
                    } else if (!this$formatSql.equals(other$formatSql)) {
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
                    Object this$visitor = getVisitor();
                    Object other$visitor = other.getVisitor();
                    if (this$visitor == null) {
                        if (other$visitor != null) {
                            return false;
                        }
                    } else if (!this$visitor.equals(other$visitor)) {
                        return false;
                    }
                    Object this$xmlType = getXmlType();
                    Object other$xmlType = other.getXmlType();
                    if (this$xmlType == null) {
                        if (other$xmlType != null) {
                            return false;
                        }
                    } else if (!this$xmlType.equals(other$xmlType)) {
                        return false;
                    }
                    Object this$xmlFilePath = getXmlFilePath();
                    Object other$xmlFilePath = other.getXmlFilePath();
                    if (this$xmlFilePath == null) {
                        if (other$xmlFilePath != null) {
                            return false;
                        }
                    } else if (!this$xmlFilePath.equals(other$xmlFilePath)) {
                        return false;
                    }
                    Object this$statusCode = getStatusCode();
                    Object other$statusCode = other.getStatusCode();
                    if (this$statusCode == null) {
                        if (other$statusCode != null) {
                            return false;
                        }
                    } else if (!this$statusCode.equals(other$statusCode)) {
                        return false;
                    }
                    Object this$errorMsg = getErrorMsg();
                    Object other$errorMsg = other.getErrorMsg();
                    if (this$errorMsg == null) {
                        if (other$errorMsg != null) {
                            return false;
                        }
                    } else if (!this$errorMsg.equals(other$errorMsg)) {
                        return false;
                    }
                    Object this$exception = getException();
                    Object other$exception = other.getException();
                    if (this$exception == null) {
                        if (other$exception != null) {
                            return false;
                        }
                    } else if (!this$exception.equals(other$exception)) {
                        return false;
                    }
                    Object this$tableStatMap = getTableStatMap();
                    Object other$tableStatMap = other.getTableStatMap();
                    if (this$tableStatMap == null) {
                        if (other$tableStatMap != null) {
                            return false;
                        }
                    } else if (!this$tableStatMap.equals(other$tableStatMap)) {
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
                    return isHasDuplicated() == other.isHasDuplicated() && isHasDuplicatedSqlTagId() == other.isHasDuplicatedSqlTagId() && isExistSubSqlTagId() == other.isExistSubSqlTagId() && isExistSubSqlTagIdDuplicated() == other.isExistSubSqlTagIdDuplicated();
                }
                return false;
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof XmlNodeParserResult;
    }

    public int hashCode() {
        Object $sqlNodeId = getSqlNodeId();
        int result = (59) + ($sqlNodeId == null ? 43 : $sqlNodeId.hashCode());
        Object $sqlNodeIdOrg = getSqlNodeIdOrg();
        int result2 = (result * 59) + ($sqlNodeIdOrg == null ? 43 : $sqlNodeIdOrg.hashCode());
        Object $nodeOptType = getNodeOptType();
        int result3 = (((((((result2 * 59) + ($nodeOptType == null ? 43 : $nodeOptType.hashCode())) * 59) + (isContainIfTest() ? 79 : 97)) * 59) + (isDynamicSql() ? 79 : 97)) * 59) + (isIncludeSql() ? 79 : 97);
        Object $nodeAsXmlNoInclude = getNodeAsXmlNoInclude();
        int result4 = (result3 * 59) + ($nodeAsXmlNoInclude == null ? 43 : $nodeAsXmlNoInclude.hashCode());
        Object $nameSpace = getNameSpace();
        int result5 = (result4 * 59) + ($nameSpace == null ? 43 : $nameSpace.hashCode());
        Object $xmlComment = getXmlComment();
        int result6 = (result5 * 59) + ($xmlComment == null ? 43 : $xmlComment.hashCode());
        Object $formatSql = getFormatSql();
        int result7 = (result6 * 59) + ($formatSql == null ? 43 : $formatSql.hashCode());
        Object $druidFormatSql = getDruidFormatSql();
        int result8 = (result7 * 59) + ($druidFormatSql == null ? 43 : $druidFormatSql.hashCode());
        Object $visitor = getVisitor();
        int result9 = (result8 * 59) + ($visitor == null ? 43 : $visitor.hashCode());
        Object $xmlType = getXmlType();
        int result10 = (result9 * 59) + ($xmlType == null ? 43 : $xmlType.hashCode());
        Object $xmlFilePath = getXmlFilePath();
        int result11 = (result10 * 59) + ($xmlFilePath == null ? 43 : $xmlFilePath.hashCode());
        Object $statusCode = getStatusCode();
        int result12 = (result11 * 59) + ($statusCode == null ? 43 : $statusCode.hashCode());
        Object $errorMsg = getErrorMsg();
        int result13 = (result12 * 59) + ($errorMsg == null ? 43 : $errorMsg.hashCode());
        Object $exception = getException();
        int result14 = (result13 * 59) + ($exception == null ? 43 : $exception.hashCode());
        Object $tableStatMap = getTableStatMap();
        int result15 = (result14 * 59) + ($tableStatMap == null ? 43 : $tableStatMap.hashCode());
        Object $dbType = getDbType();
        return (((((((((result15 * 59) + ($dbType == null ? 43 : $dbType.hashCode())) * 59) + (isHasDuplicated() ? 79 : 97)) * 59) + (isHasDuplicatedSqlTagId() ? 79 : 97)) * 59) + (isExistSubSqlTagId() ? 79 : 97)) * 59) + (isExistSubSqlTagIdDuplicated() ? 79 : 97);
    }

    public String toString() {
        return "XmlNodeParserResult(sqlNodeId=" + getSqlNodeId() + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", nodeOptType=" + getNodeOptType() + ", containIfTest=" + isContainIfTest() + ", dynamicSql=" + isDynamicSql() + ", includeSql=" + isIncludeSql() + ", nodeAsXmlNoInclude=" + getNodeAsXmlNoInclude() + ", nameSpace=" + getNameSpace() + ", xmlComment=" + getXmlComment() + ", formatSql=" + getFormatSql() + ", druidFormatSql=" + getDruidFormatSql() + ", visitor=" + getVisitor() + ", xmlType=" + getXmlType() + ", xmlFilePath=" + getXmlFilePath() + ", statusCode=" + getStatusCode() + ", errorMsg=" + getErrorMsg() + ", exception=" + getException() + ", tableStatMap=" + getTableStatMap() + ", dbType=" + getDbType() + ", hasDuplicated=" + isHasDuplicated() + ", hasDuplicatedSqlTagId=" + isHasDuplicatedSqlTagId() + ", existSubSqlTagId=" + isExistSubSqlTagId() + ", existSubSqlTagIdDuplicated=" + isExistSubSqlTagIdDuplicated() + ")";
    }

    public String getSqlNodeId() {
        return this.sqlNodeId;
    }

    public String getSqlNodeIdOrg() {
        return this.sqlNodeIdOrg;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public boolean isContainIfTest() {
        return this.containIfTest;
    }

    public boolean isDynamicSql() {
        return this.dynamicSql;
    }

    public boolean isIncludeSql() {
        return this.includeSql;
    }

    public String getNodeAsXmlNoInclude() {
        return this.nodeAsXmlNoInclude;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    public String getXmlComment() {
        return this.xmlComment;
    }

    public String getFormatSql() {
        return this.formatSql;
    }

    public String getDruidFormatSql() {
        return this.druidFormatSql;
    }

    public SchemaStatVisitor getVisitor() {
        return this.visitor;
    }

    public String getXmlType() {
        return this.xmlType;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public BaseException getException() {
        return this.exception;
    }

    public Map<TableStat.Name, TableStat> getTableStatMap() {
        return this.tableStatMap;
    }

    public String getDbType() {
        return this.dbType;
    }

    public boolean isHasDuplicated() {
        return this.hasDuplicated;
    }

    public boolean isHasDuplicatedSqlTagId() {
        return this.hasDuplicatedSqlTagId;
    }

    public boolean isExistSubSqlTagId() {
        return this.existSubSqlTagId;
    }

    public boolean isExistSubSqlTagIdDuplicated() {
        return this.existSubSqlTagIdDuplicated;
    }
}
