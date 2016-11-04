package com.prongbang.format;

import com.prongbang.main.MusicConverterJFrame;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author prongbang
 */
public class FileConverter implements Runnable {

    String command;

    public FileConverter(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger(MusicConverterJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
