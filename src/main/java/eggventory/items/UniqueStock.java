package eggventory.items;

import java.util.ArrayList;

/**
 * A child of the Stock class.
 * A UniqueStock is a group of many items (eg. Arduino Uno) that share the same StockCode, Description etc.,
 * but are also tracked individually (eg. Arduino Uno #31).
 * UniqueStock contains and manages a list (uniqueStockList) of its individual items.
 */
public class UniqueStock extends Stock {

    private ArrayList<Item> uniqueStockList = new ArrayList<>();

    /**
     * A Stock is first added with its StockType, StockCode, Description and Quantity.
     * For UniqueStock, the Quantity determines the number of Item objects created.
     * By default, the index numbers of each UniqueStock are set as 1-quantity for now.
     * By default, the loaned and lost numbers are 0.
     *
     * @param stockType   The StockType the Stock belongs to.
     * @param stockCode   The unique code that identifies the Stock. (eg. 500ohm resistors are called 'R500')
     * @param quantity    The Quantity (number of items) of this Stock.
     * @param description The name of the Stock. (eg. 500ohm resistor, mini breadboard)
     */
    public UniqueStock(String stockType, String stockCode, int quantity, String description) {
        super(stockType, stockCode, quantity, description);
        for (int i = 0; i < quantity; i++) {
            uniqueStockList.add(new Item(i + 1));
        }
    }

    /**
     * Adds multiple unique items of this Stock.
     * @param addQuantity The number of unique Stocks being added.
     */
    public void addUniqueMultiple(int addQuantity) {
        int oldTotal = uniqueStockList.size();

        for (int i = 0; i < addQuantity; i++) {
            uniqueStockList.add(new Item(oldTotal + i));
        }

        super.setQuantity(oldTotal + addQuantity); //Updates the quantity
    }

    /**
     * Adds one item of a UniqueStock to the list.
     * @param index The index number assigned to the item.
     */
    public void addUnique(int index) {
        uniqueStockList.add(new Item(index));
        super.setQuantity(super.getQuantity() + 1); //Increment quantity.
    }

    /**
     * Removes one item of a UniqueStock from the list.
     * @param index The index number assigned to the item.
     */
    public void deleteUnique(int index) {
        int size = uniqueStockList.size();

        int targetItem = -1;
        for (int i = 0; i < size; i++) {
            if (uniqueStockList.get(i).getIndex() == index) {
                targetItem = i;
            }
        }

        if (targetItem == -1) {
            System.out.println("Sorry, that is not a valid index."); //Do something better than this. 
        } else {
            uniqueStockList.remove(targetItem);
        }

        super.setQuantity(super.getQuantity() - 1); //Decrement quantity.
    }

    //Note: toString works as per normal, treating UniqueStock as a CollectiveStock.

    //TODO: Provide a 'detailed' version of the print string to show individual items.
    public void printUniqueStocks() {
       /*
           A header with all the usual info, maybe using toString.
           return getStockType() + "/" + getStockCode() + "/" + getQuantity() +
                    "/" + getDescription() + "/" + getMinimum();

           A list of all the items and their statuses. Using a for loop.
           for (int i = 0; i < uniqueStockList.size(); i++) {
               System.out.println(uniqueStockList.get(i).toString());
           }
        */
    }

    //TODO: Update both print and save method in Item class, and iterate through all of them here.
    //  Should be similar to StockType printing methods.
    /**
     * Formats all Stock details appropriately to be saved to file.
     * @return the string to save.
     */
    @Override
    public String saveDetailsString() {
        return getStockType() + "/" + getStockCode() + "/" + getQuantity()
                + "/" + getDescription() + "/" + getMinimum();
    }

    /*
        way to access individual stock
        get/set id
        add stock
            update total quantity ++
        update loan/lost status
            update total loan/lost by +/-
        delete stock
            update total quantity --
            check if min, give warning.
     */
}
