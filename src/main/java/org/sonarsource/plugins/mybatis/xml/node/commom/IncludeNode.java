package org.sonarsource.plugins.mybatis.xml.node.commom;

import org.sonarsource.plugins.mybatis.xml.node.base.BaseCommonNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class IncludeNode extends BaseCommonNode {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toString());
        }
        return result.toString();
    }
}
