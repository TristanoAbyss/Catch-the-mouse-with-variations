import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class CatchOneMouse extends Application {

    /**
     * plays a game that has a clickable mouse
     *
     * @param primaryStage the border that the mouse will be displayed on
     * @throws Exception the exception being caught if there is an error
     */

    public void start(Stage primaryStage) throws Exception {
        Random random = new Random();

        Mouse mouse = new Mouse(Color.PINK);
        mouse.getGroup().setTranslateX(random.nextInt(640));
        mouse.getGroup().setTranslateY(random.nextInt(375));

        Pane pane = new Pane();
        pane.getChildren().add(mouse.getGroup());

        MouseClick mouseClick = new MouseClick(mouse);
        mouse.getGroup().addEventHandler(MouseEvent.MOUSE_PRESSED, mouseClick);

        Scene scene = new Scene(pane);
        scene.setFill(Color.WHITE);

        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }
}
