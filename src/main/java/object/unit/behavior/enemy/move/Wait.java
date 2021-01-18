package object.unit.behavior.enemy.move;

import object.unit.Unit;
import settings.Sizes;

public class Wait implements MoveInterfejs {

    @Override
    public void move(Unit unit) {
        unit.getBodyController().moveBody(Sizes.WALK_Speed);
    }
}
