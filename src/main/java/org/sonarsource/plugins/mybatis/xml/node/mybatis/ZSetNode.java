package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import org.apache.commons.lang.StringUtils;
import org.sonarsource.plugins.mybatis.wang.util.StringUtil;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;
import org.sonarsource.plugins.mybatis.xml.node.commom.TextNode;

public class ZSetNode extends BaseMybatisNode {
    @Override // org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" set ");
        String lastIfNodeStr = "";
        int len = this.sonParseResult.size();
        int j = len - 1;
        while (true) {
            if (j < 0) {
                break;
            }
            INode iNode = this.sonParseResult.get(j);
            if (((iNode instanceof TextNode) && StringUtils.isBlank(iNode.toString())) || !(iNode instanceof ZIfNode)) {
                j--;
            } else {
                String temp = iNode.toString();
                if (StringUtil.delAllSpace(temp).endsWith(",")) {
                    int lastIndex = temp.lastIndexOf(",");
                    StringBuilder stringBuilder = new StringBuilder(temp);
                    stringBuilder.setCharAt(lastIndex, ' ');
                    lastIfNodeStr = stringBuilder.toString();
                }
            }
        }
        if (!lastIfNodeStr.isEmpty()) {
            for (int i = 0; i < j; i++) {
                result.append(this.sonParseResult.get(i).toString());
            }
            result.append(lastIfNodeStr);
        } else {
            for (int i2 = 0; i2 < len; i2++) {
                result.append(this.sonParseResult.get(i2).toString());
            }
        }
        return result.toString();
    }

    @Override // org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode, org.sonarsource.plugins.mybatis.xml.node.base.INode
    public String toHtmlString() {
        StringBuilder result = new StringBuilder();
        result.append(" set ");
        for (INode sonNode : this.sonParseResult) {
            result.append(sonNode.toHtmlString());
        }
        return result.toString();
    }
}
