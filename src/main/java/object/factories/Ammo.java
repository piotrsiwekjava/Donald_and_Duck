package object.factories;

import object.ObjectGame;
import object.ObjectImage;
import object.enumTypes.AmmoType;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ammo extends ObjectImage {
    private Unit whoShoot;
    private AmmoType type;
    private int damage;
    private double speed;
    private double nX;
    private double nY;
    public boolean isBlocked;

    public Ammo(AmmoType type, Point position, double[] size, BufferedImage image, int damage, Point target, double speed, Unit whoShoot) {
        super(position, size, image);
        this.type = type;
        this.damage = damage;
        this.speed = speed;
        this.whoShoot = whoShoot;
        isBlocked = false;
        setFly(target,speed);

    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }
    void setFly(Point target, double speed){
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
        this.setXY(nX,nY);
    }

    private boolean check_if_you_hit(){
        return !ObjectsController.getInstance().checkTrack(this.getPosition(),new double[]{nX,nY},true);
    }
    void getDamageObject (){
        ObjectGame o = ObjectsController.getInstance().whoBlocked(this.getPosition(),new double[]{nX,nY});
        if ((o!=null && !o.equals(whoShoot)) || (o instanceof Obstacle && !(((Obstacle)o).isEffect()))) {
            isBlocked = true;o.getDamage(this.damage, new Point(this.getPosition()));
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getnY() {
        return nY;
    }

    public void setnY(double nY) {
        this.nY = nY;
    }

    public int getAmmoDamage() {
        return damage;
    }

    public Unit getWhoShoot() {
        return whoShoot;
    }

    public AmmoType getType() {
        return type;
    }
}

