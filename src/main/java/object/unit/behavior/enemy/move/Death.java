package object.unit.behavior.enemy.move;

import object.ObjectImage;
import object.unit.Unit;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;

public class Death implements MoveInterfejs {
    private ObjectsController objectsController = ObjectsController.getInstance();
    private double angle;
    private int increment;
    public Death(){
        this.angle = 0;
        this.increment =3;
    }
    @Override
    public void move(Unit unit) {
        unit.alive=false;
        if (angle < 80)
            unit.getBodyController().rotateBody(increment);
        this.angle += increment;

    }
}
