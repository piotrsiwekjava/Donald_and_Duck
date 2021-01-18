package object.factories;

import object.ObjectImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends ObjectImage {
    private int dmg;
    private double nX;
    private double nY;

    public Ammo(Point position, double[] size, BufferedImage image, int dmg, Point target, int speed ) {
        super(position, size, image);
        this.dmg = dmg;
        setFly(target,speed);
    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }
    private void setFly(Point target,int speed){
        double dX=(target.getX()-getPosition().getX());
        double dY=(target.getY()-getPosition().getY());
        double dC=(Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2)));
        if (dC==0) dC=0.01;
        nX = (dX*speed)/dC;
        nY = (dY*speed)/dC;
    }
    public void fly(){
        this.setXY(nX,nY);
    }

    public int getDmg() {
        return dmg;
    }
}

