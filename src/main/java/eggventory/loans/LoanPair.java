package eggventory.loans;

//@@author cyanoei
/**
 * Data structure to store the pairs of Person and Stock, used as the key for accessing the relevant Loan.
 * For ease of implementation, LoanPairs' two attributes should never be modified.
 */
public class LoanPair {
    private String stockCode;
    private String matricNo;

    public LoanPair(String stockCode, String matricNo) {
        this.stockCode = stockCode;
        this.matricNo = matricNo;
    }

    /**
     * Gets the StockCode of this pair.
     * @return the StockCode.
     */
    public String getStockCode() {
        return stockCode;
    }

    /**
     * Gets the MatricNo of this pair.
     * @return the MatricNo.
     */
    public String getMatricNo() {
        return matricNo;
    }

    /**
     * Custom .equals method to compare two LoanPairs.
     * @param other the LoanPair to compare to.
     * @return true if their StockCode and MatricNo are the same, false otherwise.
     */
    public boolean equals(LoanPair other) {
        return this.stockCode.equals(other.getStockCode()) && this.matricNo.equals(other.getMatricNo());
    }
}
