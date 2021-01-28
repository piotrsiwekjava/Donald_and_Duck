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

    synchronized Point randomPointMove(Unit unit){
        int yMax = (int) (Sizes.Screen_Height*0.5);
        int yMin = (int) (Sizes.Screen_Height*0.1);
        if(level!=null) {
            yMin = (int) (level.getStreets()[0].getPosition().getY() - (Sizes.Soldier_Size[1] / 2));
            yMax = (int) (level.getStreets()[0].getPosition().getY() + (level.getStreets()[0].getSize()[1] * 0.6));
        }
            int x = new Random().nextInt((int) Sizes.Screen_Width);
            int y = new Random().nextInt((int) yMax)+ yMin;
            if (y>yMax)y=yMax;

        return new Point(x,y);
    }
    synchronized boolean checkTrack(Point own, double[] doubles){
        ObjectsController objectsController = ObjectsController.getInstance();
        try {
            for (ObjectGame o : objectsController.getObjectGSet()) {
                int xo = (int) o.getPosition().getX() - 1;
                int yo = (int) o.getPosition().getY() - 1;
                int xd = (int) (xo + (o.getSize()[0]) + 1);
                int yd = (int) (yo + (o.getSize()[1]) + 1);
                for (; xo <= xd; xo++) {
                    if ((own.getX() + (int)doubles[0]) == xo) {
                        System.out.println("blokada1");
                        for (; yo <= yd; yo++) ;
                        {
                            if ((own.getY() + (int)doubles[1]) == yo)
                                System.out.println("blokada2");
                                return false;

                        }
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
            double xo = o.getPosition().getX()-1;
            double yo = o.getPosition().getY()-1;
            int xd = (int)(xo + o.getSize()[0]+1);
            int yd = (int)(yo + o.getSize()[1]*2+1);
            for(;xo<=xd; xo++){
                if ((own.getX() + doubles[0])==xo) {
                    return o;
//                        for (; yo <= yd ; yo++) ;
//                        {
//                            if ((own.getY() + doubles[1])==yo)
//                                return o;
//
//                        }
                }
            }
        }
        return null;
    }

    void setLevel(Level level){
        this.level=level;
    }
}
