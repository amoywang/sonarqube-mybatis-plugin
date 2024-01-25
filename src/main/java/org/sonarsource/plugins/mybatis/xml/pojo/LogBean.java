package org.sonarsource.plugins.mybatis.xml.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonarsource.plugins.mybatis.wang.util.DateUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class LogBean {
    private static final Logger log = LoggerFactory.getLogger(LogBean.class);
    private String logPath;
    private String detailLogFileName = "1.扫描汇总";
    private String errorSqlDetailFileName = "2.错误汇总";
    private String tableOperateFileName = "3.表操作汇总";
    private String containIfTestFileName = "存在if-test(insert_update)";
    private String containForUpdateFileName = "存在for-update-wait";
    private boolean appendTimestamp = true;
    private String appNam = "";

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public void setDetailLogFileName(String detailLogFileName) {
        this.detailLogFileName = detailLogFileName;
    }

    public void setErrorSqlDetailFileName(String errorSqlDetailFileName) {
        this.errorSqlDetailFileName = errorSqlDetailFileName;
    }

    public void setTableOperateFileName(String tableOperateFileName) {
        this.tableOperateFileName = tableOperateFileName;
    }

    public void setContainIfTestFileName(String containIfTestFileName) {
        this.containIfTestFileName = containIfTestFileName;
    }

    public void setContainForUpdateFileName(String containForUpdateFileName) {
        this.containForUpdateFileName = containForUpdateFileName;
    }

    public void setAppendTimestamp(boolean appendTimestamp) {
        this.appendTimestamp = appendTimestamp;
    }

    public void setAppNam(String appNam) {
        this.appNam = appNam;
    }

    public String getLogPath() {
        return this.logPath;
    }

    public String getDetailLogFileName() {
        return this.detailLogFileName;
    }

    public String getErrorSqlDetailFileName() {
        return this.errorSqlDetailFileName;
    }

    public String getTableOperateFileName() {
        return this.tableOperateFileName;
    }

    public boolean isAppendTimestamp() {
        return this.appendTimestamp;
    }

    public String getAppNam() {
        return this.appNam;
    }

    public LogBean(String resourcePath) {
        this.logPath = "";
        this.logPath = resourcePath + File.separator + "log";
    }

    public LogBean(Class<?> cls) {
        this.logPath = "";
        this.logPath = getCurResourcePath(cls) + File.separator + "log";
    }

    private String getCurResourcePath(Class<?> cls) {
        String currPath = "";
        try {
            currPath = URLDecoder.decode(cls.getResource("").getPath(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String prjPath = currPath.substring(0, currPath.indexOf("target"));
        return prjPath + "src" + File.separator + "main " + File.separator + " resources ";
    }

    public String getDetailLogFilePath() {
        String filePath = "";
        if (!this.detailLogFileName.isEmpty()) {
            filePath = this.logPath + File.separator + this.detailLogFileName + getSuffix();
        }
        return filePath;
    }

    public String getErrorSqlDetailFilePath() {
        String filePath = "";
        if (!this.errorSqlDetailFileName.isEmpty()) {
            filePath = this.logPath + File.separator + this.errorSqlDetailFileName + getSuffix();
        }
        return filePath;
    }

    public String getTableOperateFilePath() {
        String filePath = "";
        if (!this.tableOperateFileName.isEmpty()) {
            filePath = this.logPath + File.separator + this.tableOperateFileName + getSuffix();
        }
        return filePath;
    }

    public String getContainIfTestFileName() {
        String filePath = "";
        if (!this.containIfTestFileName.isEmpty()) {
            filePath = this.logPath + File.separator + this.containIfTestFileName + getSuffix();
        }
        return filePath;
    }

    public String getContainForUpdateFileName() {
        String filePath = "";
        if (!this.containForUpdateFileName.isEmpty()) {
            filePath = this.logPath + File.separator + this.containForUpdateFileName + getSuffix();
        }
        return filePath;
    }

    public String getSuffix() {
        return (this.appNam.isEmpty() ? "" : "_" + this.appNam + "_") + (this.appendTimestamp ? "-" + DateUtil.getYmdHmsSSS() : "") + ".txt";
    }
}
