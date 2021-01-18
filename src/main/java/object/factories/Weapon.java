package object.factories;

import object.ObjectImage;
import object.enumTypes.AmmoType;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon extends ObjectImage {
    private final AmmoType ammo_type;
    private final int MaxAmmoInMagazin;
    private final int reloadSpeed;
    private final int fireSpeed;
    private int leftAmmoinMagazin;
    private int allleftAmmo;
    private Point barrelTip;
    public boolean shooting;
    private Unit unit;



    public Weapon(AmmoType ammo_type, Point position, double[] size, BufferedImage image,
                  int maxAmmoInMagazin, int allleftAmmo, int reloadSpeed, int fireSpeed) {
        super(position, size, image);
        this.ammo_type = ammo_type;
        MaxAmmoInMagazin = maxAmmoInMagazin;
        this.allleftAmmo = allleftAmmo;
        this.reloadSpeed = reloadSpeed;
        this.fireSpeed=fireSpeed;
        this.barrelTip= position;
    }

    public synchronized void fire() {
        Point target = new Point(unit.getLookTarget().getPosition());
        if (leftAmmoinMagazin == 0) {
            try {
                reload();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(leftAmmoinMagazin>0) {
            leftAmmoinMagazin -= 1;
            ObjectsController.getInstance().addBullet(
                    AmmoFactory.create(ammo_type,
                            bulletStartPoint(), target)
            );
        }
    }

    private synchronized void reload() throws InterruptedException {
        shooting=false;
           while(allleftAmmo>0 && leftAmmoinMagazin!=MaxAmmoInMagazin) {
                leftAmmoinMagazin++;
                allleftAmmo--;
            }
            wait((long) reloadSpeed);
            shooting = true;
    }
    private synchronized Point bulletStartPoint(){
        Point p = new Point(barrelTip);
        return p;
    }

    public AmmoType getAmmo_type() {
        return ammo_type;
    }

    public int getLeftAmmoinMagazin() {
        return leftAmmoinMagazin;
    }

    public void setLeftAmmoinMagazin(int leftAmmoinMagazin) {
        this.leftAmmoinMagazin = leftAmmoinMagazin;
    }

    public int getAllleftAmmo() {
        return allleftAmmo;
    }

    public void setAllleftAmmo(int allleftAmmo) {
        allleftAmmo = allleftAmmo;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public Point getBarrelTip() {
        return barrelTip;
    }

    public void setBarrelTip() {
        this.barrelTip = barrelTip;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
