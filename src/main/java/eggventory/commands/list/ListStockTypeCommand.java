package eggventory.commands.list;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.exceptions.BadInputException;
import eggventory.ui.Ui;

public class ListStockTypeCommand extends Command {
    private String query;

    public ListStockTypeCommand(CommandType type, String query) {
        super(type);
        this.query = query;
    }


    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        String output = "";

        if (query.equals("all")) { // list stocktype all command
            String listString = "";
            listString = list.toStocktypeString(); //Should contain all the stockTypes already, need not iterate.
            output = listString;
            ui.print(output);
            // Drawing stock data in GUI table.
            ui.drawTable(list.getAllStockTypesStruct());
        } else { // list stocktype <Stock Type> command
            String listString = "";
            listString = list.findStock(query);
            output = listString;

            if (listString.equals("")) {
                ui.print("Invalid command: No such StockType. list stock / list stocktype all / "
                        + "list stocktype <StockType>");
            } else {
                ui.print(output);
                // Drawing data on stocks under specific stocktype in GUI table.
                ui.drawTable(list.getAllStocksInStockTypeStruct(query));
            }
        }

        return output;
    }
}
