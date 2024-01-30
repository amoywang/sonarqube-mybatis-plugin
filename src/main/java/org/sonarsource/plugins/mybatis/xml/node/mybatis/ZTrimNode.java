package org.sonarsource.plugins.mybatis.xml.node.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.node.base.BaseMybatisNode;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;
import org.sonarsource.plugins.mybatis.xml.node.commom.TextNode;

public class ZTrimNode extends BaseMybatisNode {
    public static void main(String[] args) {
        String[] temp = "AND | OR".split("\\|");
        System.out.println(JSON.toJSONString(temp));
        System.out.println("  a a a s s ".trim().replaceFirst("a", "com/zhang"));
        int index = "abc ab bb aa zhang".lastIndexOf("com/zhang");
        System.out.println("abc ab bb aa zhang".substring(0, index));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(Constant.SPACE_CHAR).append(this.prefix).append(Constant.SPACE_CHAR);
        boolean firstElement = true;
        for (int i = 0; i < this.sonParseResult.size(); i++) {
            INode sonNode = this.sonParseResult.get(i);
            if (!(sonNode instanceof TextNode) || !sonNode.toString().isEmpty()) {
                if (firstElement && !StringUtils.isBlank(this.prefixOverrides)) {
                    String[] prefixOverridesList = this.prefixOverrides.split("\\|");
                    String tempSonNode = sonNode.toString().trim();
                    int length = prefixOverridesList.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        String prefix = prefixOverridesList[i2];
                        if (!tempSonNode.startsWith(prefix.trim())) {
                            i2++;
                        } else {
                            tempSonNode = tempSonNode.replaceFirst(prefix.trim(), "");
                            break;
                        }
                    }
                    result.append(tempSonNode);
                    firstElement = false;
                } else {
                    result.append(sonNode.toString());
                }
            }
        }
        result.append(Constant.SPACE_CHAR).append(this.suffix).append(Constant.SPACE_CHAR);
        String[] suffixOverridesList = this.suffixOverrides.split("\\|");
        String tempResult = result.toString().trim();
        int length2 = suffixOverridesList.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                break;
            }
            String suffix = suffixOverridesList[i3];
            if (!tempResult.endsWith(suffix.trim())) {
                i3++;
            } else {
                int index = tempResult.lastIndexOf(suffix.trim());
                tempResult = tempResult.substring(0, index);
                break;
            }
        }
        return tempResult;
    }
}
