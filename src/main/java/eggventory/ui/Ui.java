//@@author Raghav-B
package eggventory.ui;

public interface Ui {

    void initialize(Runnable runMethod);

    void mainLoop();

    String read();

    String print(String printString);

    default String printFormatter(String printString) {
        String output = addIndent() + addLine() + "\n";

        String[] linesToPrint = printString.split("\n", 0);
        for (int i = 0; i < linesToPrint.length; i++) {
            output += (addIndent() + linesToPrint[i]) + "\n";
        }
        output += addIndent() + addLine() + "\n";
        return output;
    }

    /**
     * Prints eggventory introduction message.
     */
    default void printIntro() {
        String logo = "  _      __    __                     __         ____         _   __         __               \n"
                + " | | /| / /__ / /______  __ _  ___   / /____    / __/__ ____ | | / /__ ___  / /____  ______ __\n"
                + " | |/ |/ / -_) / __/ _ \\/  ' \\/ -_) / __/ _ \\  / _// _ `/ _ `/ |/ / -_) _ \\/ __/ _ \\/"
                + " __/ // /\n"
                + " |__/|__/\\__/_/\\__/\\___/_/_/_/\\__/  \\__/\\___/ /___/\\_, /\\_, /|___/\\__/_//_/\\__/\\___/_/"
                + "  \\_, / \n"
                + "                                                  /___//___/                           /___/  \n";

        print(logo);
        print("Hello! I'm Humpty Dumpty\n" + "What can I do for you?");
    }

    /**
     * Prints error message to CLI.
     */
    default void printError(Exception e) {
        print("Parser error: \n" + e);
    }

    /**
     * Prints the EggVentory exit message.
     */
    default void printExitMessage() {
        print("Bye! Your stonks are safe with me!");
    }

    /**
     * Adds indent for formatting Eggventory text output.
     * @return Returns String with indentation.
     */
    default String addIndent() {
        return "        ";
    }

    /**
     * Prints the standard newline.
     * @return Returns String with newline.
     */
    default String addLine() {
        return "____________________________________________________________";
    }
}
