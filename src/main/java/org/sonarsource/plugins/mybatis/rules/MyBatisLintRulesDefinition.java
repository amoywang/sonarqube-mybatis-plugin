package org.sonarsource.plugins.mybatis.rules;


import org.sonar.api.rule.RuleKey;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.xml.Xml;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;

import java.util.ServiceLoader;

public final class MyBatisLintRulesDefinition implements RulesDefinition {
    public static final String REPO_KEY = "MyBatisXmlLint";
    protected static final String REPO_NAME = REPO_KEY;

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPO_KEY, Xml.KEY).setName(REPO_NAME);
        ServiceLoader<AbstractRule> mybatisCheckRuleClasses = ServiceLoader.load(AbstractRule.class, AbstractRule.class.getClassLoader());
        for (AbstractRule rule : mybatisCheckRuleClasses) {
            RuleKey tempRuleKey = RuleKey.of(REPO_KEY, rule.getRuleID());
            NewRule temp = repository.createRule(tempRuleKey.rule())
                    .setName(rule.getName())
                    .setHtmlDescription(rule.getDescription())
                    .setTags("mybatis-xml")
                    .setType(RuleType.SECURITY_HOTSPOT)
                    .setSeverity(rule.getSeverity());
            temp.setDebtRemediationFunction(temp.debtRemediationFunctions().linear("10min"));
        }
        repository.done();
    }

}
