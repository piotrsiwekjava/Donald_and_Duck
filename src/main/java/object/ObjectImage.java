package object;

import java.awt.*;
import java.awt.image.BufferedImage;

/** ObjectIm - Object with Image
 *
 */

public class ObjectImage extends ObjectGame {
    private BufferedImage image;
    private double[] odds_center_of_rotation;
    private double[] odds_draw;
    private Point joinToRotate;
    private double angle;
    public ObjectImage(Point position, double[] size, BufferedImage image) {
        super(new Point(position), size);
        this.image = image;
        this.odds_center_of_rotation = new double[]{0,0};
        this.odds_draw = new double[]{0,0};
        angle = 0;
        joinToRotate = position;
    }
    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public double[] getOdds_center_of_rotation() {
        return odds_center_of_rotation;
    }

    public void set_Odds_center_of_rotation(double x, double y) {
        this.odds_center_of_rotation[0] = x;
        this.odds_center_of_rotation[1] = y;
    }

    public void setCenter_of_rotation(){
        this.setXY(this.getOdds_center_of_rotation()[0],this.getOdds_center_of_rotation()[1]);
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }


    public Point getJoinToRotate() {
        return joinToRotate;
    }

    public void setJoinToRotate(Point joinToRotate) {
        this.joinToRotate = joinToRotate;
    }

    public void changePoints(Point point){
        this.getPosition().setLocation(point);
        this.joinToRotate = point;
    }

    public double[] getOdds_draw() {
        return odds_draw;
    }

    public void setOdds_draw(double x, double y) {
        this.odds_draw[0]=x;
        this.odds_draw[1]=y;
    }
}
