import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;


public class CatchOneMouseWithScore extends Application {
    private int score = 0;

    /**
     * creates a clickable mouse that will increment the score when it is pressed
     *
     * @param primaryStage the stage that will show the mouse
     * @throws Exception the exception that will be thrown if one occurs
     */

    public void start(Stage primaryStage) throws Exception {
        Random random = new Random();
        Mouse mouse = new Mouse(Color.PINK);
        Path line = createLine();
        Pane pane = new Pane();
        pane.getChildren().addAll(mouse.getGroup(), line);
        pane.maxWidth(800);
        pane.maxHeight(600);



        BorderPane border = new BorderPane();
        border.setCenter(pane);

        Label label = new Label("Score: "+ score);
        label.setFont(Font.font(24));
        VBox vBox = new VBox();
        vBox.getChildren().add(label);
        vBox.setPadding(new Insets(35));

        border.setRight(vBox);
        border.setPadding(new Insets(0, 35, 0, 0));


        MouseClickWithScore mouseClick = new MouseClickWithScore(mouse, score, label);
        mouse.getGroup().setTranslateX(random.nextInt(650));
        mouse.getGroup().setTranslateY(random.nextInt(410));
        mouse.getGroup().addEventHandler(MouseEvent.MOUSE_PRESSED, mouseClick);

        Scene scene = new Scene(border);
        scene.setFill(Color.WHITE);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * creates a line to act as the border
     */

    private Path createLine() {
        Path line = new Path(
                new MoveTo(800,0),
                new LineTo(800, 600),
                new ClosePath()
        );
        return line;
    }
}
