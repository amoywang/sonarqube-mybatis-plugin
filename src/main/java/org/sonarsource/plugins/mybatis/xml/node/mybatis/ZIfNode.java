package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class ZIfNode extends BaseMybatisNode {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toString());
            }
        }
        return result.toString();
    }

    @Override
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toHtmlString());
            }
        }
        return result.toString();
    }
}
