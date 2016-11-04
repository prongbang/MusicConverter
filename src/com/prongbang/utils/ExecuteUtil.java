package com.prongbang.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author prongbang
 */
public class ExecuteUtil {

    /**
     * Check Process
     *
     * @param processName
     * @return Boolean
     */
    public static Boolean checkProcess(String processName) {
        Boolean status = true;
        String line;
        String pidInfo = "";
        // String cmd = "tasklist | find \""+name+"\""; 
        String cmd = System.getenv("windir") + "\\system32\\" + "tasklist.exe";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                while ((line = input.readLine()) != null) {
                    pidInfo += line;
                }
                input.close();
            }

            if (pidInfo.contains(processName)) {
                status = false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

}
