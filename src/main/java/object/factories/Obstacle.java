package object.factories;

import object.ObjectImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Obstacle extends ObjectImage {

    private int hp;
    private boolean unbreakable;
    private boolean effect;
    private String imagePath;
    private Point damagePoint;
    public Obstacle(Point position, double[] size, BufferedImage image, int hp, boolean unbreakable, boolean iseffect, String imagePath) {
        super(position, size, image);
        this.hp = hp;
        this.unbreakable=unbreakable;
        this.effect = iseffect;
        this.imagePath = imagePath;
    }

    @Override
    public void getDamage(int count, Point dmgPoint) {
        if(!unbreakable)
            this.damagePoint = dmgPoint;
            this.hp-=count;
    }

    public boolean isEffect() {
        return effect;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Point getDamagePoint() {
        return damagePoint;
    }
}
