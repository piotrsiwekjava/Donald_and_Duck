package object.unit.unitTypes;

import object.factories.BodyPart;
import object.factories.Weapon;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;

public class Soldier extends Unit {


    public Soldier(Point position, BodyPart[] bodyParts, int level, Weapon weapon) {
        super(position, Sizes.Soldier_Size, bodyParts, 100*(1+(1/3)*level), weapon);
    }
}
