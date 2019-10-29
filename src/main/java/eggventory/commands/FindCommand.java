package eggventory.commands;

import eggventory.ui.Ui;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.enums.CommandType;

/**
 * Command objects for searching for Stocks by //TODO SPLIT INTO DIFFERENT PROPERTIES WE CAN FIND BY.
 */
public class FindCommand extends Command {
    private String search;

    public FindCommand(CommandType type, String search) {
        super(type);
        this.search = search;
    }

    /**
     * Allows the user to search for Stock Descriptions that match a given string.
     * Prints the list of Stocks that match. Alternatively prints a message if none are found.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;
        //int max = list.getQuantity(); Note by Rebs: changed variable name to be more specific.
        int max = list.getStockQuantity();
        boolean found = false;

        String listString = "";
        for (int i = 0; i < max; i++) {
            /*
            if (list.getStock(i).getDescription().contains(search)) { //Only search the description.
                // Adding task to print with associated index to final string
                listString += (i + 1 + ". " + list.toString() + "\n");
                found = true;
            }
            */
        }

        if (!found) {
            output = "Sorry, I could not find any Stocks containing the Description \""
                    + search + "\".\nPlease try a different search string.";
            ui.print(output);
        } else {
            output = listString;
            ui.print(output);
        }
        return output;
    }
}

