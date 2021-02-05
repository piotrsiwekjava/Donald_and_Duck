package object.unit.behavior.enemy.move;

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
    public Death(){
        this.angle = 0;
        this.increment =3;
    }
    @Override
    public void move(Unit unit) {
//        unit.alive=false;
        if (angle < 80) {
            BodyController bc = unit.getBodyController();
            for (BodyPart bp : unit.getBodyParts()) {
                bc.rotatePart(bp,increment);
            }
        }
        this.angle += increment;
        unit.setPosition(unit.getBodyParts()[0].getPosition());
        int k = (int)unit.getBodyParts()[0].getPosition().getY();
        if(k<100)
        System.out.println("Death __ unit pos "+unit.getPosition()+" body pos "+ unit.getBodyParts()[0].getPosition()+"arms pos "+unit.getBodyParts()[5].getPosition());
        if ((k+unit.getBodyParts()[0].getOdds_draw()[1])>300)System.out.println(" 2 Death __ unit pos "+unit.getPosition()+" body pos "+ unit.getBodyParts()[0].getPosition()+"arms pos "+unit.getBodyParts()[5].getPosition());

    }
}
