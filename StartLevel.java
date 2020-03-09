import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StartLevel implements EventHandler<ActionEvent> {
    private Label errorMessage;
    private CatchTheMiceFinalLogic catchMice;
    private TextField levelInput;

    public StartLevel(TextField levelInput, CatchTheMiceFinalLogic catchMice, Label errorMessage){
        this.levelInput = levelInput;
        this.catchMice = catchMice;
        this.errorMessage = errorMessage;
    }

    /**
     * handling the event of the button being interacted with by the user
     *
     * @param event the button being clicked
     */

    public void handle(ActionEvent event) {
        int input;
        try {
            input = Integer.parseInt(levelInput.getText());
            if(input >= 1 && input <= 5){
                catchMice.setStartGame(true, input);
                errorMessage.setText("");
            } else {
                levelInput.setText("");
                errorMessage.setText("Invalid Level");
                catchMice.setStartGame(false, 0);
            }
        } catch (NumberFormatException e){
            levelInput.setText("");
            errorMessage.setText("Invalid Level");
        }
    }
}
