package object.unit.behavior.enemy.looking;

import object.unit.Move_Look_Target;
import object.unit.Unit;
import object.unit.player.Player;
import settings.Sizes;

import java.awt.*;
import java.util.Random;

public class LookingEnemy implements LookingInterfejs{
    private int angle = 0;
    private int inc = 0;
    @Override
    public void look(Unit unit) {
        Move_Look_Target lookTarget = unit.getLookTarget();
        if (angle==0){
            int r = new Random().nextInt(2);
            if (r==0) inc=-1;
            else inc=1;
        }
        angle+=inc;
        lookTarget.getPosition().translate(0,10*inc);
        if (Math.abs(angle)>45) {
            angle = 0;
            int r = new Random().nextInt(3);
            if (r==0) {
                int w = new Random().nextInt(Sizes.Screen_Width);
                int h = new Random().nextInt(Sizes.Screen_Height);
                lookTarget.setPosition(new Point(w,h));
            }
        }
        if (lookTarget.getPosition().getX()< unit.getPosition().getX()) {
            if (unit.getSide() != -1) unit.setSide(-1);
        }
        else if (lookTarget.getPosition().getX()> unit.getPosition().getX()){
            if (unit.getSide() != 1) unit.setSide(1);
        }
    }
}
