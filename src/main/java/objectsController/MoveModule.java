package objectsController;

import level.Level;
import object.ObjectGame;
import object.factories.Ammo;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;
import java.util.Random;
import java.util.Set;

public class MoveModule {
    private Level level;

    MoveModule() {
    }

    synchronized Point randomPointMove(){
        int yMax = (int) (Sizes.Screen_Height*0.5);
        int yMin = (int) (Sizes.Screen_Height*0.1);
        if(level!=null) {
            yMin = (int) (level.getStreets()[0].getPosition().getY() - (Sizes.Soldier_Size[1] / 2));
            yMax = (int) (level.getStreets()[0].getPosition().getY() + (level.getStreets()[0].getSize()[1] * 0.6));
        }
            int x = new Random().nextInt((int) Sizes.Screen_Width);
            int y = new Random().nextInt((int) yMax)+ yMin;
            if (y>yMax)y=yMax;
        System.out.println("move module: point move"+x +"/"+y);
        return new Point(x,y);
    }
    synchronized boolean checkTrack(Point own, double[] doubles, boolean isBullet){
        int spaceSize=20;
        if (isBullet)spaceSize=100;
        ObjectsController objectsController = ObjectsController.getInstance();
        try {
            for (ObjectGame o : objectsController.getObjectGSet()) {

                int xo = (int) o.getPosition().getX() - 10;
                int xd = (int) (xo + (o.getSize()[0]*(spaceSize)));
                int ownXd = (int) (own.getX()+ (int)doubles[0]);
                if (ownXd>=xo && ownXd<=xd){
                    int yo = (int) o.getPosition().getY() - 10;
                    int yd = (int) (yo + (o.getSize()[1]*(spaceSize*2.5)));
                    int ownYd = (int) (own.getY()+ (int)doubles[1]);
                    if (ownYd>=yo && ownYd<=yd) {
                        return false;
                    }
                }
            }
        }
        catch (NullPointerException e){
            System.out.println("Przerywam checkTrack");
            return true;
        }
        return true;
    }
    synchronized ObjectGame if_Blocked_GiveBack_Responsible_Object(Point own, double[] doubles){
        ObjectsController objectsController = ObjectsController.getInstance();
        for (ObjectGame o : objectsController.getObjectGSet()) {
            int xo = (int) o.getPosition().getX() - 10;
            int xd = (int) (xo + (o.getSize()[0]*100));
            int ownXd = (int) (own.getX()+ (int)doubles[0]);
            if (ownXd>=xo && ownXd<=xd){
                int yo = (int) o.getPosition().getY() - 10;
                int yd = (int) (yo + (o.getSize()[1]*250));
                int ownYd = (int) (own.getY()+ (int)doubles[1]);
                if (ownYd>=yo && ownYd<=yd) return o;
            }
        }
        return null;
    }

    void setLevel(Level level){
        this.level=level;
    }
}
