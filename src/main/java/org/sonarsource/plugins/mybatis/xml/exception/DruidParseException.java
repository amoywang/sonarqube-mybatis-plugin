package org.sonarsource.plugins.mybatis.xml.exception;


import org.sonarsource.plugins.mybatis.xml.exception.base.BaseException;

public class DruidParseException extends BaseException {
    private static final long serialVersionUID = 189796715157251L;
    private final Exception ex;
    private String msg;
    private Integer code;

    public DruidParseException(Exception ex) {
        this.ex = ex;
    }

    public DruidParseException(String msg, Exception ex) {
        this.msg = msg;
        this.ex = ex;
    }

    public Exception getEx() {
        return this.ex;
    }

    @Override // java.lang.Throwable, org.sonarsource.plugins.mybatis.xml.exception.base.IException
    public String getMessage() {
        if (null != this.ex) {
            return this.ex.getMessage();
        }
        return this.msg;
    }

    @Override // java.lang.Throwable, org.sonarsource.plugins.mybatis.xml.exception.base.IException
    public synchronized Throwable getCause() {
        if (null != this.ex) {
            return this.ex.getCause();
        }
        return null;
    }
}
