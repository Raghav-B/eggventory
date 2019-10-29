package eggventory.commands.help;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import eggventory.ui.Ui;
import eggventory.Storage;
import eggventory.StockList;
import eggventory.enums.CommandType;
import eggventory.commands.Command;
import eggventory.enums.CommandType;

/**
 * Command object for all HelpCommand.
 */
public class HelpCommand extends Command {
    private String options;

    /**
     * Constructor for Command: "help".
     * @param type The type of the Command
     */
    public HelpCommand(CommandType type) {
        super(type);
        this.options = null;
    }

    /**
     * Constructor for command: "help xyz" where xyz are first parsed.
     * @param type The type of the Command
     * @param options The command syntax for which detailed help is required
     */
    public HelpCommand(CommandType type, String options) {
        super(type);
        this.options = options;
    }

    /**
     * Execute the help prompt given to user when user request for help.
     * @param list The inventory currently managed by the system
     * @param ui Ui implementation to display help to
     * @param storage Storage object which handles saving and loading of data
     * @return The string passed to Ui for parsing.
     */
    @Override
    public String execute(StockList list, Ui ui, Storage storage) {
        String output = "";
        if (this.options == null) {
            try {
                output = getStringFromFile("/help/Help.txt");
            } catch (IOException e) {
                ui.print("Error in reading Help.txt");
            }
        } else {
            switch (this.options) {
            case "add":
                try {
                    output = getStringFromFile("/help/HelpAdd.txt");
                } catch (IOException e) {
                    ui.print("Error in reading HelpAdd.txt");
                }
                break;
            case "edit":
                try {
                    output = getStringFromFile("/help/HelpEdit.txt");
                } catch (IOException e) {
                    ui.print("Error in reading HelpEdit.txt");
                }
                break;
            case "delete":
                try {
                    output = getStringFromFile("/help/HelpDelete.txt");
                } catch (IOException e) {
                    ui.print("Error in reading HelpDelete.txt");
                }
                break;
            default:
                ui.print("Your help command is not defined. Please enter 'help' for reference.");
            }
            ui.print(output);
        }
        return output;
    }

    /**
     * Reads help files from the resource folder of the JAR and returns the files as
     * a String.
     * @param resourcePath Path to file in resources folder.
     * @return String format of entire file.
     * @throws IOException when file cannot be read for some unknown error.
     */
    private String getStringFromFile(String resourcePath) throws IOException {
        InputStream is = getClass().getResourceAsStream(resourcePath);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }

            sb.append(line).append("\n");
        }

        br.close();
        isr.close();
        is.close();

        return sb.toString();
    }
}