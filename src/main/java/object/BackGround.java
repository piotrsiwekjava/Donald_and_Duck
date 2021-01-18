package object;

import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackGround extends ObjectImage {
    public BackGround(BufferedImage image) {
        super(new Point(0,(int)(-Sizes.Screen_Height*0.1)), new double[]{Sizes.Screen_Width,image.getHeight()*Sizes.Screen_Width/image.getWidth()}, image);
    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(0, iy);
    }
}
