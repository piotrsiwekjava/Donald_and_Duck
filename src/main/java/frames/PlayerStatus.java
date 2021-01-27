package frames;

import object.ImageChanger;
import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.unit.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerStatus {
    private Player player;
    private Weapon weapon;
    private BufferedImage headIm;
    private BufferedImage weaponIm;
    private Font stringFont;
    private int liveBelt = 10;
    private int energyBelt = 10;
    private int superAttackBelt = 0;
    private int headsize = 20;
    private int weaponsize = 20;

    public PlayerStatus(Player player){
        this.player = player;
        this.weapon = player.getWeapon();
        this.headIm = player.getBodyParts()[1].getImage();
        setWeaponImage();
        setFont();
    }
    void loadStatus(Graphics2D g2d){
        loadImages(g2d);
        loadAmmo(g2d);
        loadBelts(g2d);
    }
    private void loadImages(Graphics2D g2d){
        loadHead(g2d);
        loadWeapon(g2d);
    }
    private void loadHead(Graphics2D g2d){
        g2d.drawImage(headIm,20,20,null);
    }
    private void loadWeapon(Graphics2D g2d){
        g2d.drawImage(weaponIm, 150,20,null);
    }
    private void loadAmmo(Graphics2D g2d){
        int ammoInMagazin = weapon.getLeftAmmoinMagazin();
        int leftAmmo = weapon.getAllleftAmmo();
        g2d.setFont(stringFont);
        g2d.drawString(""+ammoInMagazin,400,60);
        g2d.drawString(""+leftAmmo,400,100);
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
    public void changeWeapon(){
        this.weapon = player.getWeapon();
        setWeaponImage();
    }
    private void setWeaponImage(){
        WeaponsType type = weapon.getWeapontype();
        String path="";
        if (type==WeaponsType.PISTOL) path="psm";
        else if (type==WeaponsType.AK_47) path="ak47";
        else if (type==WeaponsType.BAZOOKA) path="bazooka";
        else if (type==WeaponsType.FIST) path="fist";
        this.weaponIm = ImageChanger.getInstance().getTransImg(
                "\\weapons\\"+path+"all");
//        this.weapon = player.getWeapon().getImage();
    }

    private void setFont(){
        stringFont = new Font( "Dialog", Font.BOLD, 50 );
    }
}