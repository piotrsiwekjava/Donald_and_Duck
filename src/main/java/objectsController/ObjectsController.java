package objectsController;

import frames.GamePanel;
import level.Level;
import listeners.KeyGameListener;
import object.Item;
import object.factories.Obstacle;
import object.ObjectGame;
import object.factories.Ammo;
import object.unit.Move_Look_Point;
import object.unit.Unit;
import object.unit.behavior.enemy.attack.Attack;
import object.unit.behavior.enemy.attack.NoAttack;
import object.unit.behavior.enemy.looking.LookingEnemy;
import object.unit.behavior.enemy.looking.Stare;
import object.unit.player.Player;
import sound.Mixer;
import sound.SoundPlayer;
import threads.Attack_Runnable;
import threads.BulletFly_Runnable;
import threads.Explosion_Runnable;
import threads.Live_Walk_Look_Runnable;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ObjectsController {
    private GamePanel gamePanel;
    private static int number_of_object =0; //with bullet
    private static Set<ObjectGame> objectGameSet = new HashSet<ObjectGame>();
    private static Set<Ammo>bulletSet = new HashSet<Ammo>();
    private Set<Move_Look_Point>bloodSet = new HashSet<Move_Look_Point>();
    private MoveModule moveModule = new MoveModule();
    private ObjectsGeneratorModule objectsGeneratorModule = new ObjectsGeneratorModule();
    private Player player;
    private Level level;
    private SoundPlayer soundPlayer = Mixer.getMainSoundPlayer();
    private int howManyEnemyNow =-1;
    private boolean canPlay = false;
    private boolean enemySeePlayer = false;
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
        Unit [] units = objectsGeneratorModule.newUnits();
        for (int i=0;i<units.length;i++){
            addUnit(units[i]);
        }
    }

    void addUnit(Unit u) {
        objectGameSet.add(u);
        number_of_object++;
        showHowManyObjectsGame();
        u.setMoveTarget(moveModule.randomPointMove());
        Live_Walk_Look_Runnable.walk(u, String.valueOf(number_of_object));
        howManyEnemyNow++;
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
    public void addRandomObstracle(){
        Obstacle o = objectsGeneratorModule.newObstacle();
        addObstacle(o);
    }

    public void addItem(Item i){
        objectGameSet.add(i);
    }

    public synchronized void removeThisObject(ObjectGame o){
        objectGameSet.remove(o);
        number_of_object--;
        if(o instanceof Unit) howManyEnemyNow--;
        if (howManyEnemyNow==0) setEnemySeePlayer(false);
        o=null;
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
        System.out.println("Enemies in game: " + howManyEnemyNow);
    }

    public Set<Move_Look_Point> getBloodSet() {
        return bloodSet;
    }

    public void setBloodSet(Set<Move_Look_Point> bloodSet) {
        this.bloodSet = bloodSet;
    }

    public void setEnemySeePlayer(boolean enemySeePlayer) {
        this.enemySeePlayer = enemySeePlayer;
        if (enemySeePlayer) {
            playSoundtrack(2);
            for (ObjectGame og: objectGameSet){
                if (og instanceof Unit && !(og instanceof Player)){
                    ((Unit)og).seePlayer=true;
                    ((Unit)og).setLookingInterfejs(new Stare());
                    ((Unit)og).setLookTarget(player.getPosition());
                    ((Unit)og).setAttackInerfejs(new Attack());
                    Attack_Runnable.Attack((Unit) og);
                }
            }
        }
        else {
            playSoundtrack(1);
            for (ObjectGame og: objectGameSet) {
            if (og instanceof Unit && !(og instanceof Player)) {
                ((Unit) og).seePlayer = false;
                ((Unit) og).setLookingInterfejs(new LookingEnemy());
                ((Unit) og).setAttackInerfejs(new NoAttack());
            }
        }
        }
    }
    public void remove_Blood_and_Unit(Move_Look_Point blood){
        removeThisObject(blood.getUnit());
        bloodSet.remove(blood);
        blood=null;
    }
    public int getTime(){
        return gamePanel.getTime();
    }
    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void playSoundtrack(int number){
        soundPlayer.startPlay(number);
    }
}
