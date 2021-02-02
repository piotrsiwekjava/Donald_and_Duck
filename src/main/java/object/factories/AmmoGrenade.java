package object.factories;

import object.unit.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoGrenade extends Ammo {

    private int longPress;
    private int distanceBegin;
    private int distanceLeft;
    private double additionHigh;
    public AmmoGrenade(Point position, double[] size, BufferedImage image, int damage, Point target, int speed, Unit whoShoot) {
        super(position, size, image, damage, target, speed, whoShoot);
        Grenade grenade = (Grenade)whoShoot.getWeapon();
        this.longPress = grenade.getLongPress();
        this.distanceBegin = longPress*750;
        this.distanceLeft = this.distanceBegin;
        this.setSpeed(longPress/10);
        this.additionHigh=0;
        System.out.println("Ammo Granade: "+ longPress);
    }

    @Override
    public void fly() {
        if (distanceLeft >0) {
            super.fly();
            setPositionY();
            setnY(this.getnY()+this.additionHigh);
            this.distanceLeft-=getSpeed();
        }

    }

    public void setPositionY(){
        if (distanceLeft>=(distanceBegin/2))
            this.additionHigh++;
        else this.additionHigh--;
        if (additionHigh<0) additionHigh=0;
    }
}
