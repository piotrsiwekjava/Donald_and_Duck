package object.factories;

import object.ObjectImage;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Street extends ObjectImage {

    public Street(Point position, BufferedImage image) {
        super(position, Sizes.STREETSSIZE, image);
    }
    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
        if(this.getPosition().getX()<(-Sizes.Screen_Width*0.25))
            this.setXY(Sizes.Screen_Width*1.5,0);
        if(this.getPosition().getX()>(Sizes.Screen_Width*1.25))
            this.setXY(-Sizes.Screen_Width*1.5,0);
    }
}
