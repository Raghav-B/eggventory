package eggventory.commands.delete;

import eggventory.ui.Ui;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.items.Stock;

/**
 * Command objects for deleting Stocks.
 * Requires the index (as listed by the system) of the Stock. //TODO: Change this to the StockCode.
 */
public class DeleteStockCommand extends Command {

    private String stockCode;

    public DeleteStockCommand(CommandType type, String stockCode) {
        super(type);
        this.stockCode = stockCode;
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) {

        Stock deleted = list.deleteStock(stockCode);
        String output;
        if (deleted == null) {
            output = String.format("Sorry, I cannot find the Stock that StockCode \"%s\" refers to. "
                    + "Please try again.", stockCode);
            ui.print(output);
            return output;
        } else {
            output = String.format("I deleted the following Stock: StockType: %s StockCode: %s Quantity: %d "
                    + "Description: %s", deleted.getStockType(), stockCode,
                    deleted.getQuantity(), deleted.getDescription());
            storage.save(list);
            ui.print(output);
            // Drawing stock data in GUI table.
            ui.drawTable(list.getAllStocksStruct());
            return output;
        }
    }
}
