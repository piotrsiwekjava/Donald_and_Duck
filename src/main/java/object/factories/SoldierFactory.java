package object.factories;

import object.enumTypes.RankType;
import object.enumTypes.UnitType;
import object.enumTypes.WeaponsType;
import object.unit.unitTypes.Soldier;

import java.awt.*;

public class SoldierFactory extends UnitFactory{
    public static Soldier create(RankType rType, UnitType uType, Point position, int level) {
        BodyFactory BF = BodyFactory.getInstance();
        Soldier s;

        switch (uType) {
            case SoldierPistol:
                return new Soldier(position,BF.Create_Body(rType,position), level,
                        WeaponsFactory.create(WeaponsType.PISTOL, position,120));

            case SoldierAK:
                return new Soldier(position,BF.Create_Body(rType,position), level,
                        WeaponsFactory.create(WeaponsType.AK_47, position, 120));
            default:
                throw new UnsupportedOperationException("No such type");
        }

    }
}
