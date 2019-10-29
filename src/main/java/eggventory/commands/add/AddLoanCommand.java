package eggventory.commands.add;

import eggventory.LoanList;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.commands.Command;
import eggventory.enums.CommandType;
import eggventory.ui.Ui;

//@@author cyanoei
public class AddLoanCommand extends Command {

    private String stockCode;
    private String matricNo;
    private int quantity;

    /**
     * Constructor for AddLoanCommand.
     * @param type the type of Command.
     * @param stockCode the StockCode of the Loan to be added.
     * @param matricNo the MatricNo of the Person making the Loan.
     * @param quantity the Quantity loaned.
     */
    public AddLoanCommand(CommandType type, String stockCode, String matricNo, int quantity) {
        super(type);
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
    }

    /**
     * Method to execute the AddLoanCommand.
     * @param list the StockList.
     * @param ui the Ui for printing to Cli/Gui.
     * @param storage the storage.
     * @return the print string for assertion in testing.
     */
    public String execute(StockList list, Ui ui, Storage storage) {
        LoanList.addLoan(stockCode, matricNo, quantity);
        String output = (String.format("Nice, I have added this loan for you: \n"
                + "Stock: %s | Person: %s | Quantity: %d", stockCode, matricNo, quantity));

        ui.print(output);
        return output;
    }
}
