package eggventory;

import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.parsers.Parser;
import eggventory.ui.Cli;
import eggventory.ui.Gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Eggventory is a task list that supports 3 types of classes - Todos, deadlines and events.
 * Tasks can be added, marked as done, searched for, and deleted.
 * Tasks are loaded from and saved to file.
 */

public class Eggventory extends Application {
    private static Storage storage;
    private static Parser parser;
    private static StockList stockList;
    private static Cli cli;
    private Gui gui;

    /**
     * Eggventory does somethings.
     * @param filePath which stores path of persistent storage
     */
    public Eggventory(String filePath) {
        storage = new Storage(filePath);
        stockList = storage.load(); //Will always return the right object even if empty.
        parser = new Parser();
        cli = new Cli();
        gui = new Gui();
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/Gui.fxml"));
            fxmlLoader.setController(this);
            Stage ap = fxmlLoader.load();
            ap.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void run() {
        cli.printIntro();
        String userInput;

        while (true) {
            userInput = cli.read();

            try {
                Command command = parser.parse(userInput);
                command.execute(stockList, cli, storage);

                if (command.getType() == CommandType.BYE) {
                    break;
                }
            } catch (Exception e) {
                cli.printError(e);
            }
        }
    }
}
