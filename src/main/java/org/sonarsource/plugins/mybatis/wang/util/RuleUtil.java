package org.sonarsource.plugins.mybatis.wang.util;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.exception.DruidParseException;
import org.sonarsource.plugins.mybatis.xml.exception.base.BaseException;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RuleUtil {
    ;

    public static XmlPluginRuleResult doRuleUpdateNoWait(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        Matcher matcher = Pattern.compile("\\s*for\\s*update\\s*([a-zA-Z]*)").matcher(sql);
        if (matcher.find()) {
            String word = matcher.group(1);
            if (word.isEmpty() || !"wait".equalsIgnoreCase(word)) {
                return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "for update必须有wait");
            }
            return null;
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleDuplicatedId(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        boolean hasDuplicated = xmlNodeParserResult.isHasDuplicated();
        if (hasDuplicated) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "mapper重复id.");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleDuplicatedSqlTagId(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        boolean hasDuplicatedSqlTagId = xmlNodeParserResult.isHasDuplicatedSqlTagId();
        if (hasDuplicatedSqlTagId) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "mapper重复<sql id='xxx'>.");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleExistSubSqlTagId(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        boolean existSubSqlTagId = xmlNodeParserResult.isExistSubSqlTagId();
        if (!existSubSqlTagId) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "节点中 refid id 不存在");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleExistSubSqlTagIdDuplicated(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        boolean existSubSqlTagIdDuplicated = xmlNodeParserResult.isExistSubSqlTagIdDuplicated();
        if (existSubSqlTagIdDuplicated) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "节点中 refid id 不存在");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleSelectStar(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        if (null == xmlNodeParserResult.getException()) {
            SchemaStatVisitor visitor = xmlNodeParserResult.getVisitor();
            String formatSql = xmlNodeParserResult.getFormatSql();
            boolean containSelectAll = DruidUtil.isContainSelectAll(visitor, formatSql);
            if (containSelectAll) {
                return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "禁止使用select *");
            }
            return null;
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleForbidden$(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        Matcher matcher = Pattern.compile("\\$\\{([a-zA-Z0-9]*)}").matcher(sql);
        if (matcher.find()) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "禁止使用${}");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleNoConditionInUpdateDelete(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        String nodeOptType = xmlNodeParserResult.getNodeOptType();
        boolean isSuccess = ErrorCodeEnum.SUCCESS.getCode().equalsIgnoreCase(xmlNodeParserResult.getStatusCode());
        boolean isUpdateDelete = "update".equalsIgnoreCase(nodeOptType) || "delete".equalsIgnoreCase(nodeOptType);
        boolean isWhereConditon = sql.contains(Constant.WHERE);
        boolean isJoinConditon = sql.contains("join");
        boolean isWhenConditon = sql.contains(Constant.WHEN);
        if (isSuccess && isUpdateDelete && !isWhereConditon && !isJoinConditon && !isWhenConditon) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "update/delete 必须加上条件.不带条件的UPDATE/DELETE会更新或删除全表数据，存在巨大的误操作风险。");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleUpdateSubQueryMustHaveExists(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        SchemaStatVisitor visitor = xmlNodeParserResult.getVisitor();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        String nodeOptType = xmlNodeParserResult.getNodeOptType();
        Matcher matcher = Pattern.compile("\\s*begin.*end").matcher(sql);
        if (matcher.find()) {
            return null;
        }
        boolean isContainSubQuery = false;
        if ("update".equalsIgnoreCase(nodeOptType) && null != visitor) {
            Map<TableStat.Name, TableStat> tables = visitor.getTables();
            if (null != tables && tables.size() > 1) {
                Iterator<Map.Entry<TableStat.Name, TableStat>> it = tables.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<TableStat.Name, TableStat> entry = it.next();
                    entry.getKey().getName();
                    TableStat tableStat = entry.getValue();
                    if (tableStat.getSelectCount() > 0) {
                        isContainSubQuery = true;
                        break;
                    }
                }
            }
            if (isContainSubQuery && !sql.contains("exists")) {
                return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "正例\nUPDATE t1 SET t1.a = (SELECT t2.b FROM t2 WHERE t1.id = t2.id)\nWHERE EXISTS(SELECT 1 FROM t2 WHERE t1.id = t2.id)\n如果t2不存在t1.id = t2.id的记录，那么t1.a会被赋值为null");
            }
            return null;
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleNoConditionInSelect(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        String nodeOptType = xmlNodeParserResult.getNodeOptType();
        boolean isSelect = "select".equalsIgnoreCase(nodeOptType);
        boolean isWhereConditon = sql.contains(Constant.WHERE);
        boolean isJoinConditon = sql.contains("join");
        if (isSelect && !isWhereConditon && !isJoinConditon) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "select 必须加上条件.不带条件 select 操作会导致全表扫描");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleJoinMustOn(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        boolean isJoinConditon = sql.contains("join");
        boolean isOn = sql.contains("on");
        if (isJoinConditon && !isOn) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "join操作必须有on条件,避免笛卡尔积");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleForbiddenTrigger(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        boolean isUseTrigger = sql.contains("trigger");
        if (isUseTrigger) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "禁止在系统中使用触发器trigger");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleInsertMustHaveColumns(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        SchemaStatVisitor visitor = xmlNodeParserResult.getVisitor();
        String nodeOptType = xmlNodeParserResult.getNodeOptType();
        if ("insert".equalsIgnoreCase(nodeOptType) && null != visitor) {
            Collection<TableStat.Column> columns = visitor.getColumns();
            if (null == columns || columns.isEmpty()) {
                return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "INSERT语句必须明确字段列表");
            }
            return null;
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleIfTest(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String optType = xmlNodeParserResult.getNodeOptType();
        if (!"select".equalsIgnoreCase(optType) && xmlNodeParserResult.isContainIfTest()) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "增删改含有if-test动态标签,建议去掉if-test,一个业务操作对应一条SQL,不要写一个大而全的更新接口.避免更新无改动的字段");
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleTableMoreThan5(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        SchemaStatVisitor visitor = xmlNodeParserResult.getVisitor();
        if (null != visitor && null != visitor.getTables()) {
            Map<TableStat.Name, TableStat> tableStatMap = visitor.getTables();
            int size = tableStatMap.size();
            if (size > 5) {
                return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "多表关联建议不要超过5张表");
            }
            return null;
        }
        return null;
    }

    public static XmlPluginRuleResult doRuleNullCompare(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        Matcher matcher1 = Pattern.compile("=\\s*null").matcher(sql);
        Matcher matcher2 = Pattern.compile("null\\s*=").matcher(sql);
        Matcher matcher3 = Pattern.compile("null\\s*!\\s*=").matcher(sql);
        Matcher matcher4 = Pattern.compile("null\\s*<\\s*>").matcher(sql);
        Matcher matcher5 = Pattern.compile("\\s*<\\s*>\\s*null").matcher(sql);
        if (matcher1.find() || matcher2.find() || matcher3.find() || matcher4.find() || matcher5.find()) {
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, "null比较应该使用IS NULL或IS NOT NULL进行比较");
        }
        return null;
    }

    public static XmlPluginRuleResult getGrammarResult(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum) {
        String parseResultOrSuggestion;
        if (!ruleCodeEnum.isActive()) {
            return null;
        }
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        String sql = xmlNodeParserResult.getFormatSql().toLowerCase();
        BaseException exception = xmlNodeParserResult.getException();
        if (exception instanceof DruidParseException) {
            DruidParseException druidParseException = (DruidParseException) exception;
            if (sql.contains("$")) {
                parseResultOrSuggestion = "不支持解析$符号,存在SQL注入可能性,建议用#.";
            } else if (sql.contains("--")) {
                parseResultOrSuggestion = "可能包含非法注释(--),请使用标准的mybatis注释标签";
            } else {
                parseResultOrSuggestion = druidParseException.getMessage();
            }
            return createXmlPluginRuleResult(xmlParseResult, ruleCodeEnum, parseResultOrSuggestion);
        }
        return null;
    }

    public static XmlPluginRuleResult createXmlPluginRuleResult(XmlParseResult xmlParseResult, RuleCodeEnum ruleCodeEnum, String parseResultOrSuggestion) {
        XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
        return XmlPluginRuleResult.builder().mapperName(xmlParseResult.getMapperName()).sqlNodeId(xmlNodeParserResult.getSqlNodeId()).sqlNodeIdOrg(xmlNodeParserResult.getSqlNodeIdOrg()).sqlText(xmlNodeParserResult.getFormatSql()).nodeOptType(xmlNodeParserResult.getNodeOptType()).druidFormatSql(xmlNodeParserResult.getDruidFormatSql()).parseResult(parseResultOrSuggestion).ruleCodeEnum(ruleCodeEnum).dbType(xmlNodeParserResult.getDbType()).filePath(xmlNodeParserResult.getXmlFilePath()).build();
    }
}
