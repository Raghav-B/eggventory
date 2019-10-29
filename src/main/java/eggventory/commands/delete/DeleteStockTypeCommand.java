package eggventory.commands.delete;

import eggventory.ui.Ui;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.items.StockType;

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
            output = "Sorry, Uncategorised is the default StockType, and cannot be deleted.";
            ui.print(output);
            return output;
        }

        StockType deleted = list.deleteStockType(stockTypeName);

        if (deleted == null) {
            output = String.format("Sorry, I cannot find the Stock that StockType \"%s\" refers to. "
                    + "Please try again.", stockTypeName);
            ui.print(output);
        } else {
            output = String.format("I deleted the following StockType: %s. "
                    + "I also deleted the following Stock of that StockType: \n"
                    + deleted.toString(), stockTypeName);
            storage.save(list);
            ui.print(output);
            // Drawing stock data in GUI table.
            ui.drawTable(list.getAllStocksStruct());
        }

        return output;
    }
}
