package org.sonarsource.plugins.mybatis.xml.node.ibatis;

import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseIbatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

/* xml/node/ibatis/YIsGreaterEqualNode.class */
public class YIsGreaterEqualNode extends BaseIbatisNode {
    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseIbatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            result.append(this.open);
            if (!this.removeFirstPrepend) {
                result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
            }
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toString());
            }
            result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        }
        return result.toString();
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseIbatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        if (!this.propertyConfilct) {
            result.append(this.open);
            if (!this.removeFirstPrepend) {
                result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
            }
            for (INode sonNode : this.sonParseResult) {
                result.append(sonNode.toHtmlString());
            }
            result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        }
        return result.toString();
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseIbatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public void setConfilct(String type) {
        if (type.equalsIgnoreCase(Constant.ISGREATERTHAN)) {
            this.propertyConfilct = true;
        }
    }
}
