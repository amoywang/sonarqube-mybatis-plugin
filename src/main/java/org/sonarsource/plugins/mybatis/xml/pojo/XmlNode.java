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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlNode) {
            XmlNode other = (XmlNode) o;
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
                Object thisnameSpace = getNameSpace();
                Object othernameSpace = other.getNameSpace();
                if (thisnameSpace == null) {
                    if (othernameSpace != null) {
                        return false;
                    }
                } else if (!thisnameSpace.equals(othernameSpace)) {
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
                Object thissqlComments = getSqlComments();
                Object othersqlComments = other.getSqlComments();
                if (thissqlComments == null) {
                    if (othersqlComments != null) {
                        return false;
                    }
                } else if (!thissqlComments.equals(othersqlComments)) {
                    return false;
                }
                Object thisnodeAsXml = getNodeAsXml();
                Object othernodeAsXml = other.getNodeAsXml();
                if (thisnodeAsXml == null) {
                    if (othernodeAsXml != null) {
                        return false;
                    }
                } else if (!thisnodeAsXml.equals(othernodeAsXml)) {
                    return false;
                }
                Object thismapperType = getMapperType();
                Object othermapperType = other.getMapperType();
                if (thismapperType == null) {
                    if (othermapperType != null) {
                        return false;
                    }
                } else if (!thismapperType.equals(othermapperType)) {
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
        Object sqlNodeId = getSqlNodeId();
        int result = (59) + (sqlNodeId == null ? 43 : sqlNodeId.hashCode());
        Object sqlNodeIdOrg = getSqlNodeIdOrg();
        int result2 = (result * 59) + (sqlNodeIdOrg == null ? 43 : sqlNodeIdOrg.hashCode());
        Object nameSpace = getNameSpace();
        int result3 = (result2 * 59) + (nameSpace == null ? 43 : nameSpace.hashCode());
        Object nodeOptType = getNodeOptType();
        int result4 = (result3 * 59) + (nodeOptType == null ? 43 : nodeOptType.hashCode());
        Object sqlComments = getSqlComments();
        int result5 = (result4 * 59) + (sqlComments == null ? 43 : sqlComments.hashCode());
        Object nodeAsXml = getNodeAsXml();
        int result6 = (result5 * 59) + (nodeAsXml == null ? 43 : nodeAsXml.hashCode());
        Object mapperType = getMapperType();
        int result7 = (result6 * 59) + (mapperType == null ? 43 : mapperType.hashCode());
        Object xmlFilePath = getXmlFilePath();
        return (((((result7 * 59) + (xmlFilePath == null ? 43 : xmlFilePath.hashCode())) * 59) + (isHasDuplicatedXmlNode() ? 79 : 97)) * 59) + (isHasDuplicatedSqlTagId() ? 79 : 97);
    }

    public String toString() {
        return "XmlNode(sqlNodeId=" + getSqlNodeId() + ", sqlNodeIdOrg=" + getSqlNodeIdOrg() + ", nameSpace=" + getNameSpace() + ", nodeOptType=" + getNodeOptType() + ", sqlComments=" + getSqlComments() + ", nodeAsXml=" + getNodeAsXml() + ", mapperType=" + getMapperType() + ", xmlFilePath=" + getXmlFilePath() + ", hasDuplicatedXmlNode=" + isHasDuplicatedXmlNode() + ", hasDuplicatedSqlTagId=" + isHasDuplicatedSqlTagId() + ")";
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

    public String getNameSpace() {
        return this.nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getNodeOptType() {
        return this.nodeOptType;
    }

    public void setNodeOptType(String nodeOptType) {
        this.nodeOptType = nodeOptType;
    }

    public String getSqlComments() {
        return this.sqlComments;
    }

    public void setSqlComments(String sqlComments) {
        this.sqlComments = sqlComments;
    }

    public String getNodeAsXml() {
        return this.nodeAsXml;
    }

    public void setNodeAsXml(String nodeAsXml) {
        this.nodeAsXml = nodeAsXml;
    }

    public String getMapperType() {
        return this.mapperType;
    }

    public void setMapperType(String mapperType) {
        this.mapperType = mapperType;
    }

    public String getXmlFilePath() {
        return this.xmlFilePath;
    }

    public void setXmlFilePath(String xmlFilePath) {
        this.xmlFilePath = xmlFilePath;
    }

    public boolean isHasDuplicatedXmlNode() {
        return this.hasDuplicatedXmlNode;
    }

    public void setHasDuplicatedXmlNode(boolean hasDuplicatedXmlNode) {
        this.hasDuplicatedXmlNode = hasDuplicatedXmlNode;
    }

    public boolean isHasDuplicatedSqlTagId() {
        return this.hasDuplicatedSqlTagId;
    }

    public void setHasDuplicatedSqlTagId(boolean hasDuplicatedSqlTagId) {
        this.hasDuplicatedSqlTagId = hasDuplicatedSqlTagId;
    }
}
