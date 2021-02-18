package frames;

import object.ImageGetterAndChanger;
import object.enumTypes.WeaponsType;
import object.factories.Grenade;
import object.factories.Weapon;
import object.unit.player.Collector;
import object.unit.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerStatus {
    private Player player;
    private Weapon weapon;
    private BufferedImage headIm;
    private BufferedImage weaponIm;
    private Font stringFont;
    private int blueBeltFlashes;

    public PlayerStatus(Player player){
        this.player = player;
        this.weapon = player.getWeapon();
        this.headIm = player.getBodyParts()[1].getImage();
        setWeaponImage();
        setFont();
        blueBeltFlashes=0;
    }
    void loadStatus(Graphics2D g2d){
        loadImages(g2d);
        loadAmmo(g2d);
        loadBelts(g2d);
        loadPoints(g2d);
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
        if (weapon.getLeftAmmoinMagazin()>0 && weapon instanceof Grenade){
            int cp = ((Grenade)weapon).getCurrentPress();
            for(int i=0, r=250, g=250,b=0;i<cp;i++,g-=30,b+=0) {
                if (g<0) break;
                g2d.setColor(new Color(r,g,b));
                System.out.println(g2d.getColor());
                g2d.fillOval(150 + (i * 10), 150, 30, 20);
            }
        }
    }
    private void loadAmmo(Graphics2D g2d){
        int ammoInMagazin = weapon.getLeftAmmoinMagazin();
        int leftAmmo = weapon.getAllleftAmmo();
        g2d.setFont(stringFont);
        g2d.setColor(Color.WHITE);
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
        int hp = player.getHp()*10;
        for (int i=0, k=0; i<hp;i+=50,k+=20) {
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
        int esa = player.getEnergySuperAttack();
        if (esa>=100 && blueBeltFlashes<4) {
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(497,97,1016,26);

        }
        g2d.setColor(Color.BLUE);
        for (int i=0, k=0; i<esa;i+=2,k+=20) {
            g2d.fillRect(k+500, 100, 30, 20);
        }
        blueBeltFlashes++;
        if (blueBeltFlashes ==6)blueBeltFlashes=0;
    }
    private void loadPoints(Graphics2D g2d){
        Collector collector = Collector.getInstance();
        int points = collector.getAmount_of_points();
        g2d.setFont(stringFont);
        g2d.setColor(Color.BLACK);
        g2d.drawString(""+points,25,200);
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
        else if (type==WeaponsType.GRENADE) path="grenade";
        else if (type==WeaponsType.KONSTYTUCJA) path="konstytucja";
        this.weaponIm = ImageGetterAndChanger.getInstance().getTransImg(
                "\\weapons\\"+path+"all");
    }

    private void setFont(){
        stringFont = new Font( "Dialog", Font.BOLD, 50 );
    }
}
