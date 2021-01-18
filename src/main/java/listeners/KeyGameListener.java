package listeners;

import object.ObjectGame;
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
    private int position[];
    private boolean uppress;
    private boolean downpress;
    private boolean leftpress;
    private boolean rightpress;
    private boolean runpress;
    private int keytyped;


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
        double s = Sizes.Game_Speed;
        double speed = Sizes.RUN_Speed;
        if (runpress) s=speed;
        if (uppress && this.position[1] > Sizes.Y_MAX_MIN_WALK[0]) dy += s;
        else if (downpress && this.position[1] < Sizes.Y_MAX_MIN_WALK[1]) dy -= s;
        if (leftpress && this.position[0] > Sizes.X_MAX_MIN_WALK[0]) dx += s;
        else if (rightpress && this.position[0] < Sizes.LEVELWidth) dx -= s;

        this.position[0] -= dx;
        this.position[1] -= dy;
        objectsController.getLevel().pass200m_and_AddEnemy(-dx);
        moveObservers(dx, dy);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyShortCuts.UP)
            this.uppress=true;
        else if(e.getKeyCode()==KeyShortCuts.DOWN)
            this.downpress=true;
        else if(e.getKeyCode()==KeyShortCuts.LEFT)
            this.leftpress=true;
        else if(e.getKeyCode()==KeyShortCuts.RIGHT)
            this.rightpress=true;
        else if(e.getKeyCode()==KeyShortCuts.RUN)
            this.runpress=true;

        keytyped=e.getKeyCode();
        action();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode()== KeyShortCuts.UP)
            this.uppress=false;
        else if(e.getKeyCode()==KeyShortCuts.DOWN)
            this.downpress=false;
        else if(e.getKeyCode()==KeyShortCuts.LEFT)
            this.leftpress=false;
        else if(e.getKeyCode()==KeyShortCuts.RIGHT)
            this.rightpress=false;
        else if(e.getKeyCode()==KeyShortCuts.RUN)
            this.runpress=false;
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
    };

    public int getKeytyped() {
        return keytyped;
    }
}
