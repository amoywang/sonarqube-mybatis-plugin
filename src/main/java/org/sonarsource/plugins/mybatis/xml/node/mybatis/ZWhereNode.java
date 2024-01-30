package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class ZWhereNode extends BaseMybatisNode {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" where ");
        for (int i = 0; i < this.sonParseResult.size(); i++) {
            INode sonNode = this.sonParseResult.get(i);
            result.append(sonNode.toString());
        }
        return result.toString();
    }

    @Override
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        result.append(" where ");
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        return result.toString();
    }
}
