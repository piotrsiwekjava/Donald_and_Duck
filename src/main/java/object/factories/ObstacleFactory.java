package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.ObstacleType;
import settings.Sizes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObstacleFactory {
    public static Obstacle create(ObstacleType type, Point position) {
        Obstacle obstacle;
        BufferedImage image = null;
        String imagePath = "";
        switch (type) {
            case EXPLOSION: {
                imagePath = "effects\\e11";
                break;
            }
            case LITTLEEXPLOSION: {
                imagePath = "effects\\e11";
                break;
            }
            case BARRELS: {
                imagePath = "obstacle\\barrels";
                break;
            }
        }

        image = ImageGetterAndChanger.getInstance().getTransImg(imagePath);

        switch (type) {
            case EXPLOSION: {
                obstacle = new Obstacle(position, new double[]{Sizes.SIZE * 500, Sizes.SIZE * 500}, image, 100, true, true, imagePath);
                break;
            }
            case LITTLEEXPLOSION: {
                obstacle = new Obstacle(position, new double[]{Sizes.SIZE * 250, Sizes.SIZE * 250}, image, 100, true, true, imagePath);
                break;
            }
            case BARRELS: {
                obstacle = new Obstacle(position, new double[]{Sizes.SIZE * 100, Sizes.SIZE * 220}, image, 100, false, false, imagePath);
                break;
            }
            default:
                throw new UnsupportedOperationException("No such type");
        }
        obstacle.setOdds_draw(-obstacle.getSize()[0]*0.5,-obstacle.getSize()[1]*0.5);
        return obstacle;
    }
}
