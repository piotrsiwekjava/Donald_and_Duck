package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoFactory{


    public static Ammo create(AmmoType type, Weapon weapon) {
        BufferedImage image = null;
        Unit whoShoot = weapon.getUnit();
        int side = whoShoot.getSide();
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
        image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);
        if (side==1) image = ImageGetterAndChanger.getInstance().mirrorImage(image);

        Ammo ammo;

        switch (type) {
            case A5MM:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.A5MM, image, 10, target, (int) Sizes.RUN_Speed, whoShoot);
                break;
            case A7MM:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.A7MM, image, 15, target, (int) (Sizes.RUN_Speed * 1.5), whoShoot);
                break;
            case MISSILE:
                ammo = new Ammo(type, new Point(barrelTip), Sizes.MISSILE, image, 100, target, (int) (Sizes.RUN_Speed * 1.3), whoShoot);
                break;
            case GRENADE:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Grenade, image, 500, target, (int) (Sizes.RUN_Speed * 0.5), whoShoot,100,false);
                break;
            case KONSTYTUCJA:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Konstytucja, image, 300, target, (int) (Sizes.RUN_Speed * 0.5), whoShoot, 100, true);
                break;
            case PAPER:
                ammo = new AmmoGrenade(type, new Point(barrelTip), Sizes.Konstytucja, image, 200, target, (int) (Sizes.RUN_Speed * 0.5), whoShoot, 50,false);
                break;
            default:
                throw new UnsupportedOperationException("No such type");

        }
        if (ammo instanceof AmmoGrenade) {
            int lp = ((Grenade)weapon).getLongPress()/10;
            if (type.equals(AmmoType.PAPER))((AmmoGrenade) ammo).setDistanceBegin(0.75);
                else ((AmmoGrenade) ammo).setDistanceBegin(lp);

        }
        return ammo;
    }
}
