package object.factories;

import object.ObjectImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Building extends ObjectImage {

    public Building(Point position, BufferedImage image, double scale) {
        super(position, buildSize(image,scale) , image);
        upImage();
    }
    private static double[] buildSize(BufferedImage image, double scale){
        double [] size = {0,0};
        size[0]=image.getWidth()*scale;
        size[1]=image.getHeight()*scale;
        return size;
    }
    private void upImage(){
        this.getPosition().setLocation(this.getPosition().getX(),
                this.getPosition().getY()-this.getSize()[1]);
    }
}
