package eggventory.loans;

import java.util.Calendar;

//@@author cyanoei
public class Loan {

    private String stockCode;
    private String matricNo;
    private int quantity;
    private Calendar loanDate;
    private Calendar returnDate;

    /**
     * Temporary shorter constructor used to test the non-date attributes.
     * @param stockCode the StockCode of the Stock being loaned.
     * @param matricNo the MatricNo of the Person making the Loan.
     * @param quantity the Quantity being loaned out.
     */
    public Loan(String stockCode, String matricNo, int quantity) {
        //AddLoanCommand should have determined beforehand that the Person and Stock being referred to
        //are existing entries.
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
    }

    /**
     * Constructor for Loan class.
     * @param stockCode the StockCode of the Stock being loaned.
     * @param matricNo the MatricNo of the Person making the Loan.
     * @param quantity the Quantity being loaned out.
     * @param loanDate the date the Loan was processed.
     * @param returnDate the date the loans have to be returned by.
     */
    public Loan(String stockCode, String matricNo, int quantity, Calendar loanDate, Calendar returnDate) {
        //AddLoanCommand should have determined beforehand that the Person and Stock being referred to
        //are existing entries.
        this.stockCode = stockCode;
        this.matricNo = matricNo;
        this.quantity = quantity;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    /**
     * Gets the StockCode of the Stock being loaned.
     * @return the StockCode.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Gets the MatricNo of the Person loaning the Stock.
     * @return the MatricNo.
     */
    public String getMatricNo() {
        return matricNo;
    }

    /**
     * Gets the Quantity of Stock that this Person loaned.
     * @return the Quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the new Quantity of the Stock loaned.
     * @param quantity the new amount that is loaned.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Formats the details of this loan in a user-friendly print statement.
     * @return the string containing the details.
     */
    @Override
    public String toString() {
        return getMatricNo() + " loaned " + getQuantity() + " of " + getStockCode() + ".";
    }

    //@@author


}

