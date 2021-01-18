package object.unit;

import object.ObjectGame;

import java.awt.*;

public class Move_Look_Target extends ObjectGame {
    public Move_Look_Target(Point position) {
        super(position, new double[]{0.001,0.001});
    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }
}
