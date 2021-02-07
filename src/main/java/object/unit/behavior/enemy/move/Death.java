package object.unit.behavior.enemy.move;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import object.ObjectImage;
import object.factories.BodyPart;
import object.unit.BodyController;
import object.unit.Unit;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;

public class Death implements MoveInterfejs {
    private ObjectsController objectsController = ObjectsController.getInstance();
    private double angle;
    private int increment;

    public Death() {
        this.angle = 0;
        this.increment = 3;
    }

    @Override
    public void move(Unit unit) {
        unit.alive = false;
        if (angle < 80) {
            BodyController bc = unit.getBodyController();
            for (BodyPart bp : unit.getBodyParts()) {
                bc.rotatePart(bp, increment);
            }
        }
        this.angle += increment;
        unit.getBodyController().changePointsBodyWhenTorsoRotate();
    }

    private int[] getRandomAngleRotate(){

    }
    private int get
}
