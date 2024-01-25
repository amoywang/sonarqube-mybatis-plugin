package org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin;



import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                return this$ruleMap == null ? other$ruleMap == null : this$ruleMap.equals(other$ruleMap);
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
        int result = (1 * 59) + ($ruleMap == null ? 43 : $ruleMap.hashCode());
        return result;
    }

    public String toString() {
        return "XmlPluginRuleResultAll(ruleMap=" + getRuleMap() + ")";
    }

    public Map<RuleCodeEnum, List<XmlPluginRuleResult>> getRuleMap() {
        return this.ruleMap;
    }
}
