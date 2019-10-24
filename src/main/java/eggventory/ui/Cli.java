package eggventory.ui;

import java.util.Scanner;

/**
 * Manages the UI of Eggventory.
 * Prints intro and exit messages, and the standard newline.
 */

public class Cli implements Ui {

    private Scanner in;

    public Cli() {
        this.in = new Scanner(System.in);
    }

    public void initialize(Runnable runMethod) {
        printIntro();

        while (true) {
            runMethod.run();
        }
    }

    public String read() {
        return in.nextLine();
    }

    /**
     * Primary function that handles printing to the CLI.
     *
     * @param printString String to print (passed in from external objects accessing UI)
     */
    public String print(String printString) {
        String output = printFormatter(printString);
        System.out.print(output);

        return output;
    }

    //    public PrintType printCommand(PrintType printType, String ... statement) {
    //        String output;
    //        switch(printType){
    //            case SUCCESS_ADD_COMMAND:
    //                output = (addIndent() + "Nice! I have successfully added the stock: StockType: " + statement[0]);
    //                System.out.println(output);
    //                return PrintType.SUCCESS_ADD_COMMAND;
    //            break;
    //            case FAIL_ADD_COMMAND:
    //                output = (addIndent() + "Sorry! There seems to be an error: StockType" + statement[0] );
    //                System.out.println(output);
    //                break;
    //            default:
    //                output = "Nothing done";
    //        }
    //    }
}
