package object.unit.behavior.enemy.looking;

import object.unit.Move_Look_Point;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.util.Random;

public class LookingEnemy implements LookingInterfejs{
    private int angle = 0, inc =0;
    private int purviewHeight = (int) Sizes.SIZE*400;
    private int purviewWidth = (int) (Sizes.Screen_Width*0.75);
    private Move_Look_Point lookTarget;
    @Override
    public void look(Unit unit) {

        this.lookTarget = unit.getLookTarget();
        if (angle == 0) {
            lookingUpOrDown(); //setInc
        }
        angle += inc;
        lookTarget.getPosition().translate(0, 10 * inc);
        if (Math.abs(angle) > 45) {
            setRandomLookTargetPosition();
        }
        setUnitSide(unit);
        if (lookingPlayer(unit)) {
            ObjectsController.getInstance().setEnemySeePlayer(true);
        }

    }
    private void lookingUpOrDown(){
        int r = new Random().nextInt(2);
        if (r==0) inc=-1;
        else inc=1;
    }
    private void setRandomLookTargetPosition(){
        angle = 0;
        int r = new Random().nextInt(3);
        if (r==0) {
            int w = new Random().nextInt(Sizes.Screen_Width);
            int h = new Random().nextInt(Sizes.Screen_Height);
            this.lookTarget.setPosition(new Point(w,h));
        }
    }
    private void setUnitSide (Unit unit){
        if (lookTarget.getPosition().getX()< unit.getPosition().getX()) {
            if (unit.getSideLooking() != -1) unit.setSideLooking(-1);
        }
        else if (lookTarget.getPosition().getX()> unit.getPosition().getX()){
            if (unit.getSideLooking() != 1) unit.setSideLooking(1);
        }
    }
    private boolean lookingPlayer(Unit unit){
        if (canSeePlayerInX(unit)) {
            if (canSeePlayerInY(unit)) {
                return true;
            }
        }
        return false;
    }
    private boolean canSeePlayerInX(Unit unit){

        double uX = unit.getPosition().getX();
        double lpX = lookTarget.getPosition().getX();
        double dX=(lpX - uX);

        Player player = ObjectsController.getInstance().getPlayer();
        double pX = player.getPosition().getX();
        if (dX<0 && (pX<uX) && ((uX-pX)<purviewWidth)){
            return true;
        }
        else if (dX>0 && (pX>uX) && ((pX-uX)<purviewWidth)){
            return true;
        }
        return false;
    }
    private boolean canSeePlayerInY(Unit unit){
        double uY = unit.getPosition().getX();
        double lpY = lookTarget.getPosition().getX();
        double dY=(lpY - uY);
        Player player = ObjectsController.getInstance().getPlayer();
        double pY = player.getPosition().getY();
        if (dY<0 && (pY<uY)){
            return true;
        }
        else if (dY>0 && (pY>uY)){
            return true;
        }
            return false;
    }
}
