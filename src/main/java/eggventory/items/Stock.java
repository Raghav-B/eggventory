package eggventory.items;

import eggventory.loans.Loan;

import java.util.ArrayList;

/**
 * An abstract class representing a type of item that the lab keeps and is able to loan out.
 * Children classes are CollectiveStock and UniqueStock.
 * A Stock is first added with its StockType, StockCode, Description and Quantity.
 * Within a Stock, some of the items may be marked as 'on loan', or 'lost'.
 * TODO: Finish up the comments on this class after finalising the glossary.
 */
public class Stock {

    private String stockType;
    private String stockCode;
    protected int quantity;
    private String description;
    private int loaned;
    private int lost;
    private int minimum; //Minimum quantity the lab should maintain.
    //private int loanLimit; //Maximum quantity an individual can loan. To implement in the future.
    private ArrayList<Loan> stockLoans;

    /**
     * An Stock is first added with its StockType, StockCode, Description and Quantity.
     * By default the loaned and lost numbers are 0. They can be updated later.
     * By default the minimum Quantity is 0. This can be updated later.
     *
     * @param stockType The StockType the Stock belongs to.
     * @param stockCode The unique code that identifies the Stock. (eg. 500ohm resistors are called 'R500')
     * @param quantity The Quantity (number of items) of this Stock.
     * @param description The name of the Stock. (eg. 500ohm resistor, mini breadboard.
     */
    public Stock(String stockType, String stockCode, int quantity, String description) {
        this.stockType = stockType;
        this.stockCode = stockCode;
        this.quantity = quantity;
        this.description = description;
        this.loaned = 0;
        this.lost = 0;
        this.minimum = 0;
    }

    /**
     * Gets the StockType (category) of the Stock.
     * @return the StockType as a String.
     */
    public String getStockType() {
        return stockType;
    }

    //Should not allow updating of stockType for now (so no setter)

    /** Gets the StockCode.
     * @return the StockCode as a String.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Changes the StockCode (if there was an error in entry).
     * @param stockCode the new StockCode
     */
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    /**
     * Gets the name of the Stock.
     * @return the name (Description) of the Stock.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the name of the Stock.
     * @param description the name of the Stock.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    //Note: The access to the quantity attribute might have to be changed in the future.

    /**
     * Gets the total number of this Stock. Includes items lost and on loan.
     * @return the total Quantity of that Stock.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the new total number of this Stock. To be used by 'change' or 'qty' commands to modify the number.
     * @param newTotal the new total number of items.
     */
    protected void setQuantity(int newTotal) {
        this.quantity = newTotal;
    }

    /**
     * Gets the number of this Stock that is on loan.
     * @return loaned the number of loaned items.
     */
    public int getLoaned() {
        return loaned;
    }

    /**
     * Sets the number of this Stock on loan. To be used by the 'loan' command.
     * @param loaned the number of items on loan.
     */
    public void setLoaned(int loaned) {
        this.loaned = loaned;
    }

    /**
     * Gets the number of this Stock that is lost.
     * @return lost the number of lost items.
     */
    public int getLost() {
        return lost;
    }

    /**
     * Sets the number of this Stock that have been lost. To be used by the 'lost' command.
     * @param lost the number of items lost.
     */
    public void setLost(int lost) {
        this.lost = lost;
    }

    /**
     * Gets the minimum quantity of Stock that the lab wishes to maintain.
     * @return The minimum quantity.
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Updates the minimum quantity of Stock that the lab wishes to maintain. To be implemented in the future.
     * @param minimum The minimum quantity.
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * Calculates and returns the number of this Stock available to the lab (not lost, not on loan).
     * @return the number of available items.
     */
    public int numAvailable() {
        return (quantity - loaned - lost);
    }

    /**
     * Formats all Stock details appropriately for Cli output. Should only be called by Cli and StockType.
     * @return the Stock details string.
     */
    @Override
    public String toString() {
        return stockType + " | " + stockCode + " | " + quantity + " | " + description;
    }

    /**
     * Formats all Stock details appropriately to be saved to file.
     * @return the string to save.
     */
    public String saveDetailsString() {
        return stockType + "/" + stockCode + "/" + quantity + "/" + description + "/" + minimum;
    }

    //TODO: Fix methods below for new UI.print() implementation.

    /**
     * Prints the complete details of all the items of this type.
     * Format example: 560ohm Resistors: 280 available. 100 on loan. 20 lost. (400 total.)
     * To be used with the 'stock all' command.
     */
    public void printAll() {
        System.out.println(description + ": " + numAvailable() + " available. " + loaned + " on loan. "
                + lost + " lost. (" + quantity + " total.)");
    }

    /**
     * Prints the name and number of available items. Used as part of printing a list of available items.
     * Format example: 560ohm Resistors: 280
     * To be used with the 'stock' command.
     */
    public void printAvailable() {
        System.out.println(description + ": " + numAvailable() + " available.");
    }

    /**
     * Prints the name and number of items on loan. Used as part of printing a list of items on loan.
     * Format example: 560ohm Resistors: 100
     * To be used with the 'stock loan' command.
     */
    public void printLoan() {
        System.out.println(description + ": " + loaned + " on loan.");
    }

    /**
     * Prints the name and number of lost items. Used as part of printing a list of lost items.
     * Format example: 560ohm Resistors: 20
     * To be used with the 'stock lost' command.
     */
    public void printLost() {
        System.out.println(description + ": " + lost + " lost.");
    }

    //@@author Raghav-B
    /**
     * Returns data of Stock in format that can be read by GUI's TableView.
     * @return ArrayList containing data of Stock.
     */
    public ArrayList<String> getDataAsArray() {
        ArrayList<String> dataArray = new ArrayList<>();
        dataArray.add(stockType);
        dataArray.add(stockCode);
        dataArray.add(String.valueOf(quantity));
        dataArray.add(description);
        return dataArray;
    }
    //@@author
}
