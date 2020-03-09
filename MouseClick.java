import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class MouseClick implements EventHandler<MouseEvent> {
    private Mouse mouse;
    private Random randomX;
    private Random randomY;
    private int y;
    private int x;

    public MouseClick(Mouse mouse) {
    this.mouse = mouse;
    randomX = new Random();
    randomY = new Random();
    }

    /**
     * when the mouse is clicked the mouse is moved
     *
     * @param event the mouse click on the mouse object
     */

    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.x = randomX.nextInt(640);
            this.y = randomY.nextInt(375);
            mouse.move(x, y);
        }
    }

}
