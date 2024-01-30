package org.sonarsource.plugins.mybatis.xml.exception.base;

public interface IException {
    String getMessage();

    Throwable getCause();
}
