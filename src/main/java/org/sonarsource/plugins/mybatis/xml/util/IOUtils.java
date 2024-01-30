package org.sonarsource.plugins.mybatis.xml.util;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;

public class IOUtils {

    private static final Logger LOGGER = Loggers.get(IOUtils.class);

    private IOUtils() {
        super();
    }

    /**
     * Search file, get the line number which matches the keyword firstly
     *
     * @param filePath
     * @param stmtId
     * @return
     */
    public static int getLineNumber(final String filePath, final String stmtId) {
        int lineNumber = 1;
        final String stmtIdAuxDoublequote = stmtId + "\"";
        final String stmtIdAuxSimplequote = stmtId + "'";
        LOGGER.debug("filePath: " + filePath + " stmtId: ");
        try (LineNumberReader lineNumberReader = new LineNumberReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String readLine = null;
            while ((readLine = lineNumberReader.readLine()) != null) {
                // check if contains
                if ((readLine.contains(stmtIdAuxDoublequote) || readLine.contains(stmtIdAuxSimplequote))) {
                    lineNumber = lineNumberReader.getLineNumber();
                    break;
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
        LOGGER.debug("lineNumber found: " + lineNumber);
        if (0 == lineNumber) {
            LOGGER.warn("dosnt found line number.");
        }
        return lineNumber;
    }
}
