package object.factories;

import object.ImageChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.WeaponsType;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WeaponsFactory{
    public static Weapon create(WeaponsType type, Point position, int leftAmmo) {
        BufferedImage image = null;
        String imagePath = "";
        switch (type){
            case PISTOL: imagePath = "weapons\\psm";
            case AK_47: imagePath = "weapons\\ak47";
            case BAZOOKA: imagePath = "weapons\\ak47";

        }

        image = ImageChanger.getInstance().getTransImg(imagePath);

        switch (type){
            case PISTOL:
                return new Weapon(AmmoType.A5MM, position, Sizes.Pistol, image,10,leftAmmo,2500,1000);
            case AK_47:
                return new Weapon(AmmoType.A7MM, position, Sizes.AK, image,30,leftAmmo,2500,400);
            case BAZOOKA:
                return new Weapon(AmmoType.MISSILE, position, Sizes.Bazooka, image,1,leftAmmo,10000,1000);
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
}
