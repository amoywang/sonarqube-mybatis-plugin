package org.sonarsource.plugins.mybatis.xml.pojo;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import org.sonarsource.plugins.mybatis.sql.pojo.RuleCheckResult;
import org.sonarsource.plugins.mybatis.xml.exception.base.BaseException;

import java.util.List;
import java.util.Map;

public class XmlNodeParserResult {
    Map<TableStat.Name, TableStat> tableStatMap;
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
    private String dbType;
    private boolean hasDuplicated;
    private boolean hasDuplicatedSqlTagId;
    private boolean existSubSqlTagId = true;
    private boolean existSubSqlTagIdDuplicated;
    private List<RuleCheckResult> ruleCheckResults;

    public List<RuleCheckResult> getRuleCheckResults() {
        return ruleCheckResults;
    }

    public void setRuleCheckResults(List<RuleCheckResult> ruleCheckResults) {
        this.ruleCheckResults = ruleCheckResults;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlNodeParserResult) {
            XmlNodeParserResult other = (XmlNodeParserResult) o;
            if (other.canEqual(this)) {
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
                Object thisnodeOptType = getNodeOptType();
                Object othernodeOptType = other.getNodeOptType();
                if (thisnodeOptType == null) {
                    if (othernodeOptType != null) {
                        return false;
                    }
                } else if (!thisnodeOptType.equals(othernodeOptType)) {
                    return false;
                }
                if (isContainIfTest() == other.isContainIfTest() && isDynamicSql() == other.isDynamicSql() && isIncludeSql() == other.isIncludeSql()) {
                    Object thisnodeAsXmlNoInclude = getNodeAsXmlNoInclude();
                    Object othernodeAsXmlNoInclude = other.getNodeAsXmlNoInclude();
                    if (thisnodeAsXmlNoInclude == null) {
                        if (othernodeAsXmlNoInclude != null) {
                            return false;
                        }
                    } else if (!thisnodeAsXmlNoInclude.equals(othernodeAsXmlNoInclude)) {
                        return false;
                    }
                    Object thisnameSpace = getNameSpace();
                    Object othernameSpace = other.getNameSpace();
                    if (thisnameSpace == null) {
                        if (othernameSpace != null) {
                            return false;
                        }
                    } else if (!thisnameSpace.equals(othernameSpace)) {
                        return false;
                    }
                    Object thisxmlComment = getXmlComment();
                    Object otherxmlComment = other.getXmlComment();
                    if (thisxmlComment == null) {
                        if (otherxmlComment != null) {
                            return false;
                        }
                    } else if (!thisxmlComment.equals(otherxmlComment)) {
                        return false;
                    }
                    Object thisformatSql = getFormatSql();
                    Object otherformatSql = other.getFormatSql();
                    if (thisformatSql == null) {
                        if (otherformatSql != null) {
                            return false;
                        }
                    } else if (!thisformatSql.equals(otherformatSql)) {
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
                    Object thisvisitor = getVisitor();
                    Object othervisitor = other.getVisitor();
                    if (thisvisitor == null) {
                        if (othervisitor != null) {
                            return false;
                        }
                    } else if (!thisvisitor.equals(othervisitor)) {
                        return false;
                    }
                    Object thisxmlType = getXmlType();
                    Object otherxmlType = other.getXmlType();
                    if (thisxmlType == null) {
                        if (otherxmlType != null) {
                            return false;
                        }
                    } else if (!thisxmlType.equals(otherxmlType)) {
                        return false;
                    }
                    Object thisxmlFilePath = getXmlFilePath();
                    Object otherxmlFilePath = other.getXmlFilePath();
                    if (thisxmlFilePath == null) {
                        if (otherxmlFilePath != null) {
                            return false;
                        }
                    } else if (!thisxmlFilePath.equals(otherxmlFilePath)) {
                        return false;
                    }
                    Object thisstatusCode = getStatusCode();
                    Object otherstatusCode = other.getStatusCode();
                    if (thisstatusCode == null) {
                        if (otherstatusCode != null) {
                            return false;
                        }
                    } else if (!thisstatusCode.equals(otherstatusCode)) {
                        return false;
                    }
                    Object thiserrorMsg = getErrorMsg();
                    Object othererrorMsg = other.getErrorMsg();
                    if (thiserrorMsg == null) {
                        if (othererrorMsg != null) {
                            return false;
                        }
                    } else if (!thiserrorMsg.equals(othererrorMsg)) {
                        return false;
                    }
                    Object thisexception = getException();
                    Object otherexception = other.getException();
                    if (thisexception == null) {
                        if (otherexception != null) {
                            return false;
                        }
                    } else if (!thisexception.equals(otherexception)) {
                        return false;
                    }
                    Object thistableStatMap = getTableStatMap();
                    Object othertableStatMap = other.getTableStatMap();
                    if (thistableStatMap == null) {
                        if (othertableStatMap != null) {
                            return false;
                        }
                    } else if (!thistableStatMap.equals(othertableStatMap)) {
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
        Object sqlNodeId = getSqlNodeId();
        int result = (59) + (sqlNodeId == null ? 43 : sqlNodeId.hashCode());
        Object sqlNodeIdOrg = getSqlNodeIdOrg();
        int result2 = (result * 59) + (sqlNodeIdOrg == null ? 43 : sqlNodeIdOrg.hashCode());
        Object nodeOptType = getNodeOptType();
        int result3 = (((((((result2 * 59) + (nodeOptType == null ? 43 : nodeOptType.hashCode())) * 59) + (isContainIfTest() ? 79 : 97)) * 59) + (isDynamicSql() ? 79 : 97)) * 59) + (isIncludeSql() ? 79 : 97);
        Object nodeAsXmlNoInclude = getNodeAsXmlNoInclude();
        int result4 = (result3 * 59) + (nodeAsXmlNoInclude == null ? 43 : nodeAsXmlNoInclude.hashCode());
        Object nameSpace = getNameSpace();
        int result5 = (result4 * 59) + (nameSpace == null ? 43 : nameSpace.hashCode());
        Object xmlComment = getXmlComment();
        int result6 = (result5 * 59) + (xmlComment == null ? 43 : xmlComment.hashCode());
        Object formatSql = getFormatSql();
        int result7 = (result6 * 59) + (formatSql == null ? 43 : formatSql.hashCode());
        Object druidFormatSql = getDruidFormatSql();
        int result8 = (result7 * 59) + (druidFormatSql == null ? 43 : druidFormatSql.hashCode());
        Object visitor = getVisitor();
        int result9 = (result8 * 59) + (visitor == null ? 43 : visitor.hashCode());
        Object xmlType = getXmlType();
        int result10 = (result9 * 59) + (xmlType == null ? 43 : xmlType.hashCode());
        Object xmlFilePath = getXmlFilePath();
        int result11 = (result10 * 59) + (xmlFilePath == null ? 43 : xmlFilePath.hashCode());
        Object statusCode = getStatusCode();
        int result12 = (result11 * 59) + (statusCode == null ? 43 : statusCode.hashCode());
        Object errorMsg = getErrorMsg();
        int result13 = (result12 * 59) + (errorMsg == null ? 43 : errorMsg.hashCode());
        Object exception = getException();
        int result14 = (result13 * 59) + (exception == null ? 43 : exception.hashCode());
        Object tableStatMap = getTableStatMap();
        int result15 = (result14 * 59) + (tableStatMap == null ? 43 : tableStatMap.hashCode());
        Object dbType = getDbType();
        return (((((((((result15 * 59) + (dbType == null ? 43 : dbType.hashCode())) * 59) + (isHasDuplicated() ? 79 : 97)) * 59) + (isHasDuplicatedSqlTagId() ? 79 : 97)) * 59) + (isExistSubSqlTagId() ? 79 : 97)) * 59) + (isExistSubSqlTagIdDuplicated() ? 79 : 97);
    }

    public String toString() {
        return "XmlNodeParserResult(sqlNodeId=" + getSqlNodeId() + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", nodeOptType=" + getNodeOptType() + ", containIfTest=" + isContainIfTest() + ", dynamicSql=" + isDynamicSql() + ", includeSql=" + isIncludeSql() + ", nodeAsXmlNoInclude=" + getNodeAsXmlNoInclude() + ", nameSpace=" + getNameSpace() + ", xmlComment=" + getXmlComment() + ", formatSql=" + getFormatSql() + ", druidFormatSql=" + getDruidFormatSql() + ", visitor=" + getVisitor() + ", xmlType=" + getXmlType() + ", xmlFilePath=" + getXmlFilePath() + ", statusCode=" + getStatusCode() + ", errorMsg=" + getErrorMsg() + ", exception=" + getException() + ", tableStatMap=" + getTableStatMap() + ", dbType=" + getDbType() + ", hasDuplicated=" + isHasDuplicated() + ", hasDuplicatedSqlTagId=" + isHasDuplicatedSqlTagId() + ", existSubSqlTagId=" + isExistSubSqlTagId() + ", existSubSqlTagIdDuplicated=" + isExistSubSqlTagIdDuplicated() + ")";
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

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public boolean isContainIfTest() {
        return this.containIfTest;
    }

    public void setContainIfTest(boolean containIfTest) {
        this.containIfTest = containIfTest;
    }

    public boolean isDynamicSql() {
        return this.dynamicSql;
    }

    public void setDynamicSql(boolean dynamicSql) {
        this.dynamicSql = dynamicSql;
    }

    public boolean isIncludeSql() {
        return this.includeSql;
    }

    public void setIncludeSql(boolean includeSql) {
        this.includeSql = includeSql;
    }

    public String getNodeAsXmlNoInclude() {
        return this.nodeAsXmlNoInclude;
    }

    public void setNodeAsXmlNoInclude(String nodeAsXmlNoInclude) {
        this.nodeAsXmlNoInclude = nodeAsXmlNoInclude;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getXmlComment() {
        return this.xmlComment;
    }

    public void setXmlComment(String xmlComment) {
        this.xmlComment = xmlComment;
    }

    public String getFormatSql() {
        return this.formatSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    public String getDruidFormatSql() {
        return this.druidFormatSql;
    }

    public void setDruidFormatSql(String druidFormatSql) {
        this.druidFormatSql = druidFormatSql;
    }

    public SchemaStatVisitor getVisitor() {
        return this.visitor;
    }

    public void setVisitor(SchemaStatVisitor visitor) {
        this.visitor = visitor;
    }

    public String getXmlType() {
        return this.xmlType;
    }

    public void setXmlType(String xmlType) {
        this.xmlType = xmlType;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseException getException() {
        return this.exception;
    }

    public void setException(BaseException exception) {
        this.exception = exception;
    }

    public Map<TableStat.Name, TableStat> getTableStatMap() {
        return this.tableStatMap;
    }

    public void setTableStatMap(Map<TableStat.Name, TableStat> tableStatMap) {
        this.tableStatMap = tableStatMap;
    }

    public String getDbType() {
        return this.dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public boolean isHasDuplicated() {
        return this.hasDuplicated;
    }

    public void setHasDuplicated(boolean hasDuplicated) {
        this.hasDuplicated = hasDuplicated;
    }

    public boolean isHasDuplicatedSqlTagId() {
        return this.hasDuplicatedSqlTagId;
    }

    public void setHasDuplicatedSqlTagId(boolean hasDuplicatedSqlTagId) {
        this.hasDuplicatedSqlTagId = hasDuplicatedSqlTagId;
    }

    public boolean isExistSubSqlTagId() {
        return this.existSubSqlTagId;
    }

    public void setExistSubSqlTagId(boolean existSubSqlTagId) {
        this.existSubSqlTagId = existSubSqlTagId;
    }

    public boolean isExistSubSqlTagIdDuplicated() {
        return this.existSubSqlTagIdDuplicated;
    }

    public void setExistSubSqlTagIdDuplicated(boolean existSubSqlTagIdDuplicated) {
        this.existSubSqlTagIdDuplicated = existSubSqlTagIdDuplicated;
    }
}
