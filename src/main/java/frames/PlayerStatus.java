package frames;

import object.ImageChanger;
import object.enumTypes.WeaponsType;
import object.unit.player.Player;
import settings.Pathes_and_Links;

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
        setWeaponImage();
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
       redBelt(g2d);
       greenBelt(g2d);
       blueBelt(g2d);
    }
    private void redBelt(Graphics2D g2d){
        g2d.setColor(Color.RED);
        for (int i=0, k=0; i<player.getHp();i+=50,k+=20) {
            g2d.fillRect(k+500, 20, 30, 20);
        }
    }
    private void greenBelt(Graphics2D g2d){
        g2d.setColor(Color.GREEN);
        for (int i=0, k=0; i<player.getEnergy();i+=2,k+=20) {
            g2d.fillRect(k+500, 60, 30, 20);
        }
    }
    private void blueBelt(Graphics2D g2d){
        g2d.setColor(Color.BLUE);
        for (int i=0, k=0; i<player.getEnergySuperAttack();i+=2,k+=20) {
            g2d.fillRect(k+500, 100, 30, 20);
        }
    }
    private void setWeaponImage(){
        WeaponsType type = player.getWeapon().getWeapontype();
        String path="";
        if (type==WeaponsType.PISTOL) path="psm";
        else if (type==WeaponsType.AK_47) path="ak47";
        else if (type==WeaponsType.BAZOOKA) path="bazooka";
        else if (type==WeaponsType.FIST) path="fist";
        this.weapon = ImageChanger.getInstance().getTransImg(
                "\\weapons\\"+path+"all");
//        this.weapon = player.getWeapon().getImage();
    }
}
