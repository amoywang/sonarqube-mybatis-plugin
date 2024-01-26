package org.sonarsource.plugins.mybatis.wang.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.wang.pojo.BaseResult;
import org.sonarsource.plugins.mybatis.wang.pojo.XmlParseResult;
import org.sonarsource.plugins.mybatis.xml.consts.Constant;
import org.sonarsource.plugins.mybatis.xml.consts.ErrorCodeEnum;
import org.sonarsource.plugins.mybatis.xml.exception.base.IException;
import org.sonarsource.plugins.mybatis.xml.pojo.XmlNodeParserResult;

import java.io.*;
import java.util.Iterator;
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
                    bw.append("MapperFileName=" + xmlParseResult.getMapperName() + ",line=" + xmlParseResult.getLineNumber() + "\n");
                    bw.append("[").append(statusCode).append("]");
                    bw.append("SQL ID:").append(xmlNodeParserResult.getSqlNodeId()).append(", SQL语句: ").append(xmlNodeParserResult.getFormatSql());
                    bw.append("\n errorDetail: ").append(errorDetail);
                    bw.append("\n");

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
                    bw.append("SQL ID:").append(xmlNodeParserResult1.getSqlNodeId()).append("\n").append("文件名:").append(xmlParseResult.getMapperName()).append("\n").append("文件路径:").append(xmlNodeParserResult1.getXmlFilePath()).append("\n");
                    if (isHaveEx) {
                        IException iEx = xmlNodeParserResult1.getException();
                        String message = iEx.getMessage();
                        bw.append(message).append("\n").append("\n");
                    } else {
                        bw.append("\n");
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
                bw.append("表名:").append(tableName).append(" 操作统计:");
                Map<String, Integer> optNameAndCountMap = entry.getValue();
                for (Map.Entry<String, Integer> optNameAndCount : optNameAndCountMap.entrySet()) {
                    String opt = optNameAndCount.getKey();
                    Integer count = optNameAndCount.getValue();
                    bw.append(opt).append("[").append(String.valueOf(count)).append("]").append(Constant.SPACE_CHAR);
                }
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            logger.error("error:", e);
        }
    }

    private static boolean isContainErrorSql(List<BaseResult> results) {
        Iterator<BaseResult> iterator = results.iterator();
        while (iterator.hasNext()) {
            BaseResult temp = iterator.next();
            if (temp instanceof XmlParseResult) {
                XmlParseResult result = (XmlParseResult) temp;
                String parseResultStatusCode = result.getXmlNodeParserResult().getStatusCode();
                if (!ErrorCodeEnum.SUCCESS.getCode().equals(parseResultStatusCode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
