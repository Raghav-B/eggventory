package eggventory.commands.delete;

import eggventory.LoanList;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.exceptions.BadInputException;
import eggventory.ui.Ui;

//@@author cyanoei
public class DeleteLoanCommand extends Command {

    private String stockCode;
    private String matricNo;
    private int quantity;

    /**
     * Constructor for DeleteLoanCommand.
     * @param type the type of Command.
     * @param stockCode the StockCode of the Stock in the Loan being deleted.
     * @param matricNo the MatricNo of the Person whose Loan is being deleted.
     */
    public DeleteLoanCommand(CommandType type, String stockCode, String matricNo) {
        super(type);
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = LoanList.getLoanQuantity(stockCode, matricNo);
    }

    @Override
    public String execute(StockList list, Ui ui, Storage storage) throws BadInputException {
        boolean removeSuccess = LoanList.deleteLoan(stockCode, matricNo);

        String output = "";

        if (!removeSuccess) {
            throw new BadInputException(String.format("Sorry, there is no loan of %s to %s. ", stockCode, matricNo));

        } else {
            output = (String.format("Nice, I have deleted this loan for you: \n"
                    + "Stock: %s | Person: %s | Quantity: %d", stockCode, matricNo, quantity));
        }

        ui.print(output);
        return output;
    }
}
