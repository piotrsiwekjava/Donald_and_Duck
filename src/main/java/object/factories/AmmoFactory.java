package object.factories;

import object.ImageChanger;
import object.enumTypes.AmmoType;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AmmoFactory{


    public static Ammo create(AmmoType type, Point position, Point target) {
        BufferedImage image = null;
        String imagePath = "";
        switch (type){
            case A5MM: imagePath = "weapons\\bL";
            case A7MM: imagePath = "weapons\\bL";
            case MISSILE: imagePath = "weapons\\bL";

        }
        image = ImageChanger.getInstance().getTransImg(imagePath);

        switch (type) {
            case A5MM:
                return new Ammo(new Point(position), Sizes.A5MM, image,10,target,(int) Sizes.RUN_Speed);
            case A7MM:
                return new Ammo(new Point(position), Sizes.A7MM, image,15,target,(int) Sizes.RUN_Speed);
            case MISSILE:
                return new Ammo(new Point(position), Sizes.MISSILE, image,100, target, (int) (Sizes.RUN_Speed*1.3));
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
}
