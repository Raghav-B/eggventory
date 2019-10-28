package eggventory.parsers;

import eggventory.commands.Command;
import eggventory.commands.list.ListStockCommand;
import eggventory.commands.list.ListStockTypeCommand;
import eggventory.enums.CommandType;
import eggventory.exceptions.BadInputException;

public class ParseList {

    private Command processListStock(String input) throws BadInputException {
        String[] inputArr = input.split(" ");
        if (inputArr.length > 1) { // Checking if anything extraneous is after stock
            throw new BadInputException("Usage of list: 'list stock', 'list stocktype all' or "
                    + "'list stocktype <Stock Type>'");
        }
        return new ListStockCommand(CommandType.LIST);
    }

    private Command processListStockType(String input) throws BadInputException {
        String[] inputArr = input.split(" ");
        if (inputArr.length > 2) { // Checking for extra arguments
            throw new BadInputException("Usage of list: 'list stock', 'list stocktype all' or "
                    + "'list stocktype <Stock Type>'");
        }
        return new ListStockTypeCommand(CommandType.LIST, inputArr[0]);
    }

    public Command parse(String input) throws BadInputException {
        String[] inputArr = input.split(" ", 2);

        Command listCommand;

        switch(inputArr[0]) {
        case "stock":
            listCommand = processListStock(inputArr[0]);
            break;

        case "stocktype":
            listCommand = processListStockType(inputArr[1]);
            break;

        default:
            throw new BadInputException("Usage of list: 'list stock', 'list stocktype all' or "
                    + "'list stocktype <Stock Type>'");
        }

        return listCommand;
    }
}
