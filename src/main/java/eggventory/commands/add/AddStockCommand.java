package eggventory.commands.add;

import eggventory.StockList;
import eggventory.commands.Command;
import eggventory.ui.Ui;
import eggventory.Storage;
import eggventory.items.DateTime;
import eggventory.enums.CommandType;

/**
 * Command objects for adding Stocks.
 */
public class AddStockCommand extends Command {

    private String stockType;
    private String stockCode;
    private int quantity;
    private String description;
    private String details;
    private DateTime[] dateTimes = new DateTime[2];

    /**
     * Initialises all the attributes of the details of the Stock to be added.
     * @param type The type of Command.
     * @param stockType The StockType of the Stock.
     * @param stockCode The unique identifier code for the Stock.
     * @param quantity The total Quantity of the Stock.
     * @param description User input Description of the Stock to add.
     */
    public AddStockCommand(CommandType type, String stockType, String stockCode, int quantity, String description) {
        super(type);
        this.stockType = stockType;
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.description = description;
    }

    /**
     * Executes the actual adding of Stock to the StockType.
     * @param list StockType to add the Stock to.
     * @param ui Ui implementation to display output to.
     * @param storage Storage object to handle saving and loading of any data.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;

        if (list.isExistingStockCode(stockCode)) {
            output = String.format("Sorry, the StockCode \"%s\" is already assigned to a Stock in the system. "
                    + "Please enter a different StockCode.", stockCode);

        } else {
            list.addStock(stockType, stockCode, quantity, description);
            storage.save(list);
            output = String.format("Nice! I have successfully added the Stock: StockType: %s StockCode: %s "
                    + "Quantity: %d Description: %s", stockType, stockCode, quantity, description);
        }

        ui.print(output);
        // Drawing stock data in GUI table.
        ui.drawTable(list.getAllStocksStruct());
        return output;
    }

    /**
     * Executes the actual adding of a Stock to the StockType.
     * Only to be used by Storage.load() - handles the adding without showing UI output.
     * @param list StockType to add the Stock to.
     */
    public void execute(StockList list) {
        list.addStock(stockType, stockCode, quantity, description);
    }
}
