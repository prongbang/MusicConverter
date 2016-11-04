package com.prongbang.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author prongbang
 */
public class MusicUtil {

    /**
     * Get music from format name
     * @param path
     * @param fromFormat
     * @return List
     */
    public static List<File> getPlaylist(String path, String fromFormat) {

        List<File> playlist = new ArrayList<>();
        
        for(File f : new File(path).listFiles()) {
            if (f.isFile() && f.getName().contains("." + fromFormat)) {
                playlist.add(f);
            }
        }
        
        return playlist;
    }
    
}
