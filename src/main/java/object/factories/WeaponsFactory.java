package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import object.unit.Unit;
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
        }

        image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);

        switch (type){
            case FIST:
                return new Weapon(type, AmmoType.A5MM, position, new double[]{0.01, 0.01}, null, Integer.MAX_VALUE, Integer.MAX_VALUE,2500,1000,unit);
            case PISTOL:
                return new Weapon(type,AmmoType.A5MM, position, Sizes.Pistol, image,10,leftAmmo,10000,1000,unit);
            case AK_47:
                return new Weapon(type,AmmoType.A7MM, position, Sizes.AK, image,30,leftAmmo,10000,400,unit);
            case BAZOOKA:
                return new Weapon(type,AmmoType.MISSILE, position, Sizes.Bazooka, image,1,leftAmmo,10000,1000,unit);
            case GRENADE:
                return new Grenade(type, AmmoType.GRENADE, position, Sizes.Grenade,image,1,leftAmmo,1,1000,unit);
            case KONSTYTUCJA:
                return new Grenade(type, AmmoType.KONSTYTUCJA, position, Sizes.Grenade,image,1,leftAmmo,1,1000,unit);
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
}
