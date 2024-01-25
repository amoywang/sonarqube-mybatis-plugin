package org.sonarsource.plugins.mybatis.wang.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.wang.enums.RuleCodeEnum;
import org.sonarsource.plugins.mybatis.wang.pojo.BaseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResult;
import org.sonarsource.plugins.mybatis.wang.pojo.ideaPlugin.XmlPluginRuleResultAll;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.exception.base.IException;
import org.sonarsource.plugins.mybatis.xml.pojo.LogBean;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;

import java.io.*;
import java.util.List;
import java.util.Map;

public enum LogUtil {
    ;
    
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void logAll(List<BaseResult> result, String logPath) throws Exception {
        String statusCode;
        if (result.isEmpty() || StringUtils.isBlank(logPath)) {
            return;
        }
        File logFile = new File(logPath);
        if (!logFile.getParentFile().exists()) {
            boolean mkdirFlag = logFile.getParentFile().mkdirs();
            if (!mkdirFlag) {
                throw new FileNotFoundException("创建文件目录失败:" + logFile.getParentFile());
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile));
            for (Object object : result) {
                if (!(object instanceof BaseResult)) {
                    logger.error("不支持类:{}", object.getClass());
                    throw new Exception("不支持该类型");
                } else if (object instanceof XmlParseResult) {
                    XmlParseResult xmlParseResult = (XmlParseResult) object;
                    XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
                    String statusCode2 = xmlNodeParserResult.getStatusCode();
                    String errorDetail = xmlNodeParserResult.getErrorMsg();
                    if (statusCode2.equals(ErrorCodeEnum.SUCCESS.getCode())) {
                        statusCode = "成功";
                    } else {
                        statusCode = "失败";
                    }
                    bw.append("MapperFileName="+xmlParseResult.getMapperName()+",line="+xmlParseResult.getLineNumber()+"\n");
                    bw.append((CharSequence) "[").append((CharSequence) statusCode).append((CharSequence) "]");
                    bw.append((CharSequence) "SQL ID:").append((CharSequence) xmlNodeParserResult.getSqlNodeId()).append((CharSequence) ", SQL语句: ").append((CharSequence) xmlNodeParserResult.getFormatSql());
                    bw.append("\n errorDetail: ").append(errorDetail);
                    bw.append((CharSequence) "\n");

                } else {
                    throw new RuntimeException("数据类型不正确");
                }
            }
            bw.close();
        } catch (IOException e) {
            logger.error("error:", e);
        }
    }

    public static void logErrorAllDetail(List<BaseResult> result, String logPath) throws Exception {
        if (result.isEmpty() || StringUtils.isBlank(logPath) || !isContainErrorSql(result)) {
            return;
        }
        File logFile = new File(logPath);
        if (!logFile.getParentFile().exists()) {
            boolean mkdirFlag = logFile.getParentFile().mkdirs();
            if (!mkdirFlag) {
                throw new FileNotFoundException("创建文件目录失败:" + logFile.getParentFile());
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(logFile));
        try {
            for (Object object : result) {
                if (!(object instanceof BaseResult)) {
                    logger.error("不支持类:{}", object.getClass());
                    throw new Exception("不支持该类型");
                }
                XmlParseResult xmlParseResult = (XmlParseResult) object;
                XmlNodeParserResult xmlNodeParserResult = xmlParseResult.getXmlNodeParserResult();
                boolean hasSuccess = ErrorCodeEnum.SUCCESS.getCode().equals(xmlNodeParserResult.getStatusCode());
                boolean isHaveEx = null != xmlNodeParserResult.getException();
                XmlNodeParserResult xmlNodeParserResult1 = xmlParseResult.getXmlNodeParserResult();
                if (!hasSuccess || isHaveEx) {
                    bw.append((CharSequence) "SQL ID:").append((CharSequence) xmlNodeParserResult1.getSqlNodeId()).append((CharSequence) "\n").append((CharSequence) "文件名:").append((CharSequence) xmlParseResult.getMapperName()).append((CharSequence) "\n").append((CharSequence) "文件路径:").append((CharSequence) xmlNodeParserResult1.getXmlFilePath()).append((CharSequence) "\n");
                    if (isHaveEx) {
                        IException iEx = xmlNodeParserResult1.getException();
                        String message = iEx.getMessage();
                        bw.append((CharSequence) message).append((CharSequence) "\n").append((CharSequence) "\n");
                    } else {
                        bw.append((CharSequence) "\n");
                    }
                }
            }
            bw.close();
        } catch (Throwable th) {
            try {
                bw.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static void logTableResult(Map<String, Map<String, Integer>> tableOptCountMap, String logPath) throws Exception {
        if (tableOptCountMap.isEmpty() || StringUtils.isBlank(logPath)) {
            return;
        }
        File logFile = new File(logPath);
        if (!logFile.getParentFile().exists()) {
            boolean mkdirFlag = logFile.getParentFile().mkdirs();
            if (!mkdirFlag) {
                throw new FileNotFoundException("创建文件目录失败:" + logFile.getParentFile());
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile));
            for (Map.Entry<String, Map<String, Integer>> entry : tableOptCountMap.entrySet()) {
                String tableName = entry.getKey();
                bw.append((CharSequence) "表名:").append((CharSequence) tableName).append((CharSequence) " 操作统计:");
                Map<String, Integer> optNameAndCountMap = entry.getValue();
                for (Map.Entry<String, Integer> optNameAndCount : optNameAndCountMap.entrySet()) {
                    String opt = optNameAndCount.getKey();
                    Integer count = optNameAndCount.getValue();
                    bw.append((CharSequence) opt).append((CharSequence) "[").append((CharSequence) String.valueOf(count)).append((CharSequence) "]").append((CharSequence) Constant.SPACE_CHAR);
                }
                bw.append((CharSequence) "\n");
            }
            bw.close();
        } catch (IOException e) {
            logger.error("error:", e);
        }
    }

    public static void logForRuleResult(XmlPluginRuleResultAll result, LogBean logBean) throws Exception {
        if (null == result || null == result.getRuleMap()) {
            return;
        }
        Map<RuleCodeEnum, List<XmlPluginRuleResult>> ruleMap = result.getRuleMap();
        for (Map.Entry<RuleCodeEnum, List<XmlPluginRuleResult>> entry : ruleMap.entrySet()) {
            RuleCodeEnum ruleCodeEnum = entry.getKey();
            List<XmlPluginRuleResult> list = entry.getValue();
            String code = ruleCodeEnum.name();
            String desc = ruleCodeEnum.getDesc();
            String filePath = logBean.getLogPath() + File.separator + code + "-" + desc + logBean.getSuffix();
            File logFile = new File(filePath);
            if (!logFile.getParentFile().exists()) {
                boolean mkdirFlag = logFile.getParentFile().mkdirs();
                if (!mkdirFlag) {
                    throw new FileNotFoundException("创建文件目录失败:" + logFile.getParentFile());
                }
            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(logFile));
                for (XmlPluginRuleResult xmlPluginRuleResult : list) {
                    bw.append((CharSequence) "[").append((CharSequence) xmlPluginRuleResult.getMapperName()).append((CharSequence) "]").append((CharSequence) "[").append((CharSequence) "SQL ID:").append((CharSequence) xmlPluginRuleResult.getSqlNodeId()).append((CharSequence) "]").append((CharSequence) "\n").append((CharSequence) "SQL:").append((CharSequence) xmlPluginRuleResult.getSqlText()).append((CharSequence) "\n").append((CharSequence) "Parse:").append((CharSequence) xmlPluginRuleResult.getParseResult()).append((CharSequence) "\n");
                }
                bw.close();
            } catch (IOException e) {
                logger.error("error:", e);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isContainErrorSql(List<org.sonarsource.plugins.mybatis.wang.pojo.BaseResult> r3) throws Exception {
        /*
            r0 = r3
            java.util.Iterator r0 = r0.iterator()
            r4 = r0
        L7:
            r0 = r4
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L51
            r0 = r4
            java.lang.Object r0 = r0.next()
            r5 = r0
            r0 = r5
            org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult r0 = (org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult) r0
            r6 = r0
            r0 = r6
            org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult r0 = r0.getXmlNodeParserResult()
            r7 = r0
            org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum r0 = org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum.SUCCESS
            java.lang.String r0 = r0.getCode()
            r1 = r7
            java.lang.String r1 = r1.getStatusCode()
            boolean r0 = r0.equals(r1)
            r8 = r0
            r0 = 0
            r1 = r7
            org.sonarsource.plugins.mybatis.xml.exception.base.BaseException r1 = r1.getException()
            if (r0 == r1) goto L3f
            r0 = 1
            goto L40
        L3f:
            r0 = 0
        L40:
            r9 = r0
            r0 = r8
            if (r0 == 0) goto L4c
            r0 = r9
            if (r0 == 0) goto L4e
        L4c:
            r0 = 1
            return r0
        L4e:
            goto L7
        L51:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.sonarsource.plugins.mybatis.wang.util.LogUtil.isContainErrorSql(java.util.List):boolean");
    }
}
