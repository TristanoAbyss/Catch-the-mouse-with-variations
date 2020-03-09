import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseClickFinal implements EventHandler<MouseEvent> {
    private Mouse mouse;
    private CatchTheMiceFinalLogic catchMice;

    public MouseClickFinal(CatchTheMiceFinalLogic catchMice, Mouse mouse) {
        this.mouse = mouse;
        this.catchMice = catchMice;
    }

    /**
     * when the mouse is clicked the mouse that is clicked is hidden
     *
     * @param event mouse click on a mouse
     */

    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED && catchMice.getGameRunning()){
            this.catchMice.incrementScore();
            catchMice.hideMouse(this.mouse);
            catchMice.remainingMiceUpdate();
            if(catchMice.getNumberOfMiceLeft() == 0){
                catchMice.resetLevel();
            }
            catchMice.setLabel1();
        }
    }
}
