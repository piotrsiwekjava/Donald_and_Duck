package objectsController;

import level.Level;
import object.ObjectGame;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class MoveModule {
    private ObjectsController objectsController;
    private Level level;

    MoveModule() {
        this.objectsController = ObjectsController.getInstance();
    }

    Point randomPointMove(Unit unit){
        Point point = unit.getPosition();
        int yMin = (int) (Sizes.Screen_Height*0.5);
        int yMax = (int) (Sizes.Screen_Height*0.1);
        if(level!=null){
            yMin = (int) (level.getStreets()[0].getPosition().getY()-(Sizes.Soldier_Size[1]/2));
            yMax = (int) (level.getStreets()[0].getPosition().getY()+(level.getStreets()[0].getSize()[1]*0.6));
        }
        int attempt=0;
        while (attempt<100) {
            attempt++;
            int x = new Random().nextInt((int) Sizes.Screen_Width);
            int y = new Random().nextInt((int) yMax)+ yMin;
            if (y>yMax)y=yMax;
            int dx = x - (int)point.getX();
            int dy = y - (int)point.getY();

            if (checkTrack(x,y,dx,dy,point)) return new Point(x,y);
        }
        return point;
    }
    boolean checkTrack(int x, int y, int dx, int dy, Point point){

        if (dx<0) {
            for (int i = x; i < point.getX(); i++) {
                if (dy<0) {
                    for (int k = y; k < point.getY(); k++ ) {
                        if (isblocked(i,k)) return false;
                    }
                }
                else if (dy>0) {
                    for (int k = y; k > point.getY(); k--) {
                        if (isblocked(i,k))return false;
                    }
                }
            }
        }
        else if (dx>0) {
            for (int i = x; i > point.getX(); i--) {
                if (dy < 0) {
                    for (int k = y; k < point.getY(); k++) {
                        if (isblocked(i, k)) return false;
                    }
                } else if (dy > 0) {
                    for (int k = y; k > point.getY(); k--) {
                        if (isblocked(i, k)) return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean isblocked(int x, int y) {
        try {
            Set<ObjectGame> objectSet = objectsController.getObjectGSet();
            for (ObjectGame o : objectSet) {
                for (int i = 0; i < o.getSize()[0]; i++) {
                    for (int j = 0; j < o.getSize()[1]; j++) {
                        if (x == ((int) o.getPosition().getX() + i) && y == ((int) o.getPosition().getY() + j))
                            System.out.println("blocked");
                            return true;
                    }
                }
            }
        }
        catch (NullPointerException e){
            return false;
        }
        return false;
    }
    void setLevel(Level level){
        this.level=level;
    }
}
