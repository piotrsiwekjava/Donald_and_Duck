package object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends ObjectImage{
    private String name;
    public Item(String name, Point position, double[] size, BufferedImage image) {
        super(position, size, image);
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
