package org.sonarsource.plugins.mybatis.xml.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.regular.util.StringUtil;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlFormatUtil {
    private static final Logger logger = LoggerFactory.getLogger(SqlFormatUtil.class);

    public static String mybatisFormat(String unFormatSql) {
        String newSql = StringUtil.delLineBreak(unFormatSql);
        String sql = StringUtil.mergeBlank(fixThePar(newSql));
        sql = fixTheVar(sql);
        sql = fixPrependWhere(sql);
        sql = fixTheBracket(sql);
        return fixAbc(fixMybatisVerySpecial(fixNonVar(fixKeyWord(fixLineBlank(sql)))));
    }

    public static String ibatisFormat(String unFormatSql) {
        String newSql = StringUtil.delLineBreak(unFormatSql);
        return fixAbc(fixIbatisVerySpecial(fixBracket$(fixMultiLimit(fixNonVar(fixKeyWord(fixLineBlank(fixTheBracket(fixPrependWhere(fixTheVar(StringUtil.mergeBlank(fixThePar(newSql))))))))))));
    }

    public static String fixThePar(String newSql) {
        Pattern pattern = Pattern.compile("#(\\w+(\\w*[,|=|.|:]\\w*)+\\w+)#", 8);
        Matcher matcher = pattern.matcher(newSql);
        while (matcher.find()) {
            String oldStr = matcher.group(1);
            String newStr = matcher.group(1).replaceAll(",", "_").replaceAll("=", "_").replaceAll("\\.", "_").replaceAll(":", "_");
            newSql = newSql.replace(oldStr, newStr);
        }
        return newSql;
    }

    public static String fixTheVar(String sql) {
        if (sql.indexOf("=#") > 0) {
            return sql.replaceAll("=#", "= #");
        }
        return sql;
    }

    public static String fixPrependWhere(String str) {
        if (str.toLowerCase().contains(" where ,") || str.toLowerCase().contains(" where and ") || str.toLowerCase().contains(" where or ")) {
            str = str.toLowerCase().replaceAll(" where\\s(,|and|or)\\s", " where ");
        }
        if (str.toLowerCase().contains(" set ,")) {
            str = str.toLowerCase().replaceAll(" set\\s,", " set ");
        }
        if (str.contains(", ,")) {
            str = str.toLowerCase().replaceAll(",\\s,", " , ");
        }
        if (str.toLowerCase().contains(" and and")) {
            str = str.toLowerCase().replaceAll(" and\\sand\\s", " and ");
        }
        if (str.contains("&#")) {
            str = str.replaceAll("&#", "& #");
        }
        if (str.contains("!=-")) {
            str = str.replaceAll("!=-", "!= -");
        }
        if (str.contains(", where")) {
            str = str.toLowerCase().replaceAll(",\\swhere\\s", " where ");
        }
        if (str.contains(" where ,")) {
            str = str.toLowerCase().replaceAll(" where\\s,", " where ");
        }
        if (str.contains(", values")) {
            str = str.toLowerCase().replaceAll(",\\svalues\\s", " values ");
        }
        if (str.contains(" values ,")) {
            str = str.toLowerCase().replaceAll(" values\\s,", " values ");
        }
        if (str.contains(", )")) {
            str = str.toLowerCase().replaceAll(",\\s\\)", " ) ");
        }
        return str;
    }

    public static String fixTheBracket(String str) {
        if (str.indexOf("[]") > 0) {
            return str.replaceAll("\\[]", "");
        }
        return str;
    }

    public static String fixLineBlank(String sql) {
        if (sql.indexOf("_ ") > 0 || sql.indexOf(" _") > 0) {
            return sql.replaceAll("_ ", "_").replaceAll(" _", "_");
        }
        return sql;
    }

    public static String fixKeyWord(String sql) {
        Pattern pattern = Pattern.compile("#(\\s*(comment|start|end)\\s*)#", 8);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String oldStr = matcher.group(1);
            String newStr = matcher.group(1) + "KEY";
            sql = sql.replace(oldStr, newStr);
        }
        return sql;
    }

    public static String fixNonVar(String sql) {
        Pattern pattern = Pattern.compile("#(\\s*)#", 8);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String oldStr = matcher.group();
            sql = sql.replace(oldStr, "#PARAM#");
        }
        return sql;
    }

    public static String fixSpecialChr(String sql) {
        Pattern pattern = Pattern.compile("#\\{(.*?)\\}", 8);
        Matcher matcher = pattern.matcher(sql);
        while (matcher.find()) {
            String raw = matcher.group(0);
            String inner = matcher.group(1);
            sql = sql.replace(raw, "'" + inner + "'");
        }
        return sql;
    }

    public static String formatIbatisdruidSharp(String sql) {
        StringBuffer sb = new StringBuffer();
        boolean z = true;
        while (true) {
            boolean start = z;
            if (sql.indexOf("#") > 0) {
                if (start) {
                    sb.append(sql, 0, sql.indexOf("#"));
                    sql = sql.substring(sql.indexOf("#") + 1);
                    sb.append(" ${");
                    z = false;
                } else {
                    sb.append(sql, 0, sql.indexOf("#"));
                    sql = sql.substring(sql.indexOf("#") + 1);
                    sb.append("}");
                    z = true;
                }
            } else {
                sb.append(sql);
                return sb.toString();
            }
        }
    }

    public static String fixMultiLimit(String sql) {
        if (sql.toLowerCase().indexOf("limit") == sql.toLowerCase().lastIndexOf("limit")) {
            return sql;
        }
        int index = sql.toLowerCase().lastIndexOf("limit");
        return sql.substring(0, index);
    }

    public static String fixBracket$(String sql) {
        return sql.replaceAll("\\((\\s*)\\$", "\\${").replaceAll("\\$(\\s*)\\)", "\\}");
    }

    public static String fixMybatisVerySpecial(String sql) {
        for (String keyWord : Constant.MYBATIS_SPECIAL_KEY_WORD) {
            sql = sql.toLowerCase().replaceAll(keyWord.toLowerCase(), "'" + keyWord + "'");
        }
        return sql.replaceAll("＝", "=").replaceAll("%s", "");
    }

    public static String fixIbatisVerySpecial(String sql) {
        for (String keyWord : Constant.IBATIS_SPECIAL_KEY_WORD) {
            sql = sql.toLowerCase().replaceAll(keyWord.toLowerCase(), "'" + keyWord + "'");
        }
        return sql.replaceAll("＝", "=").replaceAll("%s", "");
    }

    public static String fixAbc(String sql) {
        String newSql1 = sql.replaceAll("#\\{([a-zA-Z0-9]*)\\s*.\\s*([a-zA-Z0-9]*)\\s*,\\s*[jJ][dD][bB][cC][tT][yY][pP][eE]\\s*=\\s*\\w*\\s*}", "'$2'");
        String newSql2 = newSql1.replaceAll("#\\{([a-zA-Z0-9]*)\\s*,\\s*[jJ][dD][bB][cC][tT][yY][pP][eE]\\s*=\\s*\\w*\\s*}", "'$1'");
        String newSql3 = newSql2.replaceAll("#\\{[a-zA-Z0-9]*\\.([a-zA-Z0-9]*)\\s*}", "'$1'");
        String newSql4 = newSql3.replaceAll("#\\{([a-zA-Z0-9]*)\\s*}", "'$1'");
        return newSql4;
    }
}
