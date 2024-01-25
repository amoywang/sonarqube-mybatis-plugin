package org.sonarsource.plugins.mybatis.xml.node.base;

import java.util.ArrayList;
import java.util.List;

public class BaseMybatisNode implements INode {
    protected String prefix = "";
    protected String suffix = "";
    protected String prefixOverrides = "";
    protected String suffixOverrides = "";
    protected String item = "";
    protected String index = "";
    protected String collection = "";
    protected String open = "";
    protected String close = "";
    protected String separator = "";
    protected String test = "";
    protected boolean propertyConfilct = false;
    protected List<INode> sonParseResult = new ArrayList();

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefixOverrides(String prefixOverrides) {
        this.prefixOverrides = prefixOverrides;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffixOverrides(String suffixOverrides) {
        this.suffixOverrides = suffixOverrides;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setItem(String item) {
        this.item = item;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setIndex(String index) {
        this.index = index;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setOpen(String open) {
        this.open = open;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setClose(String close) {
        this.close = close;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setTest(String test) {
        this.test = test;
    }

    public void setPropertyConfilct(boolean propertyConfilct) {
        this.propertyConfilct = propertyConfilct;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSonParseResult(List<INode> sonParseResult) {
        this.sonParseResult = sonParseResult;
    }

    public boolean isPropertyConfilct() {
        return this.propertyConfilct;
    }

    public List<INode> getSonParseResult() {
        return this.sonParseResult;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        return null;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setProperty(String property) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setRemoveFirstPrepend(boolean removeFirstPrepend) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareProperty(String compareProperty) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareValue(String compareValue) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConjunction(String conjunction) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConfilct(String type) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSaparator(String saparator) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrepend(String prepend) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String getPrepend() {
        return null;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getPrefixOverrides() {
        return this.prefixOverrides;
    }

    public String getSuffixOverrides() {
        return this.suffixOverrides;
    }

    public String getItem() {
        return this.item;
    }

    public String getIndex() {
        return this.index;
    }

    public String getCollection() {
        return this.collection;
    }

    public String getOpen() {
        return this.open;
    }

    public String getClose() {
        return this.close;
    }

    public String getSeparator() {
        return this.separator;
    }

    public String getTest() {
        return this.test;
    }
}
