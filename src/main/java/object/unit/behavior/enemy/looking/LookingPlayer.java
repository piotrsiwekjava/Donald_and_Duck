package object.unit.behavior.enemy.looking;

import object.unit.Move_Look_Point;
import object.unit.Unit;

import java.awt.*;

public class LookingPlayer implements LookingInterfejs{
    @Override
    public void look(Unit unit) {
        Move_Look_Point lookTarget = unit.getLookTarget();
        lookTarget.setPosition(MouseInfo.getPointerInfo().getLocation());
        if (lookTarget.getPosition().getX()< unit.getPosition().getX()) {
            if (unit.getSideLooking() != -1) unit.setSideLooking(-1);
        }
        else if (lookTarget.getPosition().getX()> unit.getPosition().getX()){
            if (unit.getSideLooking() != 1) unit.setSideLooking(1);
        }
    }
}
