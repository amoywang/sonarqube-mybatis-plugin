package org.sonarsource.plugins.mybatis.xml.node.base;

import java.util.ArrayList;
import java.util.List;

public class BaseCommonNode implements INode {
    public String textValue;
    public String stringValue;
    public String compareProperty;
    public String compareValue;
    public String conjunction;
    public List<INode> sonParseResult = new ArrayList();
    public String prepend = "";
    public String open = "";
    public String close = "";
    boolean removeFirstPrepend = false;
    boolean propertyConfilct = false;
    public String test = "";
    public String prefix = "";
    public String suffix = "";
    public String prefixOverrides = "";
    public String suffixOverrides = "";
    public String item = "";
    public String index = "";
    public String collection = "";
    public String separator = "";

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        return "";
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setProperty(String property) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConfilct(String type) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSaparator(String saparator) {
    }

    public String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public List<INode> getSonParseResult() {
        return this.sonParseResult;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSonParseResult(List<INode> sonParseResult) {
        this.sonParseResult = sonParseResult;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String getPrepend() {
        return this.prepend;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrepend(String prepend) {
        this.prepend = prepend;
    }

    public String getOpen() {
        return this.open;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return this.close;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setClose(String close) {
        this.close = close;
    }

    public boolean isRemoveFirstPrepend() {
        return this.removeFirstPrepend;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setRemoveFirstPrepend(boolean removeFirstPrepend) {
        this.removeFirstPrepend = removeFirstPrepend;
    }

    public String getCompareProperty() {
        return this.compareProperty;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareProperty(String compareProperty) {
        this.compareProperty = compareProperty;
    }

    public String getCompareValue() {
        return this.compareValue;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareValue(String compareValue) {
        this.compareValue = compareValue;
    }

    public String getConjunction() {
        return this.conjunction;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public boolean isPropertyConfilct() {
        return this.propertyConfilct;
    }

    public void setPropertyConfilct(boolean propertyConfilct) {
        this.propertyConfilct = propertyConfilct;
    }

    public String getTest() {
        return this.test;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setTest(String test) {
        this.test = test;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefixOverrides() {
        return this.prefixOverrides;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefixOverrides(String prefixOverrides) {
        this.prefixOverrides = prefixOverrides;
    }

    public String getSuffixOverrides() {
        return this.suffixOverrides;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffixOverrides(String suffixOverrides) {
        this.suffixOverrides = suffixOverrides;
    }

    public String getItem() {
        return this.item;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setItem(String item) {
        this.item = item;
    }

    public String getIndex() {
        return this.index;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setIndex(String index) {
        this.index = index;
    }

    public String getCollection() {
        return this.collection;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getSeparator() {
        return this.separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
