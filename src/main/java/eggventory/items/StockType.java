package eggventory.items;

import eggventory.enums.StockProperty;

import java.util.ArrayList;

//@@author Deculsion
/**
 * Manages the list of Stocks within this StockType.
 */

public class StockType {
    private String name;
    private ArrayList<Stock> stocks;
    //private int quantity;
    //private boolean isUniqueStock;

    /**
     * Creates a new StockType object. This overload should only be called from a Storage class.
     * @param name A unique name identifying the StockType.
     * @param savedFile A fully constructed ArrayList of Stock objects.
     */
    public StockType(String name, ArrayList<Stock> savedFile) {
        this.name = name;
        stocks = savedFile;
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     * @param isUniqueStock true if the Stock objects are a UniqueStock.
     */
    public StockType(String name, boolean isUniqueStock) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    /**
     * Creates a new StockType object. StockType should only be instantiated from a StockList class.
     * @param name A unique name identifying the StockType.
     */
    public StockType(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    /**
     * Adds a Stock to the StockList.
     * @return True if item was added successfully.
     */
    public boolean addStock(String stockType, String stockCode, int quantity, String description) {
        stocks.add(new CollectiveStock(stockType, stockCode, quantity, description));
        return true;
    }

    /**
     * Deletes a Stock of the user's choice.
     *
     * @param stockCode The code of the Stock to be deleted.
     * @return true if some StockCode was found and the corresponding Stock removed. false if none were found.
     */
    public Stock deleteStock(String stockCode) {

        Stock deletedStock;

        for (Stock stock : stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                deletedStock = stock; //Not sure if this is a copy or not. Assumes unique stockCodes.
                stocks.remove(stock);
                return deletedStock;
            }
        }
        return null;
    }

    /**
     * Accesses and sets a new StockCode of a Stock.
     * @param oldStockCode The current StockCode referring to this Stock.
     * @param newStockCode The new StockCode to change to.
     */
    public void setStockCode(String oldStockCode, String newStockCode) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(oldStockCode)) {
                stock.setStockCode(newStockCode);
                return;
            }
        }
    }

    /**
     * Accesses and sets the Quantity of a Stock.
     * @param stockCode StockCode of the Stock to change
     * @param quantity New Quantity of the Stock to change
     */
    public void setStockQuantity(String stockCode, int quantity) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                stock.setQuantity(quantity);
                return;
            }
        }
    }

    /**
     * Accesses and sets the Description of a Stock.
     * @param stockCode StockCode of the Stock to change
     * @param description New Description of the Stock to change
     */
    public void setStockDescription(String stockCode, String description) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                stock.setDescription(description);
                return;
            }
        }
    }

    //@@author
    /**
     * Returns the entire StockList.
     * @return the StockList.
     */
    public ArrayList<Stock> getStockList() {
        return stocks;
    }

    /**
     * Returns a Stock of the user's choice.
     * @param i the index of the Stock selected.
     */
    public Stock getStock(int i) {
        return stocks.get(i);
    }

    /**
     * Returns a Stock of the user's choice.
     * @param stockCode String which uniquely identifies a Stock.
     * @return If Stock exits, the Stock otherwise null.
     */
    public Stock getStock(String stockCode) {
        for (Stock stock: stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                return stock;
            }
        }
        return null;
    }

    //@@author Deculsion
    /**
     * Gets the total number of Stocks.
     * @return the number of Stocks in this StockType.
     */
    public int getQuantity() {
        return stocks.size();
    }

    /**
     * Gets the name of this StockType.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the StockType.
     * @param newName String which uniquely identifies a StockType.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    //@@author
    /**
     * Updates the values of properties of a Stock.
     * @param stockCode String which uniquely identifies a Stock.
     * @param property The attribute of a Stock we want to update.
     * @param newValue The new value of the attribute to be updated.
     * @return The unedited Stock, for printing purpose.
     */
    public Stock setStock(String stockCode, StockProperty property, String newValue) {
        Stock uneditedStock;
        for (Stock stock: stocks) {
            if (stockCode.equals(stock.getStockCode())) {
                uneditedStock = stock;
                switch (property) {
                case STOCKCODE:
                    stock.setStockCode(newValue);
                    break;
                case QUANTITY:
                    stock.setQuantity(Integer.parseInt(newValue));
                    break;
                case LOANED:
                    stock.setLoaned(Integer.parseInt(newValue));
                    break;
                case LOST:
                    stock.setLost(Integer.parseInt(newValue));
                    break;
                case DESCRIPTION:
                    stock.setDescription(newValue);
                    break;
                case MINIMUM:
                    stock.setMinimum(Integer.parseInt(newValue));
                    break;
                default:
                }
                return uneditedStock;
            }
        }
        return null;
    }

    //@@author cyanoei
    /**
     * Determines if any of the Stocks in this StockType have the same StockCode.
     * @param stockCode the queried StockCode.
     * @return true if a Stock in this StockType has that StockCode and false if none of the Stocks have this StockCode.
     */
    public boolean isExistingStockCode(String stockCode) {
        for (Stock stock : stocks) {
            if (stock.getStockCode().equals(stockCode)) {
                return true;
            }
        }
        return false; //If none of the stocks had the same code.
    }

    //@@author Deculsion
    /**
     * A string of all the Stock objects within this StockType. Should only be called by Cli and StockList.
     * @return A string list of all the Stock objects and their details.
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        int i = 1;

        for (Stock stock : stocks) {
            ret.append(String.format("%d. ", i++)).append(stock.toString()).append("\n");
        }

        return ret.toString();

    }

    /**
     * Creates a String of all Stock objects under this StockType.
     * @return The String of all Stock objects.
     */
    public String saveDetailsString() {
        StringBuilder details = new StringBuilder();
        for (Stock stock : stocks) {
            details.append(stock.saveDetailsString()).append("\n");
        }
        return details.toString();
    }

    //@@author Raghav-B
    /**
     * Returns ArrayList of data of all Stocks within this StockType to be read
     * by GUI table.
     * @return ArrayList of data of Stocks in this StockType.
     */
    public ArrayList<ArrayList<String>> getDataAsArray() {
        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        for (Stock stock : stocks) {
            dataArray.add(stock.getDataAsArray());
        }
        return dataArray;
    }
    //@@author
}
