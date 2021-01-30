package frames;

import level.Level;
import object.BackGround;
import object.ObjectGame;
import object.ObjectImage;
import object.Obstacle;
import object.factories.*;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.Sizes;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class Drawer {
    private JPanel panel;
    private ObjectsController objectsController;
    private Graphics2D g2d;
    private Level level;
    private PlayerStatus playerStatus;
    public Drawer(JPanel panel){
        objectsController = ObjectsController.getInstance();
        this.panel = panel;
        this.level = new Level(2,"desert");
    }
    public void drawAll(Graphics2D g2d){
        this.g2d = g2d;
        drawLevel();
        drawObjects();
        draw_Bullets();
        playerStatus.loadStatus(g2d);
        for (int i=0; i<=16; i++){
//            for (int j=0; i<8;j++);
                g2d.fillRect(100*i,400,10,10);
        }
    }
    private void drawLevel(){
        BackGround back = level.getBackground();
        drawObject(back);
        for (Street street: level.getStreets())
            drawObject(street);
        for (Building building: level.getBuildings())
            drawObject(building);
    }

    private void drawObjects(){

        for (ObjectGame og : objectsController.getObjectGSet()){

            if (og instanceof Unit) {
                drawUnits((Unit)og);
            }
            else if(og instanceof Obstacle){
                drawObstacle((Obstacle) og);
            }
        }
    }
    private void drawUnits(Unit unit){
        int x = (int)unit.getPosition().getX();
        int xm = Sizes.Screen_Width;
        if (x>(-xm*0.1) && x<(xm*1.1))
            drawUnit(unit);
    }
    private void drawUnit(Unit unit){
        for (BodyPart bp: unit.getBodyParts()){
            drawObjectImage(bp);
        }
        drawObjectImage(unit.getWeapon());
    }
    private void draw_Bullets(){
        for (Ammo ammo: objectsController.getBulletSet()) {
            drawObjectImage(ammo);
        }
    }
    private void drawObjectImage(ObjectImage o){
        if(o.getAngle()!=0)rotateAndDraw(o);
        else drawObject(o);
    }
    private void drawObstacle(Obstacle ob){
            int x = (int)ob.getPosition().getX();
            int xm = Sizes.Screen_Width;
            if (x>(-xm*0.1) && x<(xm*1.1))
                drawObject(ob);
    }
    private void drawObject (ObjectImage o){
        g2d.drawImage( o.getImage(),
                (int) ((o.getPosition().getX())+o.getOdds_draw()[0]),
                (int) ((o.getPosition().getY())+o.getOdds_draw()[1]),
                (int) o.getSize()[0],
                (int) o.getSize()[1],
                panel);
    }
    private void rotateAndDraw(ObjectImage o){
        double theta = ((Math.PI * 2) / 360) * o.getAngle();
        g2d.rotate(theta,o.getJoinToRotate().getX(),o.getJoinToRotate().getY());
        drawObject(o);
        g2d.rotate(-theta,o.getJoinToRotate().getX(),o.getJoinToRotate().getY());
    }
    public void loadPlayerStatus(){
        Player player = ObjectsController.getInstance().getPlayer();
        playerStatus = player.getPlayerStatus();
    }
}
