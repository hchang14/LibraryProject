package ui.gui;

import ui.console.LibraryManagementConsoleApp;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;


public class Main {

    static {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: run the GUI application of the book management APP
    public static void main(String[] args) {
        Thread consoleAppThread = new Thread(() -> {
            new LibraryManagementConsoleApp();
        });
        consoleAppThread.start();

        EventQueue.invokeLater(() -> {
            try {
                StartPage startPage = new StartPage();
                startPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
