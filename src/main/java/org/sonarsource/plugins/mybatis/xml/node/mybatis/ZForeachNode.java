package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class ZForeachNode extends BaseMybatisNode {
    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        return result.toString();
    }

    @Override
    // org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        return result.toString();
    }
}
