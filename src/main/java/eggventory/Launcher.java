package eggventory;

import eggventory.ui.Gui;
import javafx.application.Application;


public class Launcher {
    /**
     * Entry point for program that launches GUI
     */
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/data/saved_tasks.txt";

        Thread cliThread = new Thread(new Eggventory(filePath));
        cliThread.start();
        Application.launch(Gui.class, args);
    }
}
