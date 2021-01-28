package objectsController;

import level.Level;
import object.enumTypes.RankType;
import object.enumTypes.UnitType;
import object.factories.SoldierFactory;
import object.unit.Unit;
import object.unit.behavior.enemy.looking.Stare;
import object.unit.behavior.enemy.move.Patrolls;
import settings.Sizes;

import java.awt.*;
import java.util.Random;

class EnemiesGeneratorModule {
    private int level;
    private int minY;
    private int maxY;
    private final int amount_of_enemy = 4;
    EnemiesGeneratorModule (){
    }
    private void getValues(){
        Level plevel = ObjectsController.getInstance().getLevel();
        this.level = plevel.getNumber();
        this.minY = plevel.getStreets()[0].getPosition().y;
        this.maxY = minY+(int)plevel.getStreets()[0].getSize()[1];
    }
    Unit[] newUnits (){
        getValues();
        int random = new Random().nextInt(5)+1;
        Unit[] units = new Unit[random];
        for (int i =0; i<random;i++){
            int h = new Random().nextInt(maxY)+minY;
            if (h>maxY)h=maxY;
            int r = new Random().nextInt(amount_of_enemy)+1;
            units[i]=getUnitByNumber(new Point((int) (Sizes.Screen_Width*1.1), h),r);
            units[i].setMoveTarget(units[i].getPosition());
//            units[i].setMoveInterfejs(new Patrolls());
        }
        return units;
    }
    private Unit getUnitByNumber(Point point, int i){
        Unit unit = SoldierFactory.create(RankType.SOlDIER, UnitType.SoldierAK,point,level);
        switch (i){
                case(1): return unit = SoldierFactory.create(RankType.OFICER,UnitType.SoldierAK,point,level);
                case(2): return unit = SoldierFactory.create(RankType.OFICER,UnitType.SoldierPistol,point,level);
                case(3): return unit = SoldierFactory.create(RankType.SOlDIER, UnitType.SoldierAK,point,level);
                case(4): return unit = SoldierFactory.create(RankType.SOlDIER, UnitType.SoldierPistol,point,level);
        }
        return unit;
    }
}
