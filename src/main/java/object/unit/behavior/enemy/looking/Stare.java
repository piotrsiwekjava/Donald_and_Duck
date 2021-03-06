package object.unit.behavior.enemy.looking;

import object.unit.Move_Look_Point;
import object.unit.Unit;

public class Stare implements LookingInterfejs {
    @Override
    public void look(Unit unit) {
        if (unit.isAlive()) {
            Move_Look_Point lookTarget = unit.getLookTarget();

            if (lookTarget.getPosition().getX() < unit.getPosition().getX()) {
                if (unit.getSideLooking() != -1) unit.setSideLooking(-1);
            } else if (lookTarget.getPosition().getX() > unit.getPosition().getX()) {
                if (unit.getSideLooking() != 1) unit.setSideLooking(1);
            }
        }
    }
}
