package object.factories;

import object.ObjectGame;
import object.ObjectImage;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends ObjectImage {
    private int damage;
    private double nX;
    private double nY;
    public boolean isBlocked;

    public Ammo(Point position, double[] size, BufferedImage image, int damage, Point target, int speed) {
        super(position, size, image);
        this.damage = damage;
        isBlocked = false;
        setFly(target,speed);

    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }
    private void setFly(Point target, int speed){
        double dX=(target.getX()-getPosition().getX());
        double dY=(target.getY()-getPosition().getY());
        double dC=(Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2)));
        if (dC==0) dC=0.01;
        nX = (dX*speed)/dC;
        nY = (dY*speed)/dC;
    }
    public void fly(){
        if (check_if_you_hit()) {
            getDamageObject();

            this.setXY(nX,nY);
        }
        if (isBlocked) {
            System.out.println(isBlocked);
        }
        this.setXY(nX,nY);
    }

    private boolean check_if_you_hit(){
        return !ObjectsController.getInstance().checkTrack(this.getPosition(),new double[]{nX,nY});
    }
    private void getDamageObject (){
        ObjectGame o = ObjectsController.getInstance().whoBlocked(this.getPosition(),new double[]{nX,nY});
            isBlocked = true;
            o.getDamage(this.damage);
            System.out.println("Zadałem obrażenia");
//        }
    }

    public int getAmmoDamage() {
        return damage;
    }
}

