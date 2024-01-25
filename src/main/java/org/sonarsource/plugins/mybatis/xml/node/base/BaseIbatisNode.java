package org.sonarsource.plugins.mybatis.xml.node.base;

import java.util.ArrayList;
import java.util.List;


public class BaseIbatisNode implements INode {
    public String property;
    public String compareProperty;
    public String compareValue;
    public String conjunction;
    public String prepend = "";
    public String open = "";
    public String close = "";
    public boolean removeFirstPrepend = false;
    public boolean propertyConfilct = false;
    public List<INode> sonParseResult = new ArrayList();

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        return "";
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        return "";
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSonParseResult(List<INode> sonParseResult) {
        this.sonParseResult = sonParseResult;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrepend(String prepend) {
        this.prepend = prepend;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setOpen(String open) {
        this.open = open;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setClose(String close) {
        this.close = close;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setProperty(String property) {
        this.property = property;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setRemoveFirstPrepend(boolean removeFirstPrepend) {
        this.removeFirstPrepend = removeFirstPrepend;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareProperty(String compareProperty) {
        this.compareProperty = compareProperty;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCompareValue(String compareValue) {
        this.compareValue = compareValue;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setTest(String test) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefix(String prefix) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffix(String suffix) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setPrefixOverrides(String prefixOverrides) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSuffixOverrides(String suffixOverrides) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setItem(String item) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setIndex(String index) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setCollection(String collection) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setSaparator(String saparator) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConfilct(String type) {
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String getPrepend() {
        return null;
    }
}
