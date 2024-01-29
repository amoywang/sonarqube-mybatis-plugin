package org.sonarsource.plugins.mybatis.regular.enums;

import org.apache.commons.lang.StringUtils;

public enum RuleCodeEnum {
    R0001(true, DegreeEnum.BLOCKED, "for_update必须含有wait"),
    R0002(true, DegreeEnum.BLOCKED, "mapper重复id"),
    R0003(true, DegreeEnum.BLOCKED, "mapper重复sql_id"),
    R0004(true, DegreeEnum.BLOCKED, "refid引用id不存在"),
    R0005(true, DegreeEnum.BLOCKED, "refid引用id存在重复"),
//    R1001(true, DegreeEnum.CRITICAL, "禁止select星号"),
//    R1002(true, DegreeEnum.CRITICAL, "参数必须使用#{}，不允许使用${}"),
    R1003(true, DegreeEnum.CRITICAL, "禁止update,delete不带条件"),
    R1004(true, DegreeEnum.CRITICAL, "update含有子查询需使用exists,否则匹配不到,字段会赋空值"),
    R2001(true, DegreeEnum.MAJOR, "增删不建议使用if_test动态标签"),
    R2002(true, DegreeEnum.MAJOR, "多表join个数不要超过5张"),
    R2003(true, DegreeEnum.MAJOR, "null禁止直接比较"),
    R2004(true, DegreeEnum.MAJOR, "禁止select不带条件"),
    R2005(true, DegreeEnum.MAJOR, "join操作必须有on条件,避免笛卡尔积"),
    R2006(true, DegreeEnum.MAJOR, "禁止在系统中使用触发器trigger"),
    R2007(true, DegreeEnum.MAJOR, "INSERT语句必须明确字段列表"),
    R3000(true, DegreeEnum.MAJOR, "SQL解析失败，请确认SQL是否拼写正确"),
    R9999(true, DegreeEnum.MAJOR, "可能存在语法或风险");

    private boolean active;
    private DegreeEnum degreeEnum;
    private final String desc;

    RuleCodeEnum(boolean active, DegreeEnum degreeEnum, String desc) {
        this.active = active;
        this.degreeEnum = degreeEnum;
        this.desc = desc;
    }

    public static RuleCodeEnum parse(String code) {
        RuleCodeEnum[] values;
        for (RuleCodeEnum item : values()) {
            if (StringUtils.equals(code, item.name())) {
                return item;
            }
        }
        throw new RuntimeException("Enum code not exist.");
    }
    public String getName(){
        return this.name();
    }
    public String getDesc() {
        return this.desc;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DegreeEnum getDegreeEnum() {
        return this.degreeEnum;
    }

    public void setDegreeEnum(DegreeEnum degreeEnum) {
        this.degreeEnum = degreeEnum;
    }
}
