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

    @Override
    public String toString() {
        return "";
    }

    @Override
    public String toHtmlString() {
        return "";
    }

    @Override
    public void setSonParseResult(List<INode> sonParseResult) {
        this.sonParseResult = sonParseResult;
    }

    @Override
    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    public void setClose(String close) {
        this.close = close;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void setRemoveFirstPrepend(boolean removeFirstPrepend) {
        this.removeFirstPrepend = removeFirstPrepend;
    }

    @Override
    public void setCompareProperty(String compareProperty) {
        this.compareProperty = compareProperty;
    }

    @Override
    public void setCompareValue(String compareValue) {
        this.compareValue = compareValue;
    }

    @Override
    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    @Override
    public void setTest(String test) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setPrefix(String prefix) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setSuffix(String suffix) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setPrefixOverrides(String prefixOverrides) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setSuffixOverrides(String suffixOverrides) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setItem(String item) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setIndex(String index) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setCollection(String collection) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setSaparator(String saparator) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public void setConfilct(String type) {
        throw new UnsupportedOperationException("Ibatis not support this method");
    }

    @Override
    public String getPrepend() {
        return null;
    }

    @Override
    public void setPrepend(String prepend) {
        this.prepend = prepend;
    }
}
