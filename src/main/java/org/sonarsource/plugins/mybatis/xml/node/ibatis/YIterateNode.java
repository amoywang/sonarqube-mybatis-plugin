package org.sonarsource.plugins.mybatis.xml.node.ibatis;

import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseIbatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;

public class YIterateNode extends BaseIbatisNode {
    @Override

    public String toString() {
        StringBuilder result = new StringBuilder();
        if (!this.removeFirstPrepend) {
            result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
        }
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        String resultString = result.toString();
        if (resultString.contains("[")) {
            resultString = resultString.replaceAll("\\[", "");
        }
        if (resultString.contains("]")) {
            resultString = resultString.replaceAll("]", "");
        }
        return resultString;
    }

    @Override

    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        if (!this.removeFirstPrepend) {
            result.append(Constant.SPACE_CHAR).append(this.prepend).append(Constant.SPACE_CHAR);
        }
        result.append(Constant.SPACE_CHAR).append(this.open).append(Constant.SPACE_CHAR);
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        result.append(Constant.SPACE_CHAR).append(this.close).append(Constant.SPACE_CHAR);
        String resultString = result.toString();
        if (resultString.contains("[")) {
            resultString = resultString.replaceAll("\\[", "");
        }
        if (resultString.contains("]")) {
            resultString = resultString.replaceAll("]", "");
        }
        return resultString;
    }
}
