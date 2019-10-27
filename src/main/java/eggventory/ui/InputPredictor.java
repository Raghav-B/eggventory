package eggventory.ui;

import eggventory.commands.CommandDictionary;
import eggventory.exceptions.BadInputException;
import java.util.ArrayList;

//@@author Raghav-B
public class InputPredictor {

    private CommandDictionary commandDictionary;
    // Stores all results that match the current prediction.
    private static ArrayList<String> curSearch;
    private int curSearchIndex = 0; // Used to iterate through curSearch
    private boolean isCommandFound = false;
    private String commandArguments;

    /**
     * Initialize current prediction for user's input String. Initializes the
     * curSearch ArrayList to store all matching results so that user can
     * iterate through the results.
     */
    public InputPredictor() {
        this.commandDictionary = new CommandDictionary();
        curSearch = new ArrayList<>();
    }

    public void reset() {
        curSearch = new ArrayList<>();
        curSearchIndex = 0;
        isCommandFound = false;
    }

    /**
     * Returns rest of String that matches prediction.
     * @param query User's input so far that will be used for prediction.
     * @param direction -1 = previous result.
     *                  0 = current result.
     *                  1 = next result.
     * @return Returns requested String that matches prediction.
     */
    public String getPrediction(String query, int direction) {
        String remainString = "";
        if (isCommandFound) {
            remainString = commandArguments;
        } else {
            remainString = getCommandPrediction(query, direction);
            //remainString = getArgumentPrediction(query, direction);

            //String[] queryArr = query.split(" ");
            //String[] remainStringArr = query.split(" ");
        }
        return remainString;
    }

    private String getCommandPrediction(String query, int direction) {
        String remainString = "";
        curSearch = commandDictionary.searchDictCommands(query);
        //if (curSearch.isEmpty()) {
        //    throw new BadInputException("No valid command could be found.");
        //}

        curSearchIndex = moduloIndex(direction);

        try {
            remainString = getRemainString(query, curSearch.get(curSearchIndex));
        } catch (IndexOutOfBoundsException e) {
            isCommandFound = true;
            curSearchIndex = 0;
            remainString = getArgumentPrediction(query, 0);
            commandArguments = remainString;
        }

        return remainString;
    }

    private String getArgumentPrediction(String command, int direction) {
        String returnString = "";
        curSearch = commandDictionary.searchDictArguments(command);
        // curSearch shouldn't be empty right now.

        curSearchIndex = moduloIndex(direction);

        return curSearch.get(curSearchIndex);
    }

    private int moduloIndex(int direction) {
        if (direction == -1) {
            curSearchIndex -= 1;
            if (curSearchIndex < 0) {
                curSearchIndex = curSearch.size() - 1;
            }
        }
        else if (direction == 1) {
            curSearchIndex += 1;
            if (curSearchIndex >= curSearch.size()) {
                curSearchIndex = 0;
            }
        }

        return curSearchIndex;
    }

    private String getRemainString(String query, String match) throws IndexOutOfBoundsException {
        String remainString = null;
        remainString = match.substring(query.length());
        return remainString;
    }
}
