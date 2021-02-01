package object.unit.behavior.enemy.move;

import object.ObjectImage;
import object.unit.Unit;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;

public class Patrolls implements MoveInterfejs {
    private ObjectsController objectsController = ObjectsController.getInstance();
    private double[] dXY;
    public Patrolls(){
        this.dXY = new double[]{0,0};
    }
    @Override
    public void move(Unit unit) {
        this.dXY = getCourseOfOneStep(unit);
        if (dXY[0]==0 || dXY[1]==0) {
            unit.getMoveTarget().setPosition(objectsController.getRandomPointMove());
        }
        if (objectsController.checkTrack(unit.getPosition(),dXY,false)) {
            if (dXY[0] > 0) unit.setCourse(1);
            else if (dXY[0] < 0) unit.setCourse(-1);
            for (ObjectImage o : unit.getBodyParts()) {
                o.setXY(dXY[0], dXY[1]);
            }
            unit.setPosition(unit.getBodyParts()[0].getPosition());
            unit.getWeapon().setXY(dXY[0], dXY[1]);
            unit.getBodyController().moveBody(unit.getOwnLegfast());

        }
        else {
            unit.getMoveTarget().setPosition(objectsController.getRandomPointMove());
        }
    }
    private double[] getCourseOfOneStep (Unit unit) {
        Point own = unit.getPosition();
        Point target = unit.getMoveTarget().getPosition();
        double [] dXY = new double[2];
        double dX=(target.getX()-own.getX());
        double dY=(target.getY()-own.getY());
        if((int)dX==0 && (int)dY==0){
            dXY[0]=0;
            dXY[1]=0;
            return dXY;
        }
        double dC=(Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2)));
        dXY[0] = (dX* Sizes.WALK_Speed)/dC;
        dXY[1] = (dY* Sizes.WALK_Speed)/dC;
        return dXY;
    }


}
