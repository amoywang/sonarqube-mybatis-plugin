package org.sonarsource.plugins.mybatis.wang.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.regular.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.wang.pojo.regular.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.wang.util.RuleUtil;

import java.util.ArrayList;
import java.util.List;

public class RegularRuleHandler {
    private static final Logger log = LoggerFactory.getLogger(RegularRuleHandler.class);


    public static XmlPluginRuleResultAll doRuleAll(List<XmlParseResult> result) {
        RuleCodeEnum ruleCodeEnum;
        XmlPluginRuleResult ruleUpdateNoWait;
        XmlPluginRuleResultAll xmlPluginRuleResultAll = new XmlPluginRuleResultAll();
        for (XmlParseResult xmlParseResult : result) {
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

            /*RuleCodeEnum ruleCodeEnum6 = RuleCodeEnum.R1001;
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
            }*/
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
            RuleCodeEnum ruleCodeEnum17 = RuleCodeEnum.R3000;
            XmlPluginRuleResult parseError = RuleUtil.getParseError(xmlParseResult, ruleCodeEnum17);
            if (null != parseError) {
                xmlPluginRuleResultAll.getRuleMap().computeIfAbsent(ruleCodeEnum17, k16 -> {
                    return new ArrayList();
                });
                List<XmlPluginRuleResult> list16 = xmlPluginRuleResultAll.getRuleMap().get(ruleCodeEnum17);
                list16.add(parseError);
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
}
