package object.unit.behavior.enemy.looking;

import object.unit.Move_Look_Target;
import object.unit.Unit;

import java.awt.*;

public class LookingPlayer implements LookingInterfejs{
    @Override
    public void look(Unit unit) {
        Move_Look_Target lookTarget = unit.getLookTarget();
        lookTarget.setPosition(MouseInfo.getPointerInfo().getLocation());
        if (lookTarget.getPosition().getX()< unit.getPosition().getX()) {
            if (unit.getSide() != -1) unit.setSide(-1);
        }
        else if (lookTarget.getPosition().getX()> unit.getPosition().getX()){
            if (unit.getSide() != 1) unit.setSide(1);
        }
    }
}
