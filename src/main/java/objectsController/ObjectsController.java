package objectsController;

import level.Level;
import listeners.KeyGameListener;
import object.factories.Obstacle;
import object.ObjectGame;
import object.factories.Ammo;
import object.unit.Unit;
import object.unit.player.Player;
import threads.BulletFly_Runnable;
import threads.Explosion_Runnable;
import threads.Live_Walk_Look_Runnable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ObjectsController {
    private static int number_of_object =0; //with bullet
    private static Set<ObjectGame> objectGameSet = new HashSet<ObjectGame>();
    private static Set<Ammo>bulletSet = new HashSet<Ammo>();
    private MoveModule moveModule = new MoveModule();
    private EnemiesGeneratorModule enemiesGeneratorModule = new EnemiesGeneratorModule();
    private Player player;
    private Level level;
    private boolean canPlay = false;

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
        showHowManyObjectsGame();
        u.setMoveTarget(moveModule.randomPointMove());
        Live_Walk_Look_Runnable.walk(u, String.valueOf(number_of_object));
    }

    public synchronized void addBullet(Ammo ammo){
        bulletSet.add(ammo);
        number_of_object++;
        BulletFly_Runnable.Fly(ammo,String.valueOf(number_of_object));

    }
    public synchronized void removeBullet(Ammo ammo){
        bulletSet.remove(ammo);
        number_of_object--;
        showHowManyObjectsGame();
    }
    public void addObstacle(Obstacle o){
        this.objectGameSet.add(o);
        number_of_object++;
        showHowManyObjectsGame();
        if (o.isEffect())
            Explosion_Runnable.Explosion(o,String.valueOf(number_of_object));
    }

    public void removeThisObject(ObjectGame o){
        objectGameSet.remove(o);
        number_of_object--;
    }

    public Set<Ammo> getBulletSet() {
        return bulletSet;
    }

    public Point getRandomPointMove(){
        return moveModule.randomPointMove();
    }
    public synchronized boolean checkTrack(Point ownPoint, double[] doubles, boolean isBullet){
        return moveModule.checkTrack(ownPoint,doubles, isBullet);
    }
    public ObjectGame whoBlocked(Point ownPoint, double[] doubles){
        return moveModule.if_Blocked_GiveBack_Responsible_Object(ownPoint,doubles);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addUnit(player);
        canPlay = true;
        KeyGameListener.getInstance().setPlayer(player);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
        this.moveModule.setLevel(level);
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    private void showHowManyObjectsGame(){
        System.out.println("Objects in game: " + number_of_object);
    }
}
