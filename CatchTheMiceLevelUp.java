import javafx.application.Application;
import javafx.stage.Stage;

public class CatchTheMiceLevelUp extends Application {
    public void start(Stage primaryStage) throws Exception {
        CatchTheMiceLevelUpGameLogic catchTheMice = new CatchTheMiceLevelUpGameLogic();
        catchTheMice.main(primaryStage, catchTheMice);
    }
}
