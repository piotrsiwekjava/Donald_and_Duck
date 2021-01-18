package object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends ObjectImage {

    private int hp;
    private boolean unbreakable;
    public Obstacle(Point position, double[] size, BufferedImage image, int hp, boolean unbreakable) {
        super(position, size, image);
        this.hp = hp;
        this.unbreakable=unbreakable;
    }

}
