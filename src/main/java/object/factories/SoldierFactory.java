package object.factories;

import object.enumTypes.RankType;
import object.enumTypes.UnitType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import object.unit.unitTypes.Soldier;

import java.awt.*;

public class SoldierFactory extends UnitFactory{
    public static Soldier create(RankType rType, UnitType uType, Point position, int level) {
        BodyFactory BF = BodyFactory.getInstance();
        Soldier s;

        switch (uType) {
            case SoldierPistol:{
                Soldier soldier = new Soldier(position,BF.Create_Body(rType,position), level,
                        WeaponsFactory.create(WeaponsType.PISTOL, position,120,null));
                soldier.getWeapon().setUnit(soldier);
                return soldier;
            }

            case SoldierAK:{
                Soldier soldier = new Soldier(position,BF.Create_Body(rType,position), level,
                        WeaponsFactory.create(WeaponsType.AK_47, position, 120,null));
                soldier.getWeapon().setUnit(soldier);
                return soldier;
            }
            default:
                throw new UnsupportedOperationException("No such type");
        }

    }
}
