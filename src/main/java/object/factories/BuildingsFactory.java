package object.factories;

import object.ImageChanger;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BuildingsFactory {
    public static Building[] createBuldings() {
        int maxBuildings = (int)(Sizes.LEVELWidth/(Sizes.Screen_Width*0.5))+3;
        Building[] buildings = new Building[maxBuildings];
        int width=0;
        int hight= Sizes.LEVELHeight;
        for (int i=0;i<maxBuildings;i++){
            buildings[i] = buildBuilding(new Point(width,hight));
            width+=(Sizes.Screen_Width*0.5);
        }
        return buildings;
    }
    private static Building buildBuilding(Point point){
        Building building;
        BufferedImage image;
        String imagePath = "buildings\\b";
        int r = new Random().nextInt(8)+2;
        image = ImageChanger.getInstance().getTransImg(imagePath+r);
        double s = new Random().nextInt(100)+75;
        double scale = 1*(s/100);
        point = giveNewRandomPoint(point);
        building = new Building(point,image,scale);
        return building;
    }
    private static Point giveNewRandomPoint(Point point){
        Point nP = new Point(point);
        int x = new Random().nextInt(100)-50;
        int y = new Random().nextInt(100)+100;
        nP.setLocation(nP.getLocation().getX()+(Sizes.Screen_Width/50*(x/100)),
                nP.getLocation().getY()+(Sizes.Screen_Height/25*(y/100)));
        return nP;
    }
}
