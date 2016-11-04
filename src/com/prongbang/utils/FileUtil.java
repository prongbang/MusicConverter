package com.prongbang.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author prongbang
 */
public class FileUtil {

    /**
     * Get current path
     *
     * @return String
     */
    public static String getCurrentPath() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

}
