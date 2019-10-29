package eggventory.commands.add;

import eggventory.StockList;
import eggventory.Storage;
import eggventory.ui.Ui;
import eggventory.commands.Command;
import eggventory.enums.CommandType;

public class AddStockTypeCommand extends Command {
    private String name;

    /**
     * Creates a new StockType in a StorageList.
     * @param type The type of Command this is.
     * @param name Name of StockType to add.
     */
    public AddStockTypeCommand(CommandType type, String name) {
        super(type);
        this.name = name;
    }

    /**
     * Executes actual adding of StockType.
     * @param list StockType to add the Stock to.
     * @param ui Ui implementation to display output to.
     * @param storage Storage object to handle saving and loading of any data.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output;

        if (list.isExistingStockType(name)) {
            output = String.format("Sorry, \"%s\" is already an existing StockType.", name);

        } else {
            list.addStockType(name);
            output = String.format("Nice! I have successfully added the StockType: %s", name);
        }
        ui.print(output);
        // Drawing stock data in GUI table.
        ui.drawTable(list.getAllStockTypesStruct());
        return output;
    }
}
