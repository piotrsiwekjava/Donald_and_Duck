package object.unit.behavior.enemy.move;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import object.ObjectImage;
import object.factories.BodyPart;
import object.unit.BodyController;
import object.unit.Unit;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.util.Random;

public class Death implements MoveInterfejs {
    private ObjectsController objectsController = ObjectsController.getInstance();
    private Unit unit;
    private int angle;
    private int increment;

    public Death(Unit unit) {
        this.unit = unit;
        this.angle = 0;
        this.increment = 3;
        if (getlevelofLastDamage()>0) this.increment = -3;
    }

    @Override
    public void move(Unit unit) {
        unit.alive = false;
        if (angle < 80) {
            BodyController bc = unit.getBodyController();
            bc.rotatePart(unit.getBodyParts()[0],increment);
            for (int i = 1; i<6;i++) {
                bc.rotatePart(unit.getBodyParts()[i], getRandomAngleRotate());
            }
        }
        this.angle += increment;
        unit.getBodyController().changePointsBodyWhenTorsoRotate();
    }

    private int getRandomAngleRotate(){
        return new Random().nextInt(increment*2)-increment;
    }
    private int getlevelofLastDamage(){
        int yo = unit.getPosition().y;
        int ydp = unit.getDamagePoint().y;
        int y0size = (int) (unit.getSize()[0] * 10);
        return (ydp - yo - y0size);
    }
}
