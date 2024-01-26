package org.sonarsource.plugins.mybatis.sql;

import com.alibaba.druid.sql.SQLUtils;
import org.apache.commons.lang.StringUtils;
import org.sonarsource.plugins.mybatis.xml.node.base.INode;
import org.sonarsource.plugins.mybatis.xml.node.mybatis.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ParseUtil {
    ;

    public static void dealWithSequenceSetIf(List<INode> nodeParserResultList) {
        if (null == nodeParserResultList || nodeParserResultList.isEmpty()) {
            return;
        }
        for (int i = 0; i < nodeParserResultList.size(); i++) {
            INode curNode = nodeParserResultList.get(i);
            if (curNode instanceof ZWhereNode) {
                dealWithSequenceSetIf(((ZWhereNode) curNode).getSonParseResult());
            }
            if (curNode instanceof ZIfNode) {
                int j = i + 1;
                while (j < nodeParserResultList.size() && StringUtils.isBlank(nodeParserResultList.get(j).toString())) {
                    j++;
                }
                if (j < nodeParserResultList.size() && (nodeParserResultList.get(j) instanceof ZIfNode)) {
                    ZIfNode curIfNode = (ZIfNode) curNode;
                    ZIfNode nextIfNode = (ZIfNode) nodeParserResultList.get(j);
                    String nextIfTest = nextIfNode.getTest();
                    String curIfTest = curIfNode.getTest();
                    List<String> curVarList = getVarList(curIfTest);
                    Collection<?> nextVarList = getVarList(nextIfTest);
                    List<String> tempList = new ArrayList<>();
                    deepCopyList(curVarList, tempList);
                    tempList.retainAll(nextVarList);
                    boolean isSameCondition = !tempList.isEmpty() && tempList.size() == curVarList.size();
                    if (isSameCondition) {
                        nextIfNode.getSonParseResult().clear();
                    }
                }
                List<INode> sonParseResultList = ((ZIfNode) curNode).getSonParseResult();
                dealWithSequenceSetIf(sonParseResultList);
            }
        }
    }

    public static void dealWithSequenceChoose(List<INode> nodeParserResultList) {
        if (null == nodeParserResultList || nodeParserResultList.isEmpty()) {
            return;
        }
        for (INode iNode : nodeParserResultList) {
            if (iNode instanceof ZChooseNode) {
                List<INode> chooseSonNodeList = ((ZChooseNode) iNode).getSonParseResult();
                for (int i = 0; i < chooseSonNodeList.size(); i++) {
                    INode chooseSonNode = chooseSonNodeList.get(i);
                    if (chooseSonNode instanceof ZChooseNode) {
                        List<INode> sonList = ((ZChooseNode) chooseSonNode).getSonParseResult();
                        dealWithSequenceChoose(sonList);
                    }
                    if (chooseSonNode instanceof ZOtherwiseNode) {
                        ((ZOtherwiseNode) chooseSonNode).getSonParseResult().clear();
                    } else if (chooseSonNode instanceof ZWhenNode) {
                        int j = i + 1;
                        while (j < chooseSonNodeList.size() && StringUtils.isBlank(chooseSonNodeList.get(j).toString())) {
                            j++;
                        }
                        if (j < chooseSonNodeList.size() && (chooseSonNodeList.get(j) instanceof ZWhenNode)) {
                            ZWhenNode curWhenNode = (ZWhenNode) chooseSonNode;
                            ZWhenNode nextWhenNode = (ZWhenNode) chooseSonNodeList.get(j);
                            String nextIfTest = nextWhenNode.getTest();
                            String curIfTest = curWhenNode.getTest();
                            List<String> curVarList = getVarList(curIfTest);
                            Collection<?> nextVarList = getVarList(nextIfTest);
                            List<String> tempList = new ArrayList<>();
                            deepCopyList(curVarList, tempList);
                            tempList.retainAll(nextVarList);
                            boolean isSameCondition = !tempList.isEmpty() && tempList.size() == curVarList.size();
                            if (isSameCondition) {
                                List<INode> next = nextWhenNode.getSonParseResult();
                                next.clear();
                            }
                        }
                    }
                }
            }
        }
    }

    public static String formatByDruid(String sql, String dbType) {
        String fSql;
        try {
            fSql = SQLUtils.format(sql, dbType);
        } catch (Exception e) {
            fSql = "";
        }
        return fSql;
    }

    public static List<String> getVarList(String testStr) {
        List<String> varList = new ArrayList<>();
        String newStr = testStr.replaceAll("<\\s*>", "!=");
        List<Pattern> patternList = new LinkedList<>();
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*=\\s*=\\s*(\\S*)", 8));
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*!\\s*=\\s*(\\S*)", 8));
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*>\\s*(\\S*)", 8));
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*>\\s*=\\s*(\\S*)", 8));
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*<\\s*(\\S*)", 8));
        patternList.add(Pattern.compile("\\s*(\\S*)\\s*<\\s*=\\s*(\\S*)", 8));
        for (int i = 0; i < patternList.size(); i++) {
            Pattern pattern = patternList.get(i);
            Matcher matcher = pattern.matcher(newStr);
            while (matcher.find()) {
                String leftVar = matcher.group(1);
                String rightVar = matcher.group(2);
                boolean isLeftConstant = "null".equalsIgnoreCase(leftVar.trim()) || leftVar.contains("'") || leftVar.contains("\"") || StringUtils.isNumeric(leftVar);
                boolean isRightConstant = "null".equalsIgnoreCase(rightVar.trim()) || rightVar.contains("'") || rightVar.contains("\"") || StringUtils.isNumeric(rightVar);
                if (!isLeftConstant && !isRightConstant) {
                    if (leftVar.compareTo(rightVar) > 0) {
                        leftVar = rightVar;
                    }
                    varList.add(leftVar);
                } else if (isLeftConstant) {
                    varList.add(rightVar);
                } else {
                    varList.add(leftVar);
                }
            }
        }
        return varList;
    }

    public static void deepCopyList(List<String> listA, List<String> listB) {
        if (null != listA && null != listB && listB.isEmpty()) {
            listB.addAll(listA);
        }
    }
}
