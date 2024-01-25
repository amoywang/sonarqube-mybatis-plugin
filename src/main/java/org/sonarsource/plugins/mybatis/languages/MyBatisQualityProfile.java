package org.sonarsource.plugins.mybatis.languages;

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.plugins.xml.Xml;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import static org.sonarsource.plugins.mybatis.rules.MyBatisLintRulesDefinition.REPO_KEY;

/**
 * Default, BuiltIn Quality Profile for the projects having files of the language "xml"
 */
public final class MyBatisQualityProfile implements BuiltInQualityProfilesDefinition {

    @Override
    public void define(Context context) {
        NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile("MyBatisLint Rules", Xml.KEY);
        profile.setDefault(true);
        Map<String, String> rules = new HashMap<String, String>();
        ServiceLoader<AbstractRule> mybatisCheckRuleClasses = ServiceLoader.load(AbstractRule.class, AbstractRule.class.getClassLoader());
        for (AbstractRule rule : mybatisCheckRuleClasses) {
            rules.put(rule.getRuleID(), rule.getSeverity());
        }
        for (Map.Entry<String, String> entry : rules.entrySet()) {
            NewBuiltInActiveRule buildInRule = profile.activateRule(REPO_KEY, entry.getKey());
            buildInRule.overrideSeverity(entry.getValue());
        }
        profile.done();
    }

}
