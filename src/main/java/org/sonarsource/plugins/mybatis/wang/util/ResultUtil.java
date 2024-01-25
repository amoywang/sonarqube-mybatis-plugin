package org.sonarsource.plugins.mybatis.wang.util;

import com.alibaba.druid.stat.TableStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.wang.pojo.BaseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;
import org.sonarsource.plugins.mybatis.xml.util.XmlUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum ResultUtil {
    ;
    
    private static final Logger log = LoggerFactory.getLogger(ResultUtil.class);

    public static void calcAcc(List<BaseResult> result, int sum) {
        if (result.isEmpty()) {
            return;
        }
        int scanNum = 0;
        long sucNum = 0;
        long errNum = 0;
        for (Object obj : result) {
            if (obj instanceof XmlParseResult) {
                XmlParseResult xmlParseResult = (XmlParseResult) obj;
                XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
                if (null != xmlNodeParserResult && !"sql".equalsIgnoreCase(xmlNodeParserResult.getNodeOptType())) {
                    boolean isSuccess = ErrorCodeEnum.SUCCESS.getCode().equals(xmlNodeParserResult.getStatusCode());
                    if (isSuccess) {
                        sucNum++;
                    } else {
                        errNum++;
                    }
                    scanNum++;
                }
            } else {
                log.error("解析结果,不支持类:{}", obj.getClass());
            }
        }
        BigDecimal bSucNum = new BigDecimal(sucNum);
        BigDecimal bErrNum = new BigDecimal(errNum);
        new BigDecimal(0);
        BigDecimal accErr = new BigDecimal(0);
        if (scanNum > 0) {
            bSucNum.divide(BigDecimal.valueOf(scanNum), 4, 6).multiply(BigDecimal.valueOf(100L));
            accErr = bErrNum.divide(BigDecimal.valueOf(scanNum), 4, 6).multiply(BigDecimal.valueOf(100L));
        }
        long ignoredNum = sum - scanNum;
        log.warn("SQL节点 总数:{},扫描数:{},忽略数:{}", new Object[]{Integer.valueOf(sum), Integer.valueOf(scanNum), Long.valueOf(ignoredNum)});
        log.warn("SQL扫描 扫描数:{}, 异常数:{}, 异常占比:{}%", new Object[]{Integer.valueOf(scanNum), Long.valueOf(errNum), accErr});
    }

    public static void doLog(List<BaseResult> result, LogBean logBean) throws Exception {
        if (null != logBean) {
            if (!logBean.getDetailLogFilePath().isEmpty()) {
                LogUtil.logAll(result, logBean.getDetailLogFilePath());
            } else {
                log.warn("注意:没有指定 详情日志 文件路径,不会生成详情日志,如果需要,可参考{@link com.cmb.batisparser.entity.LogBean}设置,例如:logBean.setDetailLogFileName(\"扫描详情\")");
            }
            if (!logBean.getErrorSqlDetailFilePath().isEmpty()) {
                LogUtil.logErrorAllDetail(result, logBean.getErrorSqlDetailFilePath());
            } else {
                log.warn("注意:没有指定 错误SQL汇总 日志文件路径。可参考{@link com.cmb.batisparser.entity.LogBean}设置");
            }
            if (!logBean.getTableOperateFilePath().isEmpty()) {
                Map<String, Map<String, Integer>> tableOptCountMap = getTableOptCount(result);
                LogUtil.logTableResult(tableOptCountMap, logBean.getTableOperateFilePath());
                return;
            }
            log.warn("注意:没有指定 各表操作汇总 文件路径。可参考{@link com.cmb.batisparser.entity.LogBean}设置");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static Map<String, Map<String, Integer>> getTableOptCount(List<org.sonarsource.plugins.mybatis.wang.pojo.BaseResult> r3) {
        /*
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r1 = r0
            r1.<init>()
            r4 = r0
            r0 = r3
            java.util.Iterator r0 = r0.iterator()
            r5 = r0
        Lf:
            r0 = r5
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L41
            r0 = r5
            java.lang.Object r0 = r0.next()
            r6 = r0
            r0 = r6
            org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult r0 = (org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult) r0
            r7 = r0
            r0 = r7
            org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult r0 = r0.getXmlNodeParserResult()
            r8 = r0
            r0 = r8
            java.util.Map r0 = r0.getTableStatMap()
            r1 = r4
            calcTableNameAndTypeCount(r0, r1)
            r0 = 0
            r1 = r8
            java.util.Map r1 = r1.getTableStatMap()
            if (r0 != r1) goto L3e
        L3e:
            goto Lf
        L41:
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.sonarsource.plugins.mybatis.wang.util.ResultUtil.getTableOptCount(java.util.List):java.util.Map");
    }

    private static void calcTableNameAndTypeCount(Map<TableStat.Name, TableStat> tableStatMap, Map<String, Map<String, Integer>> tableResult) {
        if (null == tableStatMap) {
            return;
        }
        for (Map.Entry<TableStat.Name, TableStat> entry : tableStatMap.entrySet()) {
            String tableName = entry.getKey().getName();
            String tableNameWithoutNamespace = XmlUtil.deleteXmlIdNameSpace(tableName);
            TableStat tableStat = entry.getValue();
            Map<String, Integer> optNameAndCountMap = tableResult.get(tableNameWithoutNamespace);
            if (null == optNameAndCountMap) {
                optNameAndCountMap = new LinkedHashMap<>();
                optNameAndCountMap.put("insert", 0);
                optNameAndCountMap.put("delete", 0);
                optNameAndCountMap.put("select", 0);
                optNameAndCountMap.put("update", 0);
                optNameAndCountMap.put("merge", 0);
                optNameAndCountMap.put("alter", 0);
                optNameAndCountMap.put("create", 0);
                optNameAndCountMap.put("drop", 0);
                tableResult.put(tableNameWithoutNamespace, optNameAndCountMap);
            }
            calcOptCount(tableStat, optNameAndCountMap, "insert");
            calcOptCount(tableStat, optNameAndCountMap, "delete");
            calcOptCount(tableStat, optNameAndCountMap, "select");
            calcOptCount(tableStat, optNameAndCountMap, "update");
            calcOptCount(tableStat, optNameAndCountMap, "merge");
            calcOptCount(tableStat, optNameAndCountMap, "alter");
            calcOptCount(tableStat, optNameAndCountMap, "create");
            calcOptCount(tableStat, optNameAndCountMap, "drop");
        }
    }

    public static XmlPluginRuleResultAll doRuleAll(List<BaseResult> result) {
        RuleCodeEnum ruleCodeEnum;
        XmlPluginRuleResult ruleUpdateNoWait;
        XmlPluginRuleResultAll xmlPluginRuleResultAll = new XmlPluginRuleResultAll();
        for (BaseResult baseResult : result) {
            XmlParseResult xmlParseResult = (XmlParseResult) baseResult;
            xmlParseResult.getXmlNodeParserResult().getStatusCode();
            String dbType = xmlParseResult.getXmlNodeParserResult().getDbType();
            if ("mysql".equalsIgnoreCase(dbType) && null != (ruleUpdateNoWait = RuleUtil.doRuleUpdateNoWait(xmlParseResult, (ruleCodeEnum = RuleCodeEnum.R0001)))) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum, k -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum);
                list.add(ruleUpdateNoWait);
            }
            RuleCodeEnum ruleCodeEnum2 = RuleCodeEnum.R0002;
            XmlPluginRuleResult ruleDuplicatedId = RuleUtil.doRuleDuplicatedId(xmlParseResult, ruleCodeEnum2);
            if (null != ruleDuplicatedId) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum2, k2 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list2 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum2);
                list2.add(ruleDuplicatedId);
            }
            RuleCodeEnum ruleCodeEnum3 = RuleCodeEnum.R0003;
            XmlPluginRuleResult ruleDuplicatedSqlTagId = RuleUtil.doRuleDuplicatedSqlTagId(xmlParseResult, ruleCodeEnum3);
            if (null != ruleDuplicatedSqlTagId && ruleCodeEnum3.isActive()) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum3, k3 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list3 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum3);
                list3.add(ruleDuplicatedSqlTagId);
            }
            RuleCodeEnum ruleCodeEnum4 = RuleCodeEnum.R0004;
            XmlPluginRuleResult existSubSqlTagId = RuleUtil.doRuleExistSubSqlTagId(xmlParseResult, ruleCodeEnum4);
            if (null != existSubSqlTagId && ruleCodeEnum4.isActive()) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum4, k4 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list4 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum4);
                list4.add(existSubSqlTagId);
            }
            RuleCodeEnum ruleCodeEnum5 = RuleCodeEnum.R0005;
            XmlPluginRuleResult existSubSqlTagIdDuplicated = RuleUtil.doRuleExistSubSqlTagIdDuplicated(xmlParseResult, ruleCodeEnum5);
            if (null != existSubSqlTagIdDuplicated && ruleCodeEnum5.isActive()) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum5, k5 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list5 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum5);
                list5.add(existSubSqlTagIdDuplicated);
            }
            RuleCodeEnum ruleCodeEnum6 = RuleCodeEnum.R1001;
            XmlPluginRuleResult ruleSelectStar = RuleUtil.doRuleSelectStar(xmlParseResult, ruleCodeEnum6);
            if (null != ruleSelectStar) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum6, k6 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list6 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum6);
                list6.add(ruleSelectStar);
            }
            RuleCodeEnum ruleCodeEnum7 = RuleCodeEnum.R1002;
            XmlPluginRuleResult ruleForbidden$ = RuleUtil.doRuleForbidden$(xmlParseResult, ruleCodeEnum7);
            if (null != ruleForbidden$) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum7, k7 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list7 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum7);
                list7.add(ruleForbidden$);
            }
            RuleCodeEnum ruleCodeEnum8 = RuleCodeEnum.R1003;
            XmlPluginRuleResult ruleNoConditionInUpdateDelete = RuleUtil.doRuleNoConditionInUpdateDelete(xmlParseResult, ruleCodeEnum8);
            if (null != ruleNoConditionInUpdateDelete) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum8, k8 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list8 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum8);
                list8.add(ruleNoConditionInUpdateDelete);
            }
            XmlPluginRuleResult ruleUpdateSubQueryMustHaveExists = RuleUtil.doRuleUpdateSubQueryMustHaveExists(xmlParseResult, RuleCodeEnum.R1004);
            if (null != ruleUpdateSubQueryMustHaveExists) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(RuleCodeEnum.R1004, k9 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list9 = xmlPluginRuleResultAll.getRuleMap().get(RuleCodeEnum.R1004);
                list9.add(ruleUpdateSubQueryMustHaveExists);
            }
            RuleCodeEnum ruleCodeEnum9 = RuleCodeEnum.R2001;
            XmlPluginRuleResult ruleIfTest = RuleUtil.doRuleIfTest(xmlParseResult, ruleCodeEnum9);
            if (null != ruleIfTest) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum9, k10 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list10 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum9);
                list10.add(ruleIfTest);
            }
            RuleCodeEnum ruleCodeEnum10 = RuleCodeEnum.R2002;
            XmlPluginRuleResult ruleTableMoreThan3 = RuleUtil.doRuleTableMoreThan5(xmlParseResult, ruleCodeEnum10);
            if (null != ruleTableMoreThan3) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum10, k11 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list11 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum10);
                list11.add(ruleTableMoreThan3);
            }
            RuleCodeEnum ruleCodeEnum11 = RuleCodeEnum.R2003;
            XmlPluginRuleResult ruleNullCompare = RuleUtil.doRuleNullCompare(xmlParseResult, ruleCodeEnum11);
            if (null != ruleNullCompare) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum11, k12 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list12 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum11);
                list12.add(ruleNullCompare);
            }
            RuleCodeEnum ruleCodeEnum12 = RuleCodeEnum.R2004;
            XmlPluginRuleResult ruleNoConditionInSelect = RuleUtil.doRuleNoConditionInSelect(xmlParseResult, ruleCodeEnum12);
            if (null != ruleNoConditionInSelect) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum12, k13 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list13 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum12);
                list13.add(ruleNoConditionInSelect);
            }
            RuleCodeEnum ruleCodeEnum13 = RuleCodeEnum.R2005;
            XmlPluginRuleResult doRuleJoinMustOn = RuleUtil.doRuleJoinMustOn(xmlParseResult, ruleCodeEnum13);
            if (null != doRuleJoinMustOn) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum13, k14 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list14 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum13);
                list14.add(doRuleJoinMustOn);
            }
            RuleCodeEnum ruleCodeEnum14 = RuleCodeEnum.R2006;
            XmlPluginRuleResult doRuleForbiddenTrigger = RuleUtil.doRuleForbiddenTrigger(xmlParseResult, ruleCodeEnum14);
            if (null != doRuleForbiddenTrigger) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum14, k15 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list15 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum14);
                list15.add(doRuleForbiddenTrigger);
            }
            RuleCodeEnum ruleCodeEnum15 = RuleCodeEnum.R2007;
            XmlPluginRuleResult ruleInsertMustHaveColumns = RuleUtil.doRuleInsertMustHaveColumns(xmlParseResult, ruleCodeEnum15);
            if (null != ruleInsertMustHaveColumns) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum15, k16 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list16 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum15);
                list16.add(ruleInsertMustHaveColumns);
            }
            RuleCodeEnum ruleCodeEnum16 = RuleCodeEnum.R9999;
            XmlPluginRuleResult grammarResult = RuleUtil.getGrammarResult(xmlParseResult, ruleCodeEnum16);
            if (null != grammarResult) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum16, k17 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list17 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum16);
                list17.add(grammarResult);
            }
        }
        return xmlPluginRuleResultAll;
    }

    private static void calcOptCount(TableStat tableStat, Map<String, Integer> stringIntegerMap, String opt) {
        int add = 0;
        if ("insert".equals(opt)) {
            add = tableStat.getInsertCount();
        }
        if ("delete".equals(opt)) {
            add = tableStat.getDeleteCount();
        }
        if ("select".equals(opt)) {
            add = tableStat.getSelectCount();
        }
        if ("update".equals(opt)) {
            add = tableStat.getUpdateCount();
        }
        if ("merge".equals(opt)) {
            add = tableStat.getMergeCount();
        }
        if ("alter".equals(opt)) {
            add = tableStat.getAlterCount();
        }
        if ("create".equals(opt)) {
            add = tableStat.getCreateCount();
        }
        if ("drop".equals(opt)) {
            add = tableStat.getDropCount();
        }
        Integer count = stringIntegerMap.get(opt);
        stringIntegerMap.put(opt, Integer.valueOf(count.intValue() + add));
    }
}
