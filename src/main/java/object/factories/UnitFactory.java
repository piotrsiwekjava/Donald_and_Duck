package object.factories;

import object.enumTypes.WhichWeaponType;
import object.unit.Unit;

public abstract class UnitFactory {
    public Unit create(WhichWeaponType type, int level) {
        return null;
    }
}
