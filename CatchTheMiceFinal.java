import javafx.application.Application;
import javafx.stage.Stage;

public class CatchTheMiceFinal extends Application {
    public void start(Stage primaryStage) throws Exception {
        CatchTheMiceFinalLogic catchMice = new CatchTheMiceFinalLogic();
        catchMice.main(primaryStage, catchMice);
    }
}
