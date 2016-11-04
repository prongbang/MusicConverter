package com.prongbang.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author prongbang
 */
public class DialogUtil {
    
    public static void show(Component parrentComponent, Object message) {
        JOptionPane.showMessageDialog(parrentComponent, "Music Not Found!");
    }
    
}
