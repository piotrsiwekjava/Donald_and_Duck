package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponsFactory{
    public static Weapon create(WeaponsType type, Point position, int leftAmmo, Unit unit) {
        BufferedImage image = null;
        String imagePath = "";
        switch (type) {
            case PISTOL: {
                imagePath = "weapons\\psm";
                break;
            }
            case AK_47: {
                imagePath = "weapons\\ak47";
                break;
            }
            case BAZOOKA: {
                imagePath = "weapons\\Bazooka";
                break;
            }
            case FIST: {
                imagePath = null;
                break;
            }
            case GRENADE: {
                imagePath = "weapons\\grenadeweap";
                break;
            }
            case KONSTYTUCJA: {
                imagePath = "weapons\\konstytucja";
                break;
            }
            case NOWEAPON: {
                imagePath = "weapons\\psm";
            }
        }
        ImageGetterAndChanger igc = ImageGetterAndChanger.getInstance();
        image = igc.getTransImg(imagePath);
        if ((unit instanceof Player) && ObjectsController.getInstance().isCanPlay() && unit.getSideLooking() == 1) image = igc.mirrorImage(image);


        switch (type){
            case NOWEAPON:
                return new Weapon(type, AmmoType.A5MM, position, new double[]{0.01, 0.01}, image, 1, 1,2500,1000,unit, false);
            case FIST:
                return new Weapon(type, AmmoType.A5MM, position, new double[]{0.01, 0.01}, image, 1, 1,2500,1000,unit, false);
            case PISTOL:
                return new Weapon(type,AmmoType.A5MM, position, Sizes.Pistol, image,10,leftAmmo,10000,2000,unit, false);
            case AK_47:
                return new Weapon(type,AmmoType.A7MM, position, Sizes.AK, image,30,leftAmmo,10000,1000,unit, false);
            case BAZOOKA:
                return new Weapon(type,AmmoType.MISSILE, position, Sizes.Bazooka, image,1,leftAmmo,10000,1000,unit, false);
            case GRENADE:
                return new Grenade(type, AmmoType.GRENADE, position, Sizes.Grenade,image,1,leftAmmo,100,1000,unit, false);
            case KONSTYTUCJA:
                return new Grenade(type, AmmoType.KONSTYTUCJA, position, Sizes.Grenade,image,1,leftAmmo,1,1000,unit, true);
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
}
