package org.sonarsource.plugins.mybatis.rules;


import org.sonar.api.rule.RuleKey;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.xml.Xml;
import org.sonarsource.plugins.mybatis.sql.AbstractRule;
import org.sonarsource.plugins.mybatis.regular.enums.RuleCodeEnum;

import java.util.ServiceLoader;

public final class MyBatisLintRulesDefinition implements RulesDefinition {
    public static final String REPO_KEY = "MyBatisXmlLint";
    private static final String REPO_NAME = REPO_KEY;

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPO_KEY, Xml.KEY).setName(REPO_NAME);
        ServiceLoader<AbstractRule> mybatisCheckRuleClasses = ServiceLoader.load(AbstractRule.class, AbstractRule.class.getClassLoader());
        NewRule temp;
        for (AbstractRule rule : mybatisCheckRuleClasses) {
            RuleKey tempRuleKey = RuleKey.of(REPO_KEY, rule.getRuleID());
            temp = repository.createRule(tempRuleKey.rule())
                    .setName(rule.getName())
                    .setHtmlDescription(rule.getDescription())
                    .setTags("mybatis-mapper-xml", "cndinfo")
                    .setType(RuleType.VULNERABILITY)
                    .setSeverity(rule.getSeverity());
            temp.setDebtRemediationFunction(temp.debtRemediationFunctions().linear("10min"));
        }
        for (RuleCodeEnum codeEnum : RuleCodeEnum.values()) {
            RuleKey tempRuleKey = RuleKey.of(REPO_KEY, codeEnum.getName());
            temp = repository.createRule(tempRuleKey.rule())
                    .setName(codeEnum.getDesc())
                    .setHtmlDescription(codeEnum.getDesc())
                    .setTags("mybatis-mapper-xml", "cndinfo")
                    .setType(RuleType.BUG)
                    .setSeverity(codeEnum.getDegreeEnum().getCode());
            temp.setDebtRemediationFunction(temp.debtRemediationFunctions().linear("10min"));
        }
        repository.done();
    }

}
