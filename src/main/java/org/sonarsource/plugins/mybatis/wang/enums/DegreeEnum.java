package org.sonarsource.plugins.mybatis.wang.enums;

import org.apache.commons.lang.StringUtils;

/* loaded from: sql_scanner-3.2.3-SNAPSHOT.jar:com/zhang/zmain/enums/DegreeEnum.class */
public enum DegreeEnum {
    BLOCKED("Blocked", "阻塞"),
    CRITICAL("Critical", "致命"),
    MAJOR("Major", "严重");
    
    private String code;
    private String desc;

    DegreeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DegreeEnum parse(String code) {
        DegreeEnum[] values;
        for (DegreeEnum item : values()) {
            if (StringUtils.equals(code, item.code)) {
                return item;
            }
        }
        throw new RuntimeException("Enum code not exist.");
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
