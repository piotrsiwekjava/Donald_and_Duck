package object.factories;

import object.enumTypes.UnitType;
import object.unit.Unit;

public abstract class UnitFactory {
    public Unit create(UnitType type, int level) {
        return null;
    }
}
