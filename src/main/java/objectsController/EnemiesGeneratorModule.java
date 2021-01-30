package objectsController;

import level.Level;
import object.enumTypes.RankType;
import object.enumTypes.UnitType;
import object.factories.SoldierFactory;
import object.unit.Unit;
import object.unit.behavior.enemy.move.Patrolls;
import settings.Sizes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class EnemiesGeneratorModule {
    private int level;
    private final int amount_of_enemy = 4;
    private ArrayList <RespawnPoint> respawnPoint;
    private boolean initiationed;
    EnemiesGeneratorModule (){
    }
    private void initiation(){
        this.respawnPoint = new ArrayList<RespawnPoint>();
        int high0 = (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.6);
        int hd = (int)(high0/6);
        this.respawnPoint.add(new RespawnPoint(0,high0));
        this.respawnPoint.add(new RespawnPoint(50,(high0 + hd)));
        this.respawnPoint.add(new RespawnPoint(-50,(high0 - hd)));
        this.respawnPoint.add(new RespawnPoint(-50,(high0 + (hd*2))));
        this.respawnPoint.add(new RespawnPoint(50, (high0 - (hd*2))));
    }
    Unit[] newUnits (){
        if (!initiationed) {
            initiation();
            initiationed = true;
        }
        getLevelNumber();
        int random = new Random().nextInt(5)+1;
        Unit[] units = new Unit[random];
        for (int i =0; i<random;i++){
            int r = new Random().nextInt(amount_of_enemy)+1;
            Point p = new Point(respawnPoint.get(i).getPosition());
            units[i]=getUnitByNumber(p,r);
            units[i].setMoveTarget(units[i].getPosition());
            units[i].setMoveInterfejs(new Patrolls());
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
    private void getLevelNumber(){
        Level plevel = ObjectsController.getInstance().getLevel();
        this.level = plevel.getNumber();
    }
}
