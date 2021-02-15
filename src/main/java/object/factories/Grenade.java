package object.factories;

import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grenade extends Weapon{
    private int longPress;
    public Grenade(WeaponsType type, AmmoType ammo_type, Point position, double[] size, BufferedImage image, int maxAmmoInMagazin, int allleftAmmo, int reloadSpeed, int fireSpeed, Unit unit, boolean isSuperWeapon) {
        super(type, ammo_type, position, size, image, maxAmmoInMagazin, allleftAmmo, reloadSpeed, fireSpeed,unit,isSuperWeapon);
        this.longPress=0;
    }
    private void throwGrenade() {
        if (getLeftAmmoinMagazin() == 0) {
            try {
                this.reload();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (getLeftAmmoinMagazin() > 0) {
            setLeftAmmoinMagazin(-1);
            ObjectsController.getInstance().addBullet(
                    AmmoFactory.create(getAmmo_type(),this)
            );
            if (this.isSuperweapon())((Player)this.getUnit()).setEnergySuperAttack(0);
        }
    }

    @Override
    public synchronized void fire() {
        if (longPress==0) longPress = ObjectsController.getInstance().getTime();
    }

    @Override
    public void triggerRelease() {
        System.out.println(longPress);
        longPress = ((ObjectsController.getInstance().getTime()) - longPress);
        shooting=true;
        throwGrenade();
        shooting=false;
        this.longPress=0;
    }


    public int getLongPress() {
        return longPress;
    }
}
