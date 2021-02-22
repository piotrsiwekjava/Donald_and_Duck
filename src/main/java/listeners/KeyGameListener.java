package listeners;

import object.ObjectGame;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.KeyShortCuts;
import settings.Sizes;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyGameListener extends KeyAdapter {
    private Set<ObjectGame> registeredObjectsG = new HashSet<ObjectGame>();
    private Set<Object> registeredComponentsG = new HashSet<Object>();
    private ObjectsController objectsController = ObjectsController.getInstance();
    private Player player;
    private int position[];
    private boolean uppress;
    private boolean downpress;
    private boolean leftpress;
    private boolean rightpress;
    private boolean runpress;
    private int keytyped;
    private boolean gamestarted = false;


    private static KeyGameListener keyListener = new KeyGameListener();

    private KeyGameListener() {
        this.position=new int[] {0,0};
        this.keytyped=0;
    }
    public static KeyGameListener getInstance(){
        return  keyListener;
    }

    private void action() {
        int dx = 0;
        int dy = 0;
        double s = Sizes.WALK_Speed;
        double speed = Sizes.RUN_Speed;
        if (runpress && player.getEnergy()>0) s=speed;
        if (uppress && this.position[1] > Sizes.Y_MAX_MIN_WALK[0]) dy += s;
        else if (downpress && this.position[1] < Sizes.Y_MAX_MIN_WALK[1]) dy -= s;
        if (leftpress && this.position[0] > Sizes.X_MAX_MIN_WALK[0]) dx += s;
        else if (rightpress && this.position[0] < Sizes.LEVELWidth) dx -= s;

        if (dx!=0 || dy!=0) {
            player.setMoveInterfejsInMove();
            if (runpress) {
                player.changeEnergy(-1);
                player.setOwnLegfast(speed);
            }
        }

        this.position[0] -= dx;
        this.position[1] -= dy;
        objectsController.getLevel().passDistance(-dx);
        moveObservers(dx, dy);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (gamestarted) {
            if (e.getKeyChar() == KeyShortCuts.SWITHGUN) {
                player.swiftWeapon();
            }
            else if (e.getKeyChar() == KeyShortCuts.fstWeapons)
                player.swiftWeapon(0);
            else if (e.getKeyChar() == KeyShortCuts.secWeapons)
                player.swiftWeapon(1);
            else if (e.getKeyChar() == KeyShortCuts.thirdWeapons)
                player.swiftWeapon(2);
            else if (e.getKeyChar() == KeyShortCuts.fourthWeapons)
                player.swiftWeapon(3);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyShortCuts.UP)
            this.uppress = true;
        if (e.getKeyCode() == KeyShortCuts.DOWN)
            this.downpress = true;
        if (e.getKeyCode() == KeyShortCuts.LEFT)
            this.leftpress = true;
        if (e.getKeyCode() == KeyShortCuts.RIGHT)
            this.rightpress = true;
        if (e.getKeyCode() == KeyShortCuts.RUN)
            this.runpress = true;

        keytyped = e.getKeyCode();
        if (gamestarted) action();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyShortCuts.UP)
            this.uppress = false;
        if (e.getKeyCode() == KeyShortCuts.DOWN)
            this.downpress = false;
        if (e.getKeyCode() == KeyShortCuts.LEFT)
            this.leftpress = false;
        if (e.getKeyCode() == KeyShortCuts.RIGHT)
            this.rightpress = false;
        if (e.getKeyCode() == KeyShortCuts.RUN) {
            this.runpress = false;
            player.setOwnLegfast(Sizes.Leg_Speed);
        }

        action();
    }

    public void registerObjectG(ObjectGame o){
        registeredObjectsG.add(o);
    }
    public void unregisterObjectG(ObjectGame o){
        registeredObjectsG.remove(o);
    }
    public void registerComponentG(Object o){
        registeredComponentsG.add(o);
    }
    public void unregisterComponentG(Object o){
        registeredComponentsG.remove(o);
    }
    private void moveObservers(double x, double y){
        for (ObjectGame o: registeredObjectsG){
            o.setXY(x,y);
        }
    }
    public int getKeytyped() {
        return keytyped;
    }
    public void setPlayer(Player player){
        this.player=player;
        gamestarted=true;
    }
    public void timeLapse(){
        if (gamestarted) {
            if (!runpress) {
                player.changeEnergy(+1);
                if (!rightpress && !leftpress && !downpress && !uppress) {
                    player.changeEnergy(+1);
                    player.setMoveInterfejsInWait();

                }
            }
            player.changeEnergySuperAttack(+1);
        }
    }
}
