package org.sonarsource.plugins.mybatis.wang.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public enum DateUtil {
    ;

    private static final DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter ymdHms = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter ymdHmsSSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private static final DateTimeFormatter y_m_d_H_m_s = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    private static final DateTimeFormatter mdHmsSSS = DateTimeFormatter.ofPattern("MMddHHmmssSSS");
    private static final DateTimeFormatter hmsSSS = DateTimeFormatter.ofPattern("HHmmssSSS");
    private static final DateTimeFormatter md_Hms = DateTimeFormatter.ofPattern("MMdd-HHmmss");

    public static String getYmd() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(ymd);
    }

    public static String getYmdHms() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(ymdHms);
    }

    public static String getYmdHmsSSS() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(ymdHmsSSS);
    }

    public static String get_y_m_d_H_m_s() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(y_m_d_H_m_s);
    }

    public static String getMdHmsSSS() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(mdHmsSSS);
    }

    public static String getMd_Hms() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(md_Hms);
    }

    public static String getHmsSSS() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return localDateTime.format(hmsSSS);
    }
}
