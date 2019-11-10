package eggventory.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author Raghav-B
/**
 * Tests if input predictor returns correct predictions for incomplete
 * inputs.
 */
public class InputPredictorTest {
    private InputPredictor inputPredictor = new InputPredictor();

    @Test
    public void testGetPrediction_Command_Succeeds() {
        String incompleteInput = "ad";
        assertEquals(incompleteInput + inputPredictor.getPrediction(incompleteInput, 0),
                "add stock");

        incompleteInput = "b";
        assertEquals(incompleteInput + inputPredictor.getPrediction(incompleteInput, 0),
                "bye");

        incompleteInput = "add stockt";
        assertEquals(incompleteInput + inputPredictor.getPrediction(incompleteInput, 0),
                "add stocktype");
    }

    @Test
    public void testGetPrediction_Command_Fails() {
        String incompleteInput = "udfvjh";
        String remainString = inputPredictor.getPrediction(incompleteInput, 0);

        assertEquals(remainString, "");
    }

    @Test
    void testGetPrediction_Argument_Succeeds() {
        String incompleteInput = "add stock";
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                " <StockType> <StockCode> <Quantity> <Description> [Optional: -m <Minimum quantity>]");

        incompleteInput += " ";
        System.out.println(inputPredictor.getPrediction(incompleteInput, 0));
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                "<StockType> <StockCode> <Quantity> <Description> [Optional: -m <Minimum quantity>] ");

        incompleteInput += "Arduino 398SH ";
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                "<Quantity> <Description> [Optional: -m <Minimum quantity>] ");

        inputPredictor.reset();
        incompleteInput = "edit stock";
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                " <StockCode> <Property> <NewValue>");

        incompleteInput += " Arduino";
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                " <Property> <NewValue> ");

        incompleteInput += " StockCode SH983";
        assertEquals(inputPredictor.getPrediction(incompleteInput, 0),
                " ");
    }
}
//@@author
