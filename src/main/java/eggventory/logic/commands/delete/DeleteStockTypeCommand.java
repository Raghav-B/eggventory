package eggventory.logic.commands.delete;

import eggventory.ui.Ui;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.logic.commands.Command;
import eggventory.commons.enums.CommandType;
import eggventory.model.items.StockType;

//@@author cyanoei
public class DeleteStockTypeCommand extends Command {

    private String stockTypeName;

    public DeleteStockTypeCommand(CommandType type, String stockTypeName) {
        super(type);
        this.stockTypeName = stockTypeName;
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) {

        String output;

        if (stockTypeName.equals("Uncategorised")) {
            output = "Sorry, Uncategorised is the default category, and cannot be deleted.";
            ui.print(output);
            return output;
        }

        StockType deleted = list.deleteStockType(stockTypeName);

        if (deleted == null) {
            output = String.format("Sorry, I cannot find the stock type \"%s\" refers to. "
                    + "Please try again.", stockTypeName);
            ui.print(output);
        } else {
            output = String.format("I deleted the following stockType: %s. "
                    + "I also deleted the following stocks of that type: \n"
                    + deleted.toString(), stockTypeName);
            storage.save(list);
            ui.print(output);
            // Drawing stock data in GUI table.
            ui.drawTable(list.getAllStocksStruct());
            //ui.drawTable(list.getAllStockTypesStruct());
        }

        return output;
    }
}
