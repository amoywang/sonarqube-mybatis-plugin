package org.sonarsource.plugins.mybatis.regular.pojo;


import org.sonarsource.plugins.mybatis.regular.enums.RuleCodeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class XmlPluginRuleResultAll {
    private Map<RuleCodeEnum, List<XmlPluginRuleResult>> ruleMap = new HashMap();

    public void setRuleMap(Map<RuleCodeEnum, List<XmlPluginRuleResult>> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof XmlPluginRuleResultAll) {
            XmlPluginRuleResultAll other = (XmlPluginRuleResultAll) o;
            if (other.canEqual(this)) {
                Object this$ruleMap = getRuleMap();
                Object other$ruleMap = other.getRuleMap();
                return Objects.equals(this$ruleMap, other$ruleMap);
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(Object other) {
        return other instanceof XmlPluginRuleResultAll;
    }

    public int hashCode() {
        Object $ruleMap = getRuleMap();
        int result = (59) + ($ruleMap == null ? 43 : $ruleMap.hashCode());
        return result;
    }

    public String toString() {
        return "XmlPluginRuleResultAll(ruleMap=" + getRuleMap() + ")";
    }

    public Map<RuleCodeEnum, List<XmlPluginRuleResult>> getRuleMap() {
        return this.ruleMap;
    }
}
