package object.factories;

import object.ImageChanger;
import object.enumTypes.AmmoType;
import object.unit.Unit;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoFactory{


    public static Ammo create(AmmoType type, Point barrelTip, Point target, int side, Unit whoShoot) {
        BufferedImage image = null;
        String imagePath = "";
        switch (type){
            case A5MM: imagePath = "weapons\\bL";
            case A7MM: imagePath = "weapons\\bL";
            case MISSILE: imagePath = "weapons\\bL";
        }
        image = ImageChanger.getInstance().getTransImg(imagePath);
        if (side==1) image = ImageChanger.getInstance().mirrorImage(image);

        Ammo ammo;

        switch (type) {
            case A5MM:
                ammo = new Ammo(new Point(barrelTip), Sizes.A5MM, image,10,target,(int) Sizes.RUN_Speed, whoShoot);
                break;
            case A7MM:
                ammo =  new Ammo(new Point(barrelTip), Sizes.A7MM, image,15,target,(int) Sizes.RUN_Speed, whoShoot);
                break;
            case MISSILE:
                ammo =  new Ammo(new Point(barrelTip), Sizes.MISSILE, image,100, target, (int) (Sizes.RUN_Speed*1.3), whoShoot);
                break;
            default:
                throw new UnsupportedOperationException("No such type");

        }

        return ammo;
    }
}
