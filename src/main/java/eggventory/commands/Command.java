package eggventory.commands;

import eggventory.ui.Ui;
import eggventory.StockList;
import eggventory.Storage;
import eggventory.enums.CommandType;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Collections;

/**
 * This is an abstract class.
 * Command objects are sent from the Parser and executed with StockType or Cli.
 * Commands include: add, delete, find, list.
 */
public abstract class Command {

    protected CommandType type;
    // Acts as a dictionary containing definitions of each command.
    public static SortedMap<String, ArrayList<ArrayList<String>>> commandNames = new TreeMap<>();

    public Command(CommandType type) {
        ArrayList<ArrayList<String>> addCommandNames = new ArrayList<>();
        addCommandNames.add(new ArrayList<>(Arrays.asList("stock", "<Stock Type>",
                "<Stock Code>", "<Quantity>", "<Description>")));
        addCommandNames.add(new ArrayList<>(Arrays.asList("stocktype", "<Stock Type>")));
        commandNames.put("add", addCommandNames);

        ArrayList<ArrayList<String>> deleteCommandNames = new ArrayList<>();
        deleteCommandNames.add(new ArrayList<>(Arrays.asList("stock", "<Stock Code>")));
        deleteCommandNames.add(new ArrayList<>(Arrays.asList("stocktype", "<Stock Type>")));
        deleteCommandNames.add(new ArrayList<>(Arrays.asList("template", "<Template Name>")));
        deleteCommandNames.add(new ArrayList<>(Arrays.asList("person", "<Matric No.>")));
        commandNames.put("delete", deleteCommandNames);

        ArrayList<ArrayList<String>> editCommandNames = new ArrayList<>();
        editCommandNames.add(new ArrayList<>(Arrays.asList("stock", "<Property>", "<New Value>")));
        editCommandNames.add(new ArrayList<>(Arrays.asList("person", "<Property>", "<New Value>")));
        commandNames.put("edit", editCommandNames);

        ArrayList<ArrayList<String>> listCommandNames = new ArrayList<>();
        listCommandNames.add(new ArrayList<>(Collections.singletonList("stock")));
        listCommandNames.add(new ArrayList<>(Collections.singletonList("stocktypes")));
        listCommandNames.add(new ArrayList<>(Arrays.asList("stocktype", "<Stock Type>")));
        listCommandNames.add(new ArrayList<>(Collections.singletonList("loan")));
        listCommandNames.add(new ArrayList<>(Collections.singletonList("template")));
        listCommandNames.add(new ArrayList<>(Collections.singletonList("lost")));
        commandNames.put("list", listCommandNames);

        ArrayList<ArrayList<String>> findCommandNames = new ArrayList<>();
        findCommandNames.add(new ArrayList<>(Arrays.asList("stock", "<Query>")));
        findCommandNames.add(new ArrayList<>(Arrays.asList("stocktype", "<Query>")));
        commandNames.put("find", findCommandNames);

        ArrayList<ArrayList<String>> loanCommandNames = new ArrayList<>();
        loanCommandNames.add(new ArrayList<>(Arrays.asList("add", "<Matric No.>", "<Stock Code>", "<Quantity>")));
        loanCommandNames.add(new ArrayList<>(Arrays.asList("add", "<Matric No.>", "<Template Name>")));
        loanCommandNames.add(new ArrayList<>(Arrays.asList("returned", "<Matric No.>", "<Stock Code>", "<Quantity>")));
        loanCommandNames.add(new ArrayList<>(Arrays.asList("returned", "<Matric No.>", "<Template Name>")));
        commandNames.put("loan", loanCommandNames);

        ArrayList<ArrayList<String>> lostCommandNames = new ArrayList<>();
        lostCommandNames.add(new ArrayList<>(Arrays.asList("<Stock Code>", "<Quantity>")));
        commandNames.put("lost", lostCommandNames);

        commandNames.put("undo", new ArrayList<>());

        commandNames.put("redo", new ArrayList<>());

        this.type = type;
    }

    public CommandType getType() {
        return type;
    }

    /**
     * Executes the command. Need to implement if inheriting from Command class.
     */
    public abstract String execute(StockList list, Ui ui, Storage storage);
}
