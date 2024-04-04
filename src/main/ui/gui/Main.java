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

        // 在EDT（事件调度线程）中启动GUI应用
        EventQueue.invokeLater(() -> {
            try {
                StartPage startPage = new StartPage(); // 假设您有一个启动页面叫做StartPage
                startPage.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

