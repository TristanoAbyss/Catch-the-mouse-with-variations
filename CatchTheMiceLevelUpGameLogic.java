import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class CatchTheMiceLevelUpGameLogic {
    private int level = 1;
    private int score = 0;
    private int numberOfMiceLeft;
    private ArrayList<Mouse> mice;
    private ArrayList<MouseClickWithLevel> mouseClick;
    private Label label;
    private Label label1;
    private Pane pane;

    public CatchTheMiceLevelUpGameLogic(){
        createArrayOfMice();
        Path path = createLine();
        createPane(path);
        createLabel();
        this.mouseClick = new ArrayList<>();
    }

    /**
     * creates the application and stores the information
     *
     * @param primaryStage the stage that will display the game
     * @param catchMice an instance variable of itself
     */

    public void main(Stage primaryStage, CatchTheMiceLevelUpGameLogic catchMice) {

        setMice();

        randomPosCreation(pane);
        showMice();

        BorderPane border = new BorderPane();
        border.setCenter(pane);
        border.setPadding(new Insets(0, 40, 0, 0));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, label1);
        BorderPane.setMargin(vBox, new Insets(30,50,20 , 15));

        label.setFont(Font.font(24));
        label1.setFont(Font.font(24));

        createHandle(catchMice);



        border.setRight(vBox);

        Scene scene = new Scene(border);
        scene.setFill(Color.WHITE);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets each mouse to its own individual handle and stores it in the MouseClickWithLevel class
     *
     * @param catchMice
     */

    private void createHandle(CatchTheMiceLevelUpGameLogic catchMice) {
        for(Mouse mouse: mice)
            mouseClick.add(new MouseClickWithLevel(catchMice, mouse));
        for(int i = 0; i < mice.size(); i ++)
            mice.get(i).getGroup().addEventHandler(MouseEvent.MOUSE_PRESSED, mouseClick.get(i));
    }

    /**
     * shows the mice that are remaining on the board
     */

    private void showMice() {
        for(int i = 0; i < numberOfMiceLeft; i++)
            mice.get(i).show();
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
        mice.get(i).show();

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
     * creates the labels of both the level and score
     */

    private void createLabel() {
        this.label = new Label("Level: " + level);
        this.label1 = new Label("Score: " + score);
    }



    private void createPane(Path path) {
        this.pane = new Pane();
        pane.getChildren().add(path);
        pane.setMaxHeight(600);
        pane.setMaxWidth(800);
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

}
