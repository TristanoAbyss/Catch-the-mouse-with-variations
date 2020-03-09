import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseClickNinja implements EventHandler<MouseEvent> {
    private Mouse mouse;
    private NinjaMiceGameLogic catchMice;

    public MouseClickNinja(NinjaMiceGameLogic catchMice, Mouse mouse) {
        this.mouse = mouse;
        this.catchMice = catchMice;
    }

    /**
     * when the mouse is clicked the mouse that is clicked is hidden and the score is incremented, the amount of mice are reduced by 1, and if there are no mice on the board
     * the board is reset and the level is incremented
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
