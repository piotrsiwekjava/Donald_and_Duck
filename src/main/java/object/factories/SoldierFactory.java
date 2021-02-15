package object.factories;

import object.enumTypes.UnitType;
import object.enumTypes.WhichWeaponType;
import object.enumTypes.WeaponsType;
import object.unit.unitTypes.Soldier;

import java.awt.*;

public class SoldierFactory extends UnitFactory{
    public static Soldier create(UnitType uType, WhichWeaponType wType, Point position, int level) {
        BodyFactory BF = BodyFactory.getInstance();
        int points_for_kill = set_Points_for_kill(uType);
        Soldier s;

        switch (wType) {
            case SoldierPistol:{
                Soldier soldier = new Soldier(position,BF.Create_Body(uType,position), level,
                        WeaponsFactory.create(WeaponsType.PISTOL, position,120,null),points_for_kill);
                soldier.getWeapon().setUnit(soldier);
                return soldier;
            }

            case SoldierAK:{
                Soldier soldier = new Soldier(position,BF.Create_Body(uType,position), level,
                        WeaponsFactory.create(WeaponsType.AK_47, position, 120,null),points_for_kill);
                soldier.getWeapon().setUnit(soldier);
                return soldier;
            }
            default:
                throw new UnsupportedOperationException("No such type");
        }

    }

    private static int set_Points_for_kill(UnitType type){
        int points=100;
        if (type.equals(UnitType.OFICER))
            points=25;
        else if (type.equals(UnitType.SOlDIER))
            points=10;
        return points;
    }
}
