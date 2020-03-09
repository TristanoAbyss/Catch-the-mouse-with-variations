

import javafx.event.EventHandler;

import javafx.scene.input.MouseEvent;


public class MouseClickWithLevel implements EventHandler<MouseEvent> {
    private Mouse mouse;

    private CatchTheMiceLevelUpGameLogic catchMice;

    public MouseClickWithLevel(CatchTheMiceLevelUpGameLogic catchMice, Mouse mouse) {
        this.mouse = mouse;
        this.catchMice = catchMice;
    }


    /**
     * increments the score, level, and hides the mouse that is clicked. If no mice are on the board after the mouse is clicked
     * the board is reset and the level is incremented
     *
     * @param event a mouseclick on the indicated mouse
     */

    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
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
