package object;

import listeners.KeyGameListener;

import java.awt.*;

/** ObjectG - Object in mainGame.Game
 *
 */
public abstract class ObjectGame {
    private Point position;
    private double [] size;
    private KeyGameListener keyListener;

    public ObjectGame(Point position, double[]size) {
        this.position = position;
        this.size = size;
        keyListener= KeyGameListener.getInstance();
        keyListener.registerObjectG(this);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public double[] getSize() {
        return size;
    }

    public void setSize(double[] size) {
        this.size = size;
    }

    public void setXY(double ix, double iy){
        double x = this.getPosition().getX();
        double y = this.getPosition().getY();
        this.position.setLocation(x+ix,y+iy);
    }
    public void getDamage(int count, Point dmgPoint){}
}
