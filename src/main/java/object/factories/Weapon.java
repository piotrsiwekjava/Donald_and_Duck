package object.factories;

import object.ObjectImage;
import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import sound.SoundPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Weapon extends ObjectImage {
    private final AmmoType ammo_type;
    private final int MaxAmmoInMagazin;
    private final int reloadSpeed;
    private final int fireSpeed;
    private final WeaponsType type;
    private int leftAmmoinMagazin;
    private int allleftAmmo;
    private Point barrelTip;
    public boolean shooting;
    private Unit unit;
    private double [] oddbulletTip;
    private boolean superweapon;


    public Weapon(WeaponsType type, AmmoType ammo_type, Point position, double[] size, BufferedImage image,
                  int maxAmmoInMagazin, int allleftAmmo, int reloadSpeed, int fireSpeed, Unit unit, boolean isSuperWeapon) {
        super(position, size, image);
        this.type=type;
        this.ammo_type = ammo_type;
        MaxAmmoInMagazin = maxAmmoInMagazin;
        this.allleftAmmo = allleftAmmo;
        this.reloadSpeed = reloadSpeed;
        this.fireSpeed=fireSpeed;
        this.barrelTip=position;
        this.unit = unit;
        this.superweapon = isSuperWeapon;
    }

    public synchronized void fire() {
        if (leftAmmoinMagazin == 0) {
                reload();
        }
        else if(leftAmmoinMagazin>0) {
            leftAmmoinMagazin -= 1;
            giveSound();
            ObjectsController.getInstance().addBullet(
                    AmmoFactory.create(ammo_type, this)
            );
        }
        if (superweapon)((Player)unit).setEnergySuperAttack(0);
    }

    protected synchronized void reload(){
        shooting=false;
        try {
            while (allleftAmmo > 0 && leftAmmoinMagazin != MaxAmmoInMagazin) {
                leftAmmoinMagazin++;
                allleftAmmo--;
            }
            wait((long) reloadSpeed);
            shooting = true;
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void triggerPull(){
        shooting = true;
        fire();
    }
    public void triggerRelease (){
        shooting = false;
    }

    public AmmoType getAmmo_type() {
        return ammo_type;
    }

    public int getLeftAmmoinMagazin() {
        return leftAmmoinMagazin;
    }

    public void setLeftAmmoinMagazin(int count) {
        this.leftAmmoinMagazin += count;
    }

    public void setAmmoInMagazin(int count) {
        this.leftAmmoinMagazin = count;
    }

    public int getAllleftAmmo() {
        return allleftAmmo;
    }

    public void setAllleftAmmo(int allLeftAmmo) {
        this.allleftAmmo = allLeftAmmo;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public Point getBarrelTip() {
        return barrelTip;
    }

    public void setBarrelTip(Point barrelTip) {
        this.barrelTip = barrelTip;
    }

    public void set_odds_from_barrelTip_to_Center(double x, double y) {
        this.oddbulletTip = new double[]{x,y};
    }

    public double[] getOddbulletTip() {
        return oddbulletTip;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public WeaponsType getWeapontype() {
        return type;
    }

    public boolean isSuperweapon() {
        return superweapon;
    }

    private void giveSound(){
        if (type.equals(WeaponsType.AK_47))
            SoundPlayer.getMainSoundPlayer().playNewMusicThread(5);
        else if (type.equals(WeaponsType.PISTOL))
            SoundPlayer.getMainSoundPlayer().playNewMusicThread(8);
    }
}
