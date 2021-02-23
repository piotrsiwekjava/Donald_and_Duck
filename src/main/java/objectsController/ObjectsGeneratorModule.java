package objectsController;

import level.Level;
import object.enumTypes.ObstacleType;
import object.enumTypes.UnitType;
import object.enumTypes.WhichWeaponType;
import object.factories.Obstacle;
import object.factories.ObstacleFactory;
import object.factories.SoldierFactory;
import object.unit.Unit;
import object.unit.behavior.enemy.move.Patrolls;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class ObjectsGeneratorModule {
    private int level;
    private final int amount_of_enemy = 4;
    private ArrayList <RespawnPoint> respawnPoint;
    private boolean initiationed;
    ObjectsGeneratorModule(){
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
        Unit unit = SoldierFactory.create(UnitType.SOlDIER, WhichWeaponType.SoldierAK,point,level);
        switch (i){
                case(1): return unit = SoldierFactory.create(UnitType.OFICER, WhichWeaponType.SoldierAK,point,level);
                case(2): return unit = SoldierFactory.create(UnitType.OFICER, WhichWeaponType.SoldierPistol,point,level);
                case(3): return unit = SoldierFactory.create(UnitType.SOlDIER, WhichWeaponType.SoldierAK,point,level);
                case(4): return unit = SoldierFactory.create(UnitType.SOlDIER, WhichWeaponType.SoldierPistol,point,level);
        }
        return unit;
    }
    Obstacle newObstacle (){
        if (!initiationed) {
            initiation();
            initiationed = true;
        }
        return getRandomObject();
    }
    private void getLevelNumber(){
        Level plevel = ObjectsController.getInstance().getLevel();
        this.level = plevel.getNumber();
    }
    private Point getRandomRestPawn(){
        int r = new Random().nextInt(5);
        int h = new Random().nextInt(50)+100;
        Point p = new Point(respawnPoint.get(r).getPosition());
        p.translate(0,h);
        return p;
    }
    private Obstacle getRandomObject(){
        Obstacle obstacle = ObstacleFactory.create(ObstacleType.BARRELS,getRandomRestPawn());
        int r = new Random().nextInt(10);
        if (r>7)
            obstacle = ObstacleFactory.create(ObstacleType.SCRAPCAR,getRandomRestPawn());
        return obstacle;
    }
}
