package com.prongbang.format;

import com.prongbang.utils.ExecuteUtil;
import com.prongbang.utils.FileUtil;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author prongbang
 */
public class Converter {

    private static Converter formatter;
    private static DefaultTableModel tableModel;
    private int[] indexStatus;
    private Thread[] thread;
    private int limitThread = 0;
    private int indexThread = 0;

    public static Converter getInstance(DefaultTableModel tableModel) {
        Converter.tableModel = tableModel;
        if (formatter == null) {
            formatter = new Converter();
        }
        return formatter;
    }

    /**
     * Converter
     *
     * @param playlists
     * @param from
     * @param to
     */
    public void formatter(List<File> playlists, String from, String to) {

        // Preprocessing Thread
        indexThread = 0;
        limitThread = playlists.size();
        indexStatus = new int[playlists.size()];
        thread = new Thread[playlists.size()];
        FileConverter[] formatters = new FileConverter[playlists.size()];
        int index = 0;

        for (File f : playlists) {
            String mpFrom = f.getAbsolutePath();
            String mpTo = mpFrom.replace(from, to);
            String delMpFrom = "del \"" + mpFrom + "\"";
            String exit = "exit";
            String toMpTo = "ffmpeg\\ffmpeg -i \"" + mpFrom + "\" -q:a 0 -map a \"" + mpTo + "\"";
            String options = toMpTo + " & " + delMpFrom + " & " + exit;
            String command = "cmd /c cd \"" + FileUtil.getCurrentPath() + "\" & start cmd /k \"" + options + "\"";
            formatters[index] = new FileConverter(command);
            thread[index] = new Thread(formatters[index]);
            indexStatus[index] = index;
            index++;
        }

        // formatter intecepter
        int sec = 1;
        final Timer timerIntecepter = new Timer();
        timerIntecepter.schedule(new TimerTask() {
            @Override
            public void run() {
                Boolean statusDownload = ExecuteUtil.checkProcess("ffmpeg.exe");
                if (statusDownload) {
                    if (indexThread < limitThread) {
                        tableModel.setValueAt("Formatting", indexStatus[indexThread], 2);
                        thread[indexThread].start();
                    }
                    if (indexThread > 0 && indexThread <= limitThread) {
                        tableModel.setValueAt("Completed", indexStatus[indexThread - 1], 2);
                        thread[indexThread - 1].stop();
                    }
                    if (limitThread <= indexThread) {
                        timerIntecepter.cancel();
                    }
                    indexThread++;
                }
            }
        }, 0, (sec * 1000));

    }

}
