package eggventory.ui;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.util.ArrayList;

public class PredictionTextBox {

    // Uses one Text object per word

    private TextFlow textFlow;
    private ArrayList<Text> textList;
    private int curWordIndex;

    public PredictionTextBox(TextFlow textFlow) {
        this.textFlow = textFlow;
        textList = new ArrayList<>();
        textList.add(new Text(""));
        curWordIndex = 0;
    }

    // TEST METHOD
    public int getArrSize() {
        return textList.size();
    }

    private void updateTextFlow() {
        textFlow.getChildren().removeAll(textList);
        textFlow.getChildren().addAll(textList);
    }

    // Triggers when user presses the spacebar
    public void appendWord() {
        appendToWord(" ");
        textList.add(new Text(""));
        curWordIndex++;
        updateTextFlow();
    }

    // Triggers when user types a character
    public void appendToWord(String appendChar) {
        String curText = textList.get(curWordIndex).getText();
        textList.get(curWordIndex).setText(curText + appendChar);
        updateTextFlow();
    }

    // Triggers when user presses backspace
    public void removeFromWord() {
        String curText = textList.get(curWordIndex).getText();

        if (curText.length() - 1 < 0) {
            if (curWordIndex == 0) {
                return;
            }
            textList.remove(curWordIndex);
            curWordIndex--;
            return;
        }

        String newText = curText.substring(0, curText.length() - 1);
        textList.get(curWordIndex).setText(newText);
        updateTextFlow();
    }

    public String getAllText() {
        StringBuilder sb = new StringBuilder();
        for (Text text : textList) {
            sb.append(text.getText());
        }
        return sb.toString();
    }

    public void clearAllText() {
        textFlow.getChildren().removeAll(textList);
        textList.clear();
        textList.add(new Text(""));
        curWordIndex = 0;
        textFlow.getChildren().addAll(textList);
    }
}
