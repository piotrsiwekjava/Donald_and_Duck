package object.factories;

import object.ObjectGame;
import object.enumTypes.ObstacleType;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoGrenade extends Ammo {

    private int distanceBegin;
    private int distanceLeft;
    private boolean exploded;
    private Point target;
    private int sizeField_of_fire;
    public AmmoGrenade(Point position, double[] size, BufferedImage image, int damage, Point target, int speed, Unit whoShoot, int sizeField_of_fire ) {
        super(position, size, image, damage, target, speed, whoShoot);
        this.target = target;
        this.exploded = false;
        this.sizeField_of_fire = sizeField_of_fire;
    }

    @Override
    public void fly() {
        this.distanceLeft-=getSpeed();
        if (isBlocked && (distanceLeft>distanceBegin*0.75 || distanceLeft<distanceBegin*0.25)) makeExplosion();
        if (distanceLeft >0) {
            setPositionY();
            super.fly();
        }
        else makeExplosion();

    }

    @Override
    void getDamageObject() {
    }

    public void setDistanceBegin(int distance) {
        this.distanceBegin = distance*300;
        this.distanceLeft = distanceBegin;
        this.setSpeed(distanceBegin/50);
        this.setFly(this.target, this.getSpeed());
    }

    private void setPositionY(){
        if (distanceLeft>distanceBegin*0.5) {
            setnY(this.getnY() - 1);
        }
        else {
            setnY(this.getnY() + 2.66);
        }
    }
    public void makeExplosion (){
        Obstacle obstacle = ObstacleFactory.create(ObstacleType.EXPLOSION,this.getPosition());
        ObjectsController obj = ObjectsController.getInstance();
        obj.addObstacle(obstacle);
        makeDamage();
        exploded = true;
    }

    public boolean isExploded() {
        return exploded;
    }

    private void makeDamage(){
        ObjectsController objectsController = ObjectsController.getInstance();
        for (ObjectGame og: objectsController.getObjectGSet()){
            double x = og.getPosition().getX();
            double xown = this.getPosition().getX();
            if (x>(xown-sizeField_of_fire)&& x<(xown+sizeField_of_fire)) {
                double y = og.getPosition().getX();
                double yown = this.getPosition().getX();
                if (y>(yown-sizeField_of_fire)&& y<(yown+sizeField_of_fire)) {
                    og.getDamage(this.getAmmoDamage());
                }
            }
        }

    }

}
