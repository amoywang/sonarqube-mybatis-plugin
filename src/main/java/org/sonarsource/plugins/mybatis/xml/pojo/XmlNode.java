package org.sonarsource.plugins.mybatis.xml.pojo;

public class XmlNode implements IXmlNodes {
    private String sqlNodeId;
    private String sqlNodeIdOrg;
    private String nameSpace;
    private String nodeOptType;
    private String sqlComments;
    private String nodeAsXml;
    private String mapperType;
    private String xmlFilePath;
    private boolean hasDuplicatedXmlNode;
    private boolean hasDuplicatedSqlTagId;

    public void setSqlNodeId(String sqlNodeId) {
        this.sqlNodeId = sqlNodeId;
    }

    public void setSqlNodeIdOrg(String sqlNodeIdOrg) {
        this.sqlNodeIdOrg = sqlNodeIdOrg;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public void setSqlComments(String sqlComments) {
        this.sqlComments = sqlComments;
    }

    public void setNodeAsXml(String nodeAsXml) {
        this.nodeAsXml = nodeAsXml;
    }

    public void setMapperType(String mapperType) {
        this.mapperType = mapperType;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public void setHasDuplicatedXmlNode(boolean hasDuplicatedXmlNode) {
        this.hasDuplicatedXmlNode = hasDuplicatedXmlNode;
    }

    public void setHasDuplicatedSqlTagId(boolean hasDuplicatedSqlTagId) {
        this.hasDuplicatedSqlTagId = hasDuplicatedSqlTagId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlNode) {
            XmlNode other = (XmlNode) o;
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
                Object this$nameSpace = getNameSpace();
                Object other$nameSpace = other.getNameSpace();
                if (this$nameSpace == null) {
                    if (other$nameSpace != null) {
                        return false;
                    }
                } else if (!this$nameSpace.equals(other$nameSpace)) {
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
                Object this$sqlComments = getSqlComments();
                Object other$sqlComments = other.getSqlComments();
                if (this$sqlComments == null) {
                    if (other$sqlComments != null) {
                        return false;
                    }
                } else if (!this$sqlComments.equals(other$sqlComments)) {
                    return false;
                }
                Object this$nodeAsXml = getNodeAsXml();
                Object other$nodeAsXml = other.getNodeAsXml();
                if (this$nodeAsXml == null) {
                    if (other$nodeAsXml != null) {
                        return false;
                    }
                } else if (!this$nodeAsXml.equals(other$nodeAsXml)) {
                    return false;
                }
                Object this$mapperType = getMapperType();
                Object other$mapperType = other.getMapperType();
                if (this$mapperType == null) {
                    if (other$mapperType != null) {
                        return false;
                    }
                } else if (!this$mapperType.equals(other$mapperType)) {
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
                return isHasDuplicatedXmlNode() == other.isHasDuplicatedXmlNode() && isHasDuplicatedSqlTagId() == other.isHasDuplicatedSqlTagId();
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof XmlNode;
    }

    public int hashCode() {
        Object $sqlNodeId = getSqlNodeId();
        int result = (59) + ($sqlNodeId == null ? 43 : $sqlNodeId.hashCode());
        Object $sqlNodeIdOrg = getSqlNodeIdOrg();
        int result2 = (result * 59) + ($sqlNodeIdOrg == null ? 43 : $sqlNodeIdOrg.hashCode());
        Object $nameSpace = getNameSpace();
        int result3 = (result2 * 59) + ($nameSpace == null ? 43 : $nameSpace.hashCode());
        Object $nodeOptType = getNodeOptType();
        int result4 = (result3 * 59) + ($nodeOptType == null ? 43 : $nodeOptType.hashCode());
        Object $sqlComments = getSqlComments();
        int result5 = (result4 * 59) + ($sqlComments == null ? 43 : $sqlComments.hashCode());
        Object $nodeAsXml = getNodeAsXml();
        int result6 = (result5 * 59) + ($nodeAsXml == null ? 43 : $nodeAsXml.hashCode());
        Object $mapperType = getMapperType();
        int result7 = (result6 * 59) + ($mapperType == null ? 43 : $mapperType.hashCode());
        Object $xmlFilePath = getXmlFilePath();
        return (((((result7 * 59) + ($xmlFilePath == null ? 43 : $xmlFilePath.hashCode())) * 59) + (isHasDuplicatedXmlNode() ? 79 : 97)) * 59) + (isHasDuplicatedSqlTagId() ? 79 : 97);
    }

    public String toString() {
        return "XmlNode(sqlNodeId=" + getSqlNodeId() + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", nameSpace=" + getNameSpace() + ", nodeOptType=" + getNodeOptType() + ", sqlComments=" + getSqlComments() + ", nodeAsXml=" + getNodeAsXml() + ", mapperType=" + getMapperType() + ", xmlFilePath=" + getXmlFilePath() + ", hasDuplicatedXmlNode=" + isHasDuplicatedXmlNode() + ", hasDuplicatedSqlTagId=" + isHasDuplicatedSqlTagId() + ")";
    }

    public String getSqlNodeId() {
        return this.sqlNodeId;
    }

    public String getSqlNodeIdOrg() {
        return this.sqlNodeIdOrg;
    }

    public String getNameSpace() {
        return this.nameSpace;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public String getSqlComments() {
        return this.sqlComments;
    }

    public String getNodeAsXml() {
        return this.nodeAsXml;
    }

    public String getMapperType() {
        return this.mapperType;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }

    public boolean isHasDuplicatedXmlNode() {
        return this.hasDuplicatedXmlNode;
    }

    public boolean isHasDuplicatedSqlTagId() {
        return this.hasDuplicatedSqlTagId;
    }
}
