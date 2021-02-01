package object.factories;

import object.ImageGetterAndChanger;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StreetsFactory {
    public static Street[] create(String name) {
        BufferedImage image = null;
        String imagePath = "level\\"+name+"street";

        Street[] streets = new Street[6];
        int width=0;
        int hight=Sizes.LEVELHeight;
        int j=1;
        for (int i=0;i<6;i++){
            if (i==4)j=1;
            image = ImageGetterAndChanger.getInstance().getImage(imagePath+(j++));
            streets[i]= new Street(new Point(width,hight),image);
            width+=(Sizes.Screen_Width*0.25);

        }
        return streets;
    }
}