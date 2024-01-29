package org.sonarsource.plugins.mybatis.xml;

import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;

import java.util.Objects;

public class XmlParseResult extends BaseResult {
    private String mapperFilePath;
    private String mapperName;
    private int lineNumber;
    private XmlNodeParserResult xmlNodeParserResult;

    public String getMapperFilePath() {
        return mapperFilePath;
    }

    public void setMapperFilePath(String mapperFilePath) {
        this.mapperFilePath = mapperFilePath;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public void setXmlNodeParserResult(XmlNodeParserResult xmlNodeParserResult) {
        this.xmlNodeParserResult = xmlNodeParserResult;
    }

    @Override
    public String toString() {
        return "XmlParseResult(mapperName=" + getMapperName() + ",lineNumber=" + getLineNumber() +
                ", xmlNodeParserResult=" + getXmlNodeParserResult() + ")";
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer line) {
        this.lineNumber = line;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlParseResult) {
            XmlParseResult other = (XmlParseResult) o;
            if (other.canEqual(this) && super.equals(o)) {
                Object this$mapperName = getMapperName();
                Object other$mapperName = other.getMapperName();
                if (this$mapperName == null) {
                    if (other$mapperName != null) {
                        return false;
                    }
                } else if (!this$mapperName.equals(other$mapperName)) {
                    return false;
                }
                Object this$xmlNodeParserResult = getXmlNodeParserResult();
                Object other$xmlNodeParserResult = other.getXmlNodeParserResult();
                if (getLineNumber() != other.getLineNumber()) {
                    return false;
                }
                return Objects.equals(this$xmlNodeParserResult, other$xmlNodeParserResult);
            }
            return false;
        }
        return false;
    }

    @Override
    protected boolean canEqual(Object other) {
        return other instanceof XmlParseResult;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        Object $mapperName = getMapperName();
        result = result * 59 + getLineNumber().hashCode();
        int result2 = (result * 59) + ($mapperName == null ? 43 : $mapperName.hashCode());
        Object $xmlNodeParserResult = getXmlNodeParserResult();
        return (result2 * 59) + ($xmlNodeParserResult == null ? 43 : $xmlNodeParserResult.hashCode());
    }

    public String getMapperName() {
        return this.mapperName;
    }

    public XmlNodeParserResult getXmlNodeParserResult() {
        return this.xmlNodeParserResult;
    }
}
