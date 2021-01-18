package object;

import listeners.KeyGameListener;

import java.awt.*;

/** ObjectG - Object in Game
 *
 */
public abstract class ObjectGame {
    private Point position;
    private double [] size;
    private KeyGameListener keyListener;
    private Point posOnLevel;

    public ObjectGame(Point position, double[]size) {
        this.position = position;
        this.posOnLevel = new Point(position);
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

    public Point getPosOnLevel() {
        return posOnLevel;
    }

    public void setPosOnLevel(Point posOnLevel) {
        this.posOnLevel = posOnLevel;
    }

    public KeyGameListener getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KeyGameListener keyListener) {
        this.keyListener = keyListener;
    }

    public double[] getSize() {
        return size;
    }

    public void setSize(double[] size) {
        this.size = size;
    }


    public void setXY(double ix, double iy){
        this.position.setLocation(
                this.getPosition().getX()+ix,
                this.getPosition().getY()+iy
        );
        this.posOnLevel.setLocation(this.getPosition().getX()+ix,
                this.getPosition().getY()+iy);
    }
}
