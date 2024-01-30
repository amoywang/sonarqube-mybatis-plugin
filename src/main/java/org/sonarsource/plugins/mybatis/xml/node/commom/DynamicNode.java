package org.sonarsource.plugins.mybatis.xml.node.commom;

import org.apache.commons.lang.StringUtils;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseCommonNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class DynamicNode extends BaseCommonNode {
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        int j = 1;
        for (int i = 0; i < this.sonParseResult.size(); i++) {
            INode sonNode = this.sonParseResult.get(i);
            if (1 == j && !StringUtils.isBlank(sonNode.getPrepend())) {
                j++;
                sonNode.setRemoveFirstPrepend(true);
            }
            result.append(sonNode.toString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        return result.toString();
    }

    @Override
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        return result.toString();
    }
}
