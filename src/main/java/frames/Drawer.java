package frames;

import level.Level;
import object.BackGround;
import object.Item;
import object.ObjectGame;
import object.ObjectImage;
import object.factories.Obstacle;
import object.factories.*;
import object.unit.Move_Look_Point;
import object.unit.Unit;
import object.unit.player.Player;
import objectsController.ObjectsController;
import settings.Sizes;

import javax.swing.*;
import java.awt.*;


public class Drawer {
    private JPanel panel;
    private ObjectsController objectsController;
    private Graphics2D g2d;
    private Level level;
    private PlayerStatus playerStatus;
    public Drawer(JPanel panel){
        objectsController = ObjectsController.getInstance();
        this.panel = panel;
        this.level = objectsController.getLevel();
    }
    public void drawAll(Graphics2D g2d){
        this.g2d = g2d;
        drawLevel();
        draw_bloods();
        draw_Objects();
        draw_Bullets();
        playerStatus.loadStatus(g2d);
    }
    private void drawLevel(){
        BackGround back = level.getBackground();
        drawObject(back);
        for (Street street: level.getStreets())
            drawObject(street);
        for (Building building: level.getBuildings())
            drawObject(building);
    }

    private void draw_bloods(){
        g2d.setColor(Color.RED);
        for (ObjectGame og: objectsController.getBloodSet()){
            int x = og.getPosition().x;
            int y = og.getPosition().y;
            int x1 = (int) og.getSize()[0];
            int y1 = (int) og.getSize()[1];
            g2d.fillOval(x,y,x1,y1);
            if (x1<100){
                og.getPosition().translate(-1,0);
                og.setSize(new double[]{x1+2,y1+1});
            }
            else {
                if (!(((Move_Look_Point) og).getUnit() instanceof Player))
                objectsController.remove_Blood_and_Unit((Move_Look_Point) og);
            }
        }
    }

    private void draw_Objects(){

        for (ObjectGame og : objectsController.getObjectGSet()){
            if(isOnScreen(og)){
                if (og instanceof Unit) {
                    drawUnit((Unit) og);
                } else if (og instanceof Obstacle || og instanceof Item) {
                    draw_Obstacle_or_Item((ObjectImage) og);
                }
            }
        }
    }
    private boolean isOnScreen(ObjectGame og){
        int x = (int)og.getPosition().getX();
        int xm = Sizes.Screen_Width;
        return x > (-xm * 0.1) && x < (xm * 1.1);
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
        try {
            if (o.getAngle() != 0) rotateAndDraw(o);
            else drawObject(o);
        }
        catch (Exception e){e.printStackTrace();}
    }
    private void draw_Obstacle_or_Item(ObjectImage o){
            int x = (int)o.getPosition().getX();
            int xm = Sizes.Screen_Width;
            if (x>(-xm*0.1) && x<(xm*1.1))
                drawObject(o);
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
