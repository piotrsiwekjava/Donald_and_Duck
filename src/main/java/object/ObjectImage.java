package object;

import java.awt.*;
import java.awt.image.BufferedImage;

/** ObjectIm - Object with Image
 *
 */

public class ObjectImage extends ObjectGame {
    private BufferedImage image;
    private double[] odds_locate;
    private double[] odds_draw;
    private Point joinToRotate;
    private double angle;
    public ObjectImage(Point position, double[] size, BufferedImage image) {
        super(new Point(position), size);
        this.image = image;
        this.odds_locate = new double[]{0,0};
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

    public double[] getOdds_locate() {
        return odds_locate;
    }

    public void setOdds_locate(double x, double y) {
        this.odds_locate[0] = x;
        this.odds_locate[1] = y;
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

    public double[] getOdds_draw() {
        return odds_draw;
    }

    public void setOdds_draw(double x, double y) {
        this.odds_draw[0]=x;
        this.odds_draw[1]=y;
    }
}
