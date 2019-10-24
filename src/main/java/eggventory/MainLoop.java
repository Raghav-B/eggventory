package eggventory;

import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.ui.Ui;

public class MainLoop implements Runnable {
    @Override
    public void run() {
        try {
            String userInput = ui.read();

            Command command = parser.parse(userInput);
            command.execute(stockList, ui, storage);

            if (command.getType() == CommandType.BYE) {
                System.exit(0);
            }
        } catch (Exception e) {
            ui.printError(e);
        }
    }

    public String dick = "dick";
}
