package object.factories;

import object.ObjectImage;
import objectsController.ObjectsController;
import sound.Mixer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
        giveSound(12);
        if(!unbreakable) {
            this.damagePoint = dmgPoint;
            this.hp -= count;
            if (this.hp<=0)
                giveSound(20);
                ObjectsController.getInstance().removeThisObject(this);
        }
    }

    private void giveSound(int action) {
        int a= action;
        if (a != 20) a = new Random().nextInt(3) + 12;
        Mixer.getSecondPlayer().playNewMusicThread(a);
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
