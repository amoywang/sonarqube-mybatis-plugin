package org.sonarsource.plugins.mybatis.wang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public enum FileUtil {
    ;
    
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void getFileList(String filePath, String suffix, List<String> filelist) {
        File root = new File(filePath);
        if (root.isDirectory() && root.getName().contains(".idea")) {
            return;
        }
        if (root.isDirectory() && root.getName().contains("target")) {
            return;
        }
        if (!root.isDirectory() && root.getName().contains(suffix)) {
            filelist.add(root.getAbsolutePath());
            return;
        }
        File[] files = root.listFiles();
        if (null != files) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getFileList(file.getAbsolutePath(), suffix, filelist);
                } else if (file.getName().contains(suffix)) {
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
    }
}
