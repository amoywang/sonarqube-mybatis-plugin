package org.sonarsource.plugins.mybatis.xml.pojo;

public class IncludeMapNode implements IXmlNodes {
    private String sqlClassId;
    private String sqlXml;

    public String getSqlClassId() {
        return this.sqlClassId;
    }

    public void setSqlClassId(String sqlClassId) {
        this.sqlClassId = sqlClassId;
    }

    public String getSqlXml() {
        return this.sqlXml;
    }

    public void setSqlXml(String sqlXml) {
        this.sqlXml = sqlXml;
    }
}
