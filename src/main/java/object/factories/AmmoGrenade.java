package object.factories;

import object.ObjectGame;
import object.enumTypes.AmmoType;
import object.enumTypes.ObstacleType;
import object.unit.Unit;
import objectsController.ObjectsController;
import sound.Mixer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AmmoGrenade extends Ammo {

    private int distanceBegin;
    private int distanceLeft;
    private boolean exploded;
    private Point target;
    private int sizeField_of_fire;
    private boolean clusterbomb;
    public AmmoGrenade(AmmoType type, Point position, double[] size, BufferedImage image, int damage, int speed, Unit whoShoot, int sizeField_of_fire, boolean isClusterBomb ) {
        super(type, position, size, image, damage, speed, whoShoot);
        this.target = whoShoot.getLookTarget().getPosition();
        this.exploded = false;
        this.sizeField_of_fire = sizeField_of_fire;
        this.clusterbomb = isClusterBomb;
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

    public void setDistanceBegin(double distance) {
        this.distanceBegin = (int)(distance);
        this.distanceLeft = distanceBegin;
        this.setSpeed(distanceBegin/50);
        this.setFly();
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
        giveExplosionSound();
        Obstacle obstacle = ObstacleFactory.create(ObstacleType.EXPLOSION,this.getPosition());
        ObjectsController obj = ObjectsController.getInstance();
        obj.addObstacle(obstacle);
        makeDamage();
        if (clusterbomb)
            clusterBombExploded();
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
                    og.getDamage(this.getAmmoDamage(),new Point(this.getPosition()));
                }
            }
        }

    }
    private void clusterBombExploded(){
        AmmoType ammoType=null;
        if (getAmmoType()==AmmoType.KONSTYTUCJA) ammoType= AmmoType.PAPER;
        int r = new Random().nextInt(4)+4;
        for (int i =0; i<r; i++){
            Point pTarget = randomPoint();
            int side = (int) (pTarget.getX() - this.getPosition().getX());
            if (side >0) side=1;
            else side=-1;
            Ammo ammo = AmmoFactory.createShardsPaper(ammoType,this.getPosition(),pTarget,side);
            addBulletToController(ammo);
        }
    }
    private Point randomPoint(){
        int wr = new Random().nextInt(500)-250;
        int hr = new Random().nextInt(500)-250;
        int wo = (int) this.getPosition().getX();
        int ho = (int) this.getPosition().getY();
        return new Point(wo-wr,ho-hr);
    }
    private AmmoType getAmmoType(){
        Unit u = this.getWhoShoot();
        Weapon w = u.getWeapon();
     return w.getAmmo_type();
    }
    private void addBulletToController(Ammo ammo){
        ObjectsController.getInstance().addBullet(ammo);
    }
    private void giveExplosionSound(){
        Mixer.getSecondPlayer().playNewMusicThread(15);
    }
}
