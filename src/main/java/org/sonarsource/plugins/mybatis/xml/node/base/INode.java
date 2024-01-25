package org.sonarsource.plugins.mybatis.xml.node.base;

import java.util.List;

public interface INode {
    String toString();

    String toHtmlString();

    void setPrepend(String str);

    String getPrepend();

    void setOpen(String str);

    void setClose(String str);

    void setProperty(String str);

    void setRemoveFirstPrepend(boolean z);

    void setCompareProperty(String str);

    void setCompareValue(String str);

    void setConjunction(String str);

    void setConfilct(String str);

    void setSonParseResult(List<INode> list);

    void setTest(String str);

    void setPrefix(String str);

    void setSuffix(String str);

    void setPrefixOverrides(String str);

    void setSuffixOverrides(String str);

    void setItem(String str);

    void setIndex(String str);

    void setCollection(String str);

    void setSaparator(String str);
}
