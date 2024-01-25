package org.sonarsource.plugins.mybatis.wang.util;

import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;

public enum DruidUtil {
    ;

    public static boolean isContainSelectAll(SchemaStatVisitor visitor, String sql) {
        boolean flag = false;
        if (null != visitor) {
            for (TableStat.Column column : visitor.getColumns()) {
                if ("*".equals(column.getName().trim()) && !"dual".equalsIgnoreCase(column.getTable()) && !sql.toLowerCase().contains("count")) {
                    flag = true;
                }
            }
        }
        return flag;
    }
}
