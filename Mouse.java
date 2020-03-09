import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Mouse {
  private Group mouseGroup;

  public Mouse(Color noseColor) {
    this.mouseGroup = this.draw(noseColor);
  }

  private static final Color MOUSE_FILL = Color.LIGHTGRAY;
  private static final int EAR_RADIUS = 25;
  private static final Color EAR_FILL = MOUSE_FILL;

  private static final Color FACE_FILL = MOUSE_FILL;
  private static final Color FACE_STROKE = Color.GRAY;
  private static final int FACE_WIDTH = 90;
  private static final int FACE_HEIGHT = 100;

  private static final int EYE_RADIUS = 5;
  private static final Color EYE_FILL = Color.BLACK;

  private static final int NOSE_RADIUS = 10;

  private static final int BODY_RADIUS = 60;
  private static final Color BODY_FILL = MOUSE_FILL;

  public static final int MOUSE_WIDTH = 140;
  public static final int MOUSE_HEIGHT = 185;

  private Circle ear(double x, double y) {
    Circle circle = new Circle(x + EAR_RADIUS, y + EAR_RADIUS, EAR_RADIUS);
    circle.setFill(EAR_FILL);
    return circle;
  }

  private Group face(double x, double y, Color noseColor) {
    Path face = new Path(
        new MoveTo(x, y),
        new LineTo(x + FACE_WIDTH, y),
        new LineTo(x + FACE_WIDTH / 2, y + FACE_HEIGHT),
        new ClosePath()
    );
    face.setFill(FACE_FILL);
    face.setStroke(FACE_STROKE);

    Circle leftEye = new Circle(x + 25, y + 25, EYE_RADIUS, EYE_FILL);
    Circle rightEye = new Circle(x + FACE_WIDTH - 25, y + 25, EYE_RADIUS, EYE_FILL);

    Circle nose = new Circle(x + FACE_WIDTH / 2, y + FACE_HEIGHT, NOSE_RADIUS, noseColor);

    return new Group(face, leftEye, rightEye, nose);
  }

  private Group draw(Color noseColor) {
    Shape leftEar = this.ear(0, 0);
    Shape rightEar = this.ear(FACE_WIDTH, 0);
    Group face = this.face(EAR_RADIUS, EAR_RADIUS, noseColor);
    Shape body = this.body(EAR_RADIUS + FACE_WIDTH / 2, EAR_RADIUS + FACE_HEIGHT);
    Group group = new Group(body, face, leftEar, rightEar);
    return group;
  }

  public Group getGroup() {
    return this.mouseGroup;
  }

  public void move(double x, double y) {
    this.mouseGroup.setTranslateX(x);
    this.mouseGroup.setTranslateY(y);
  }

  private Circle body(double x, double y) {
    return new Circle(x, y, BODY_RADIUS, BODY_FILL);
  }

  public void hide() {
    this.mouseGroup.setVisible(false);
  }

  public void show() {
    this.mouseGroup.setVisible(true);
  }

  public void setOpacity(){
    this.mouseGroup.setOpacity(0);
  }
}
