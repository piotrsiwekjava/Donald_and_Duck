package object.unit;

import object.ObjectGame;

import java.awt.*;

public class Move_Look_Point extends ObjectGame {
    private Unit unit;

    public Move_Look_Point(Point position) {
        super(position, new double[]{0.001,0.001});
    }

    public Move_Look_Point(Point position,Unit unit) {
        super(position, new double[]{0.001, 0.001});
        this.unit = unit;
    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }

    public Unit getUnit() {
        return unit;
    }
}
