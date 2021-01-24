package frames;

import object.unit.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerStatus {
    private Player player;
    private BufferedImage head;
    private BufferedImage weapon;
    private int liveBelt = 10;
    private int energyBelt = 10;
    private int superAttackBelt = 0;
    private int headsize = 20;
    private int weaponsize = 20;

    public PlayerStatus(Player player){
        this.player = player;
        this.head = player.getBodyParts()[1].getImage();

    }
    void loadStatus(Graphics2D g2d){
        loadImages(g2d);
        loadBelts(g2d);
    }
    private void loadImages(Graphics2D g2d){
        loadHead(g2d);
        loadWeapon(g2d);
    }
    private void loadHead(Graphics2D g2d){
        g2d.drawImage(head,20,20,null);
    }
    private void loadWeapon(Graphics2D g2d){
        g2d.drawImage(weapon, 150,20,null);
    }
    private void loadBelts(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.fillOval(500,20,10,10);
    }
    private void setWeapon(){
        String type = player
        this.weapon = player.getWeapon().;
    }
}
