package object.factories;

import object.ImageChanger;
import object.enumTypes.AmmoType;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoFactory{


    public static Ammo create(AmmoType type, Point barrelTip, Point target) {
        BufferedImage image = null;
        String imagePath = "";
        switch (type){
            case A5MM: imagePath = "weapons\\bL";
            case A7MM: imagePath = "weapons\\bL";
            case MISSILE: imagePath = "weapons\\bL";
        }
        image = ImageChanger.getInstance().getTransImg(imagePath);
        Ammo ammo;

        switch (type) {
            case A5MM:
                ammo = new Ammo(new Point(barrelTip), Sizes.A5MM, image,10,target,(int) Sizes.RUN_Speed);
                break;
            case A7MM:
                ammo =  new Ammo(new Point(barrelTip), Sizes.A7MM, image,15,target,(int) Sizes.RUN_Speed);
                break;
            case MISSILE:
                ammo =  new Ammo(new Point(barrelTip), Sizes.MISSILE, image,100, target, (int) (Sizes.RUN_Speed*1.3));
                break;
            default:
                throw new UnsupportedOperationException("No such type");

        }

        return ammo;
    }
}
