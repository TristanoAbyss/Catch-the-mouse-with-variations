import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.Random;

public class MouseClickWithScore implements EventHandler<MouseEvent> {
    private Label label;
    private Mouse mouse;
    private Random randomX;
    private Random randomY;
    private int y;
    private int x;
    private int score;

    public MouseClickWithScore(Mouse mouse, int score, Label label) {
        this.mouse = mouse;
        randomX = new Random();
        randomY = new Random();
        this.score = score;
        this.label = label;
    }

    /**
     * moves the mouse to a random position and increments the score
     *
     * @param event
     */

    public void handle(MouseEvent event) {
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED){
            this.x = randomX.nextInt(650);
            this.y = randomY.nextInt(410);
            mouse.move(x, y);
            score += 1;
            label.setText("Score: "+score);
        }
    }

}
