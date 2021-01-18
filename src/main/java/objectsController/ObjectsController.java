package objectsController;

import level.Level;
import object.Obstacle;
import object.ObjectGame;
import object.factories.Ammo;
import object.unit.Unit;
import object.unit.player.Player;
import threads.BulletFly_Runnable;
import threads.Walk_Look_Runnable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ObjectsController {
    private static int number_of_object =0; //with bullet
    private static Set<ObjectGame> objectGameSet = new HashSet<ObjectGame>();
    private static Set<Obstacle>obstacleSet = new HashSet<Obstacle>();
    private static Set<Ammo>bulletSet = new HashSet<Ammo>();
    private MoveModule moveModule = new MoveModule();
    private EnemiesGeneratorModule enemiesGeneratorModule = new EnemiesGeneratorModule();
    private Player player;
    private Level level;

    private static ObjectsController objectsController= new ObjectsController();

    private ObjectsController() {
    }
    public static ObjectsController getInstance(){
        return objectsController;
    }


    public Set<ObjectGame> getObjectGSet() {
        return objectGameSet;
    }

    public void addUnitToGame(){
        Unit [] units = enemiesGeneratorModule.newUnits();
        for (int i=0;i<units.length;i++){
            addUnit(units[i]);
        }
    }

    public void addUnit(Unit u) {
        objectGameSet.add(u);
        number_of_object++;
        System.out.println("number of object in game : "+number_of_object);
        u.setMoveTarget(moveModule.randomPointMove(u));
        Walk_Look_Runnable.walk(u, String.valueOf(number_of_object));
    }

    public synchronized void addBullet(Ammo ammo){
        bulletSet.add(ammo);
        number_of_object++;
        BulletFly_Runnable.Fly(ammo,String.valueOf(number_of_object));

    }
    public synchronized void removeBullet(Ammo ammo){
        bulletSet.remove(ammo);
        number_of_object--;
        System.out.println("number of object in game : "+number_of_object);
    }
    public void addObstacle(Obstacle o){
        this.obstacleSet.add(o);
        number_of_object++;
        System.out.println("number of object in game : "+number_of_object);
    }

    public Set<Obstacle> getObstacleSet() {
        return obstacleSet;
    }

    public void removeOutObject (ObjectGame o){
        objectGameSet.remove(o);
        number_of_object--;
    }

    public Set<Ammo> getBulletSet() {
        return bulletSet;
    }

    public MoveModule getMoveModule() {
        return moveModule;
    }

    public Point getRandomPointMove(Unit unit){
        return moveModule.randomPointMove(unit);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addUnit(player);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
        this.moveModule.setLevel(level);
    }
}
