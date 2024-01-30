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

    public boolean isPropertyConfilct() {
        return this.propertyConfilct;
    }

    public void setPropertyConfilct(boolean propertyConfilct) {
        this.propertyConfilct = propertyConfilct;
    }

    public List<INode> getSonParseResult() {
        return this.sonParseResult;
    }

    @Override
    public void setSonParseResult(List<INode> sonParseResult) {
        this.sonParseResult = sonParseResult;
    }

    @Override
    public String toHtmlString() {
        return null;
    }

    @Override
    public void setProperty(String property) {
        throw new UnsupportedOperationException("Mybatis not support this method");
    }

    @Override
    public void setRemoveFirstPrepend(boolean removeFirstPrepend) {
    }

    @Override
    public void setCompareProperty(String compareProperty) {
    }

    @Override
    public void setCompareValue(String compareValue) {
    }

    @Override
    public void setConjunction(String conjunction) {
    }

    @Override
    public void setConfilct(String type) {
    }

    @Override
    public void setSaparator(String saparator) {
    }

    @Override
    public String getPrepend() {
        return null;
    }

    @Override
    public void setPrepend(String prepend) {
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPrefixOverrides() {
        return this.prefixOverrides;
    }

    @Override
    public void setPrefixOverrides(String prefixOverrides) {
        this.prefixOverrides = prefixOverrides;
    }

    public String getSuffixOverrides() {
        return this.suffixOverrides;
    }

    @Override
    public void setSuffixOverrides(String suffixOverrides) {
        this.suffixOverrides = suffixOverrides;
    }

    public String getItem() {
        return this.item;
    }

    @Override
    public void setItem(String item) {
        this.item = item;
    }

    public String getIndex() {
        return this.index;
    }

    @Override
    public void setIndex(String index) {
        this.index = index;
    }

    public String getCollection() {
        return this.collection;
    }

    @Override
    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getOpen() {
        return this.open;
    }

    @Override
    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return this.close;
    }

    @Override
    public void setClose(String close) {
        this.close = close;
    }

    public String getSeparator() {
        return this.separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getTest() {
        return this.test;
    }

    @Override
    public void setTest(String test) {
        this.test = test;
    }
}
