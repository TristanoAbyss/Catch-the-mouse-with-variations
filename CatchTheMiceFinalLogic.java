import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Random;


public class CatchTheMiceFinalLogic {
    private int level = 1;
    private int score = 0;
    private int numberOfMiceLeft;
    private ArrayList<Mouse> mice;
    private ArrayList<MouseClickFinal> mouseClick;
    private ArrayList<FadeTransition> fade;
    private Label label;
    private Label label1;
    private Pane pane;
    private TextField startLevel;
    private Button startBtn;
    private boolean startGame;
    private boolean gameRunning;


    /**
     * initializes instance variables
     */

    public CatchTheMiceFinalLogic(){
        createArrayOfMice();
        Path path = createLine();
        createPane(path);
        createLabel();
        createLevelInput();
        createFade();
        this.mouseClick = new ArrayList<>();
        this.startGame = false;
        this.gameRunning = false;
    }



    public void main(Stage primaryStage, CatchTheMiceFinalLogic catchMice) {
        setMice();
        randomPosCreation(pane);

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(40));
        border.setCenter(pane);

        HBox invalidAnswer = new HBox();
        Label errorMessage = new Label();
        invalidAnswer.getChildren().addAll(startLevel, errorMessage);
        invalidAnswer.setSpacing(10);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(invalidAnswer, startBtn, label, label1);
        vBox.setSpacing(10);

        StartLevel startLevel = new StartLevel(this.startLevel, catchMice, errorMessage);
        this.startBtn.setOnAction(startLevel);

        label.setFont(Font.font(24));
        label1.setFont(Font.font(24));
        for (Mouse mouse : mice)
            mouseClick.add(new MouseClickFinal(catchMice, mouse));
        for (int i = 0; i < mice.size(); i++)
            mice.get(i).getGroup().addEventHandler(MouseEvent.MOUSE_PRESSED, mouseClick.get(i));

        border.setRight(vBox);

        Scene scene = new Scene(border);
        scene.setFill(Color.WHITE);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * the textfield that accepts a number 1-5 to start the game
     */

    private void createLevelInput() {
        this.startLevel = new TextField("");
        this.startLevel.setPromptText("Start Level");
        Button startBtn = new Button("Start");
        startBtn.setMaxWidth(150);
        this.startBtn = startBtn;
    }

    /**
     * creates the labels of both the level and score
     */

    private void createLabel() {
        this.label = new Label("Level: " + level);
        this.label1 = new Label("Score: " + score);
    }

    /**
     * creates the pane that will display the mice
     *
     * @param path the line that acts as the border
     */

    private void createPane(Path path) {
        this.pane = new Pane();
        pane.getChildren().add(path);
        pane.setMaxHeight(600);
        pane.setMaxWidth(800);
    }

    /**
     * shows the mice that are remaining on the board
     */


    private void showMice() {
        for(int i = 0; i < numberOfMiceLeft; i++) {
            mice.get(i).show();
            fade.get(i).play();
        }
    }

    /**
     * reduces the amount of mice on the board by one
     */

    public void remainingMiceUpdate() {
        this.numberOfMiceLeft -= 1;
    }

    /**
     * Increments the score when a mouse is clicked
     */

    public void incrementScore(){
        score ++;
    }

    /**
     * when no mice are remaining on the board the level is incremented, the labels are updated and a new set of mice
     * are displayed on the board based on the level
     */

    public void resetLevel(){
        if(level == 5)
            level = 5;
        else
            level += 1;
        numberOfMiceLeft = level;
        label.setText("Level: "+ level);
        label1.setText("Score: "+score);
        for(int i = 0; i < numberOfMiceLeft; i ++)
            displayAndRandom(i);
    }

    /**
     * a call method that will display the amount of mice that are needed for the level (mice remaining) and then shows the mice
     *
     * @param i the index position of the mice array
     */

    private void displayAndRandom(int i) {
        Random random = new Random();
        mice.get(i).move(random.nextInt(650),random.nextInt(410));
        mice.get(i).setOpacity();
        mice.get(i).show();
        fade.get(i).play();
    }

    /**
     * the mice that are remaining on the board when the level starts
     */

    private void setMice(){
        if(level > 5)
            numberOfMiceLeft = 5;
        else
            numberOfMiceLeft = level;
    }

    /**
     * when the mouse is created the mouse will be positioned at a random position and hidden
     *
     * @param pane the pane the mouse is contained within
     */

    private void randomPosCreation(Pane pane) {
        for(Mouse mouse: mice) {
            Random random = new Random();
            pane.getChildren().add(mouse.getGroup());
            mouse.move(random.nextInt(650),random.nextInt(410));
            mouse.hide();
        }
    }

    /**
     * Sets the game taking the inputs from the user and also clears the board so a new game can be made
     *
     * @param startGame if the game has already been started, the game will restart the score and do the level input
     * @param startLevel the level that the game will start on
     */

    public void setStartGame(boolean startGame, int startLevel){
        this.startGame = startGame;
        if(this.startGame) {
            clearBoard();
            startGameFromInput(startLevel);
        }
    }

    /**
     * hides all mice currently on the board.
     */

    private void clearBoard() {
        for(Mouse mouse: mice){
            mouse.hide();
        }
    }

    /**
     * Begins the game from the input (textfield on the right border)
     *
     * @param startLevel
     */

    private void startGameFromInput(int startLevel) {
        this.level = startLevel;
        setLevel();
        setMice();
        showMice();
        this.gameRunning = true;
        this.startGame = false;
    }

    /**
     * The level is set from the textField on the right border as well as the score
     */

    public void setLevel(){
        label.setText("Level: "+level);
        score = 0;
        label1.setText("Score: "+score);
    }

    /**
     * Hides the indicated mouse from the click Class
     *
     * @param mouse The mouse that is being clicked
     */

    public void hideMouse(Mouse mouse){
        mouse.hide();
    }

    /**
     * creates the array of mice at the beginning of the game
     */

    private void createArrayOfMice() {
        this.mice = new ArrayList<>();
        for(int i = 0; i < 5 ; i++)
            mice.add(new Mouse(Color.PINK));
    }

    /**
     * creates a line to indicate the border of the mouse == Information panel
     *
     * @return
     */

    private Path createLine() {
        Path line = new Path(
                new MoveTo(800,-1000),
                new LineTo(800, 2000),
                new ClosePath()
        );
        return line;
    }

    /**
     * returns the amounts of active mice on the board
     *
     * @return the amount of mice left on the board
     */

    public int getNumberOfMiceLeft() {
        return numberOfMiceLeft;
    }

    /**
     * sets the label of the score after a mouse is clicked
     */

    public void setLabel1(){
        this.label1.setText("Score: "+score);
    }

    /**
     * the fade for when the mice are drawn after each level
     */

    private void createFade() {
        this.fade = new ArrayList<>();
        for (int i = 0; i < mice.size(); i++) {
            this.fade.add(new FadeTransition(Duration.seconds(.5), mice.get(i).getGroup()));
            this.fade.get(i).setToValue(1);
            this.fade.get(i).setFromValue(0);
        }
    }

    /**
     * Checks if the game is currently running, if it is, it will reset upon a new level being input
     *
     * @return the game's run state
     */


    public boolean getGameRunning() {
        return this.gameRunning;
    }
}
