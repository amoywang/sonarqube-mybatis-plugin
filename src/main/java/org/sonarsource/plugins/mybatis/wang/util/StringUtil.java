package org.sonarsource.plugins.mybatis.wang.util;

import org.sonarsource.plugins.mybatis.xml.consts.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StringUtil {
    ;

    private static final Pattern P1 = Pattern.compile(" {2,}");
    private static final Pattern P2 = Pattern.compile("\\t{2,}");

    public static String delLineBreak(String sql) {
        return sql.trim().replace("\n", Constant.SPACE_CHAR).replace("\t", Constant.SPACE_CHAR).replace("\r", Constant.SPACE_CHAR).replace("\f", Constant.SPACE_CHAR);
    }

    public static String mergeBlank(String sql) {
        Matcher m = P1.matcher(sql);
        return m.replaceAll(Constant.SPACE_CHAR);
    }

    public static String formatSqlInLine(String sql) {
        String newSql = delLineBreak(sql);
        return mergeBlank(newSql);
    }

    public static String delAllSpace(String sql) {
        return sql.trim().replace("\n", "").replace("\\n", "").replace("\r", "").replace("\\r", "").replace("\t", "").replace("\\t", "").replace("\f", "").replace("\\f", "").replace(Constant.SPACE_CHAR, "");
    }
}
