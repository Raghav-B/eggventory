package eggventory.logic.commands;

import eggventory.commons.enums.CommandType;
import eggventory.commons.exceptions.BadInputException;
import eggventory.model.StockList;
import eggventory.storage.Storage;
import eggventory.ui.Ui;

public class UndoCommand extends Command {

    public UndoCommand(CommandType type) {
        super(type);
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        ui.clearTable();
        return null;
    }
}
