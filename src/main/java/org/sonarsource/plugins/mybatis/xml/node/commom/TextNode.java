package org.sonarsource.plugins.mybatis.xml.node.commom;

import org.sonarsource.plugins.mybatis.xml.node.base.BaseCommonNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class TextNode extends BaseCommonNode {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toString());
        }
        result.append(this.textValue);
        return result.toString();
    }

    @Override
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        result.append(this.textValue);
        return result.toString();
    }
}
