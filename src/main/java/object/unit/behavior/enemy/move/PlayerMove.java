package object.unit.behavior.enemy.move;

import object.unit.Unit;
import settings.Sizes;

public class PlayerMove implements MoveInterfejs{
    @Override
    public void move(Unit unit) {
        unit.getBodyController().moveBody(unit.getOwnLegfast());
    }
}
