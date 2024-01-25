package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class ZOtherwiseNode extends BaseMybatisNode {
    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toString());
            }
        }
        return result.toString();
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toHtmlString());
            }
        }
        return result.toString();
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConfilct(String type) {
        if (type.equalsIgnoreCase(Constant.WHEN)) {
            this.propertyConfilct = true;
        }
    }
}
