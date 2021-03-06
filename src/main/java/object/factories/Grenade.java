package object.factories;

import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Grenade extends Weapon{
    private int startPress;
    public Grenade(WeaponsType type, AmmoType ammo_type, Point position, double[] size, BufferedImage image, int maxAmmoInMagazin, int allleftAmmo, int reloadSpeed, int fireSpeed, Unit unit, boolean isSuperWeapon) {
        super(type, ammo_type, position, size, image, maxAmmoInMagazin, allleftAmmo, reloadSpeed, fireSpeed,unit,isSuperWeapon);
        this.startPress =0;
        this.setSize(new double[]{0.1,0.1});
    }
    private void throwGrenade() {
        if (getLeftAmmoinMagazin() == 0) {
                this.reload();
        } else if (getLeftAmmoinMagazin() > 0) {
            removeGrenadeFromHand();
            createNewGrenade();
            if (this.isSuperweapon()) removeSuperEnergy();
        }
    }

    @Override
    protected synchronized void reload() {
        super.reload();

        double [] s = {0.1,0.1};
        if ((getLeftAmmoinMagazin()+getAllleftAmmo())>0) {
            if (this.getWeapontype().equals(WeaponsType.GRENADE))
                s = Sizes.Grenade;
            else if (this.getWeapontype().equals(WeaponsType.KONSTYTUCJA))
                s = Sizes.Konstytucja;
        }
        this.setSize(s);
    }

    @Override
    public synchronized void fire() {
        if (startPress ==0) startPress = ObjectsController.getInstance().getTime();
    }

    @Override
    public void triggerRelease() {
        startPress = ((ObjectsController.getInstance().getTime()) - startPress);
        shooting=true;
        throwGrenade();
        shooting=false;
        this.startPress =0;
    }

    private void removeGrenadeFromHand(){
        this.setSize(new double[]{0.1,0.1});
        setLeftAmmoinMagazin(-1);
    }
    private void createNewGrenade(){
        ObjectsController.getInstance().addBullet(
                AmmoFactory.create(getAmmo_type(),this)
        );
    }
    private void removeSuperEnergy(){
        ((Player)this.getUnit()).setEnergySuperAttack(0);
    }

    public int getStartPress() {
        return startPress;
    }

    public int getCurrentPress() {
        if (startPress !=0) {
            return currentPressIsIncreasingTo1000();
        }
        return 0;
    }
    private int currentPressIsIncreasingTo1000() {
        double eW = Sizes.Screen_Width / 1600;
        int currentPress = ( int)((ObjectsController.getInstance().getTime() - startPress) * eW / 100);
        if (currentPress>1000)currentPress=1000;
        return currentPress;
    }

}
