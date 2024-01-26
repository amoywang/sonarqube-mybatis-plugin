package org.sonarsource.plugins.mybatis.xml.node.commom;

import org.sonarsource.plugins.mybatis.xml.node.base.BaseCommonNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class CdataNode extends BaseCommonNode {
    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toString());
        }
        result.append(this.stringValue);
        return result.toString();
    }

    @Override
    // org.sonarsource.plugins.mybatis.xml.node.base.BaseCommonNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        result.append(this.stringValue);
        return result.toString();
    }
}
