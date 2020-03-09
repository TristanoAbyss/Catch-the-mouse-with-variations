import javafx.application.Application;
import javafx.stage.Stage;

public class NinjaMiceGame extends Application {
    public void start(Stage primaryStage) throws Exception {
        NinjaMiceGameLogic ninjaMice = new NinjaMiceGameLogic();
        ninjaMice.main(primaryStage, ninjaMice);
    }
}
