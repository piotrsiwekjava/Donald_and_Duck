package object.factories;

import object.ObjectImage;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BodyPart extends ObjectImage {
    /**
     * courseMove - the direction of movement
     * 0-none, -1 -Left, 1 -Right
     */
    private int courseMove;
    private boolean isPlayer;
    private Point fieldOfWalking = new Point(0,0);
    public BodyPart(Point position, double[] size, BufferedImage image) {
        super(position, size,image);
        this.courseMove=1;
    }

    public int getCourseMove() {
        return courseMove;
    }

    public void setCourseMove() {
        this.courseMove *= -1;
    }
    public void setCourseMove(int courseMove) {
        this.courseMove = courseMove;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }
    private void setFieldOfWalking(double ix, double iy){
        fieldOfWalking.setLocation(fieldOfWalking.getX()+ix,fieldOfWalking.getY()+iy);
    }

    @Override
    public void setXY(double ix, double iy) {
        if (isPlayer) playerMove(ix,iy);
        else super.setXY(ix, iy);

    }

    private void playerMove(double ix, double iy){
        ix*=-1;
        iy*=-1;
        if (ix<0 && (fieldOfWalking.getX() < Sizes.X_MAX_MIN_WALK[0]))
            ix = 0;

        else if (ix>0 && (fieldOfWalking.getX() > Sizes.X_MAX_MIN_WALK[1]))
            ix = 0;
        setFieldOfWalking(ix,iy);
        this.getPosition().setLocation(this.getPosition().getX()+ix,
                this.getPosition().getY()+iy);
    }

}
