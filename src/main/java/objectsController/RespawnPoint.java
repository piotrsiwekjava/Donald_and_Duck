package objectsController;

import object.ObjectGame;
import settings.Sizes;

import java.awt.*;

public class RespawnPoint extends ObjectGame {
    public RespawnPoint(int wight, int high) {
        super(new Point((int) (Sizes.Screen_Width*1.1+wight), high), new double[]{0.001,0.001});
    }
    @Override
    public void setXY(double ix, double iy) {
        super.setXY(0, iy);
    }
}
