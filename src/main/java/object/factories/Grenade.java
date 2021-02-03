package object.factories;

import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grenade extends Weapon{
    private int longPress;
    public Grenade(WeaponsType type, AmmoType ammo_type, Point position, double[] size, BufferedImage image, int maxAmmoInMagazin, int allleftAmmo, int reloadSpeed, int fireSpeed, Unit unit) {
        super(type, ammo_type, position, size, image, maxAmmoInMagazin, allleftAmmo, reloadSpeed, fireSpeed,unit);
        this.longPress=0;
    }
    private void throwGrenade(){
        Point target = new Point(getUnit().getLookTarget().getPosition());
        if (getLeftAmmoinMagazin() == 0) {
            try {
                this.reload();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(getLeftAmmoinMagazin()>0) {
            setLeftAmmoinMagazin(-1);
            ObjectsController.getInstance().addBullet(
                    AmmoFactory.create(getAmmo_type(), getBarrelTip(), target, getUnit().getSide(),getUnit())
            );
      }
    }

    @Override
    public synchronized void fire() {
        if (longPress<=3)this.longPress++;
    }

    @Override
    public void triggerRelease() {
        shooting=true;
        throwGrenade();
        shooting=false;
        this.longPress=0;
    }


    public int getLongPress() {
        return longPress;
    }
}
