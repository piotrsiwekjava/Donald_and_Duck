package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponsFactory{
    public static Weapon create(WeaponsType type, Point position, int leftAmmo) {
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
        }

        image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);

        switch (type){
            case FIST:
                return new Weapon(type, AmmoType.A5MM, position, new double[]{0.01, 0.01}, null, Integer.MAX_VALUE, Integer.MAX_VALUE,2500,1000);
            case PISTOL:
                return new Weapon(type,AmmoType.A5MM, position, Sizes.Pistol, image,10,leftAmmo,2500,1000);
            case AK_47:
                return new Weapon(type,AmmoType.A7MM, position, Sizes.AK, image,30,leftAmmo,2500,400);
            case BAZOOKA:
                return new Weapon(type,AmmoType.MISSILE, position, Sizes.Bazooka, image,1,leftAmmo,10000,1000);
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
}
