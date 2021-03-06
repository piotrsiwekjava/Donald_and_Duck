package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AmmoFactory{

    private static BufferedImage BULLETIMAGE;

    public static Ammo create(AmmoType type, Weapon weapon) {
        BufferedImage image = null;
        Unit whoShoot = weapon.getUnit();
        int side = whoShoot.getSideLooking();
        Point barrelTip = weapon.getBarrelTip();
        Point target = whoShoot.getLookTarget().getPosition();
        String imagePath = "";
        switch (type) {
            case A5MM: {
                imagePath = "weapons\\bL";
                break;
            }
            case A7MM: {
                imagePath = "weapons\\bL";
                break;
            }
            case MISSILE: {
                imagePath = "weapons\\bL";
                break;
            }
            case GRENADE: {
                imagePath = "weapons\\grenade";
                break;
            }
            case KONSTYTUCJA: {
                imagePath = "weapons\\konstytucja";
                break;
            }
            case PAPER: {
                imagePath = "weapons\\paper";
                break;
            }
        }
        if (imagePath.equals("weapons\\bL")) {
            if (BULLETIMAGE != null) image = BULLETIMAGE;
            else {
                image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);
                BULLETIMAGE = image;
            }
        }
        else image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);

        if (side == 1) image = ImageGetterAndChanger.getInstance().mirrorImage(image);

        Ammo ammo;

        switch (type) {
            case A5MM:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.A5MM, image, 20, (int) Sizes.RUN_Speed, whoShoot);
                break;
            case A7MM:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.A7MM, image, 30, (int) (Sizes.RUN_Speed * 1.5), whoShoot);
                break;
            case MISSILE:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.MISSILE, image, 100, (int) (Sizes.RUN_Speed * 1.3), whoShoot);
                break;
            case GRENADE:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Grenade, image, 500, (int) (Sizes.RUN_Speed * 0.5), whoShoot, 100, false);
                break;
            case KONSTYTUCJA:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Konstytucja, image, 300, (int) (Sizes.RUN_Speed * 0.5), whoShoot, 100, true);
                break;
            case PAPER:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Konstytucja, image, 200, (int) (Sizes.RUN_Speed * 0.5), whoShoot, 50, false);
                break;
            default:
                throw new UnsupportedOperationException("No such type");

        }
        if (ammo instanceof AmmoGrenade) {
            setGrenadesDistance(ammo,weapon);
        }
        return ammo;
    }
    public static Ammo createShardsPaper(AmmoType type, Point position, Point target,int side){
        BufferedImage image;
        String imagePath = "weapons\\paper";
        image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);
        if (side == 1) image = ImageGetterAndChanger.getInstance().mirrorImage(image);
        Ammo ammo;
        switch (type) {
            case PAPER:
                ammo = new AmmoGrenade(type, position, Sizes.Konstytucja, image, 200, (int) (Sizes.RUN_Speed * 0.5),
                        null, 50, false);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        setGrenadesDistance(ammo,null);
        return ammo;
    }
    private static void setGrenadesDistance(Ammo ammo, Weapon weapon){
        double eW = Sizes.Screen_Width / 1600;
        double lp;
        try {
             lp = (((Grenade) weapon).getStartPress() * eW / 1.5);
        }
        catch (Exception e){

            lp= new Random().nextInt(150)+50 * eW;
        }
        if (lp > 1000) lp = 1000*eW;
        AmmoType type = ammo.getType();
        ((AmmoGrenade) ammo).setDistanceBegin(lp);
    }

}
