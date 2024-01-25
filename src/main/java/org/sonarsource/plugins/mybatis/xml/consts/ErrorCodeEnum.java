package org.sonarsource.plugins.mybatis.xml.consts;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ErrorCodeEnum {
    SUCCESS("200000", "成功"),
    E500001("E500001", "系统异常"),
    E500002("E500002", "sql可能存在潜在问题"),
    E500003("E500003", "无法获取xml节点node的id"),
    E500004("E500004", "不支持该xml节点node的类型"),
    E500006("E500006", "节点标签无对应的处理类"),
    E500007("E500007", "refid <sql id>重复"),
    E500008("E500008", "节点id重复"),
    E500009("E500009", "refid的id不存在"),
    E500010("E500010", "refid的id存在重复"),
    E888888("E888888", "违反规则"),
    E999991("E999991", "ibaits正在完善,暂不支持"),
    E999999("E999999", "xml必须为mybaits或ibatis");
    
    private static final Logger log = LoggerFactory.getLogger(ErrorCodeEnum.class);
    private String code;
    private String desc;

    ErrorCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorCodeEnum parse(String code) {
        ErrorCodeEnum[] values;
        for (ErrorCodeEnum item : values()) {
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

    @Override // java.lang.Enum
    public String toString() {
        return this.code + ":" + this.desc;
    }
}
