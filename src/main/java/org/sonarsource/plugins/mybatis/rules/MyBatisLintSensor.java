package org.sonarsource.plugins.mybatis.rules;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.config.Configuration;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonar.plugins.xml.Xml;
import org.sonarsource.plugins.mybatis.sql.RuleCheckResult;
import org.sonarsource.plugins.mybatis.wang.parser.XmlBatisSqlParser;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.sonarsource.plugins.mybatis.MyBatisPlugin.SONAR_MYBATIS_SKIP;



/**
 * The goal of this Sensor is analysis mybatis mapper files and generate issues.
 */
public class MyBatisLintSensor implements Sensor {

    private static final Logger LOGGER = Loggers.get(MyBatisLintSensor.class);

    private static final String LEFT_SLASH = "/";
    private static final String SELECT = "select";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String WHERE = "where";
    private static final String COUNT_STAR = "count(*)";
    private static final String STAR = "*";

    protected final Configuration config;
    protected final FileSystem fileSystem;
    protected SensorContext context;
    private List<String> stmtIdExcludeList = new ArrayList<>();

    /**
     * Use of IoC to get Settings, FileSystem, RuleFinder and ResourcePerspectives
     */
    public MyBatisLintSensor(final Configuration config, final FileSystem fileSystem) {
        this.config = config;
        this.fileSystem = fileSystem;
    }

    @Override
    public void describe(final SensorDescriptor descriptor) {
        descriptor.name("MyBatisLint Sensor");
        descriptor.onlyOnLanguage(Xml.KEY);
    }

    @Override
    public void execute(final SensorContext context) {
        this.context = context;
        Boolean sonarMyBatisSkipBooleanValue = Boolean.valueOf(false);
        Optional<Boolean> sonarMyBatisSkipValue = config.getBoolean(SONAR_MYBATIS_SKIP);
        if (sonarMyBatisSkipValue.isPresent()) {
            sonarMyBatisSkipBooleanValue = sonarMyBatisSkipValue.get();
        }
        if (Boolean.TRUE.equals(sonarMyBatisSkipBooleanValue)) {
            LOGGER.info("MyBatis sensor is skiped.");
            return;
        }
        // analysis mybatis mapper files and generate issues
        FileSystem fs = context.fileSystem();
        fs.baseDir();
        Iterable<InputFile> xmlInputFiles = fs.inputFiles(fs.predicates().hasLanguage("xml"));
        List<String> mapperFiles = new ArrayList<>();
        for (InputFile xmlInputFile : xmlInputFiles) {
            String xmlFilePath = xmlInputFile.uri().getPath();
            mapperFiles.add(xmlFilePath);
        }
        XmlBatisSqlParser xmlBatisSqlParser = new XmlBatisSqlParser();
        List<XmlParseResult> results = xmlBatisSqlParser.parseXml(mapperFiles, null, "mysql");
        List<ErrorDataFromLinter> mybatisError = new ArrayList<>();
        for (XmlParseResult temp : results) {
            for (RuleCheckResult r : temp.getXmlNodeParserResult().getRuleCheckResults()) {
                ErrorDataFromLinter errorData = new ErrorDataFromLinter(r.getRuleId(), r.getObj(), temp.getMapperFilePath(), temp.getLineNumber());
                mybatisError.add(errorData);
            }
        }
        for (ErrorDataFromLinter err : mybatisError) {
            getResourceAndSaveIssue(err);
        }

    }

    private void getResourceAndSaveIssue(final ErrorDataFromLinter error) {
        LOGGER.debug(error.toString());

        final FileSystem fs = context.fileSystem();
        final InputFile inputFile = fs.inputFile(fs.predicates().hasAbsolutePath(error.getFilePath()));

        LOGGER.debug("inputFile null ? " + (inputFile == null));

        if (inputFile != null) {
            saveIssue(inputFile, error.getLine(), error.getType(), error.getDescription());
        } else {
            LOGGER.error("Not able to find a InputFile with " + error.getFilePath());
        }
    }

    private void saveIssue(final InputFile inputFile, int line, final String externalRuleKey, final String message) {
        RuleKey ruleKey = RuleKey.of(getRepositoryKeyForLanguage(), externalRuleKey);

        NewIssue newIssue = context.newIssue().forRule(ruleKey).gap(2.0);

        NewIssueLocation primaryLocation = newIssue.newLocation().on(inputFile).message(message);
        if (line > 0) {
            primaryLocation.at(inputFile.selectLine(line));
        }
        newIssue.at(primaryLocation);

        newIssue.save();
    }

    private static String getRepositoryKeyForLanguage() {
        return MyBatisLintRulesDefinition.REPO_KEY;
    }

    @Override
    public String toString() {
        return "MyBatisLintSensor";
    }

}
