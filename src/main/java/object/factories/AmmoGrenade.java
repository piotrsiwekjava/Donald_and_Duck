package object.factories;

import object.unit.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoGrenade extends Ammo {

    private int distanceBegin;
    private int distanceLeft;
    private boolean exploded;
    private Point target;
    public AmmoGrenade(Point position, double[] size, BufferedImage image, int damage, Point target, int speed, Unit whoShoot) {
        super(position, size, image, damage, target, speed, whoShoot);
        this.target = target;
        this.exploded = false;
    }

    public void setDistanceBegin(int distance) {
        this.distanceBegin = distance*100;
        this.distanceLeft = distanceBegin;
        this.setSpeed(distanceBegin/50);
        this.setFly(this.target, this.getSpeed());
    }

    @Override
    public void fly() {
        System.out.println("speed "+getSpeed());
        this.distanceLeft-=getSpeed();
        System.out.println("Ammo grenades: distance left " + distanceLeft);
        System.out.println("Ammo grenades: distance begin " + distanceBegin);
        if (distanceLeft >0) {
            setPositionY();

            super.fly();
        }
        else makeExplosion();

    }

    private void setPositionY(){
        if (distanceLeft>distanceBegin/2)
            setnY(this.getnY()-1);
        else {
            setnY(this.getnY() + 2);
        }
    }
    public void makeExplosion (){
        exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }

}
