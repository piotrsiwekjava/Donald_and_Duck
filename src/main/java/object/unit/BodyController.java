package object.unit;

import object.ImageChanger;
import object.ObjectImage;
import object.factories.BodyPart;
import object.factories.Weapon;
import object.unit.player.Player;

import java.awt.*;


/**
 * Class to control unit's body parts
 */
public class BodyController {
    private Unit unit;
    private BodyPart[] bodyParts;
    private double size[] = new double[2];
    private ImageChanger imChnger;
    private Weapon weapon;
    private boolean isPlayer = false;

    public BodyController(Unit unit) {
        this.unit = unit;
        if (unit instanceof Player)
            isPlayer=true;
        initiation();
    }
    private void initiation(){
        this.bodyParts = unit.getBodyParts();
        this.weapon = unit.getWeapon();
        size[0] = (bodyParts[0].getImage().getWidth()) * unit.getSize()[0];
        size[1] = (bodyParts[0].getImage().getHeight()) * unit.getSize()[0];
        setSize();
        resetPosition();
        imChnger = ImageChanger.getInstance();
        setPositionLeft();
    }

    private void setSize() {

        bodyParts[0].setSize(new double[]{size[0], size[1]});
        bodyParts[1].setSize(new double[]{size[0] / 2, size[1] / 2});
        bodyParts[2].setSize(new double[]{size[0] / 2.2, size[1] * 0.9});
        bodyParts[3].setSize(new double[]{size[0] / 2.2, size[1] * 0.9});
        bodyParts[4].setSize(new double[]{size[0] / 1.5, size[1] / 1.5});
        bodyParts[5].setSize(new double[]{size[0] / 1.5, size[1] / 1.5});
    }
    private void resetPosition(){
        resetAngle();
        if (!isPlayer) {
            for (BodyPart bp : bodyParts) {
                bp.getPosition().setLocation(new Point(bodyParts[0].getPosition()));
                bp.getJoinToRotate().setLocation(new Point(bodyParts[0].getPosition()));
            }
            weapon.getPosition().setLocation(new Point(bodyParts[0].getPosition()));
        }
        else if(isPlayer){
            for (BodyPart bp : bodyParts) {
                bp.getPosition().setLocation(bodyParts[0].getPosition());
                bp.getJoinToRotate().setLocation(bodyParts[0].getPosition());
            }
            weapon.getPosition().setLocation(bodyParts[0].getPosition());
        }
    }
    private void resetAngle(){
        for (BodyPart bp: bodyParts){
            setAnglePart(bp,0);
        }
        weapon.setAngle(0);
    }

    private void setPositionLeft() {
        setOdds_LocateLeft();
        setOdds_drawLeft();
        locationLeft();
    }

    private void setPositionRight() {
        setOdds_LocateRight();
        setOdds_drawRight();
        locationRight();
    }

    private void setOdds_LocateLeft() {
        bodyParts[1].setOdds_locate(size[0]*0.45 , size[1]*0.01 );
        bodyParts[2].setOdds_locate(size[0]*0.25, size[1]*0.95);
        bodyParts[3].setOdds_locate(size[0]*0.75, size[1] * 0.95);
        bodyParts[4].setOdds_locate(size[0]*0.1, size[1]*0.2);
        bodyParts[5].setOdds_locate(size[0]*0.9, size[1]*0.2);
        weapon.setOdds_locate(size[0] * 0.9, size[1] * 0.2);
    }

    private void setOdds_drawLeft(){
        bodyParts[1].setOdds_draw(-size[0]*0.19,-size[1]*0.44);
        bodyParts[2].setOdds_draw(-size[0]*0.2,-size[1]*0.1);
        bodyParts[3].setOdds_draw(-size[0]*0.25,-size[1]*0.1);
        bodyParts[4].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        bodyParts[5].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        weapon.setOdds_draw(-size[0]*1.3,size[1]*0.2);
    }

    private void setOdds_LocateRight() {

        bodyParts[1].setOdds_locate(size[0] * 0.5, size[1] * 0.01);
        bodyParts[2].setOdds_locate(size[0] * 0.25, size[1] * 0.95);
        bodyParts[3].setOdds_locate(size[0] * 0.75, size[1] * 0.95);
        bodyParts[4].setOdds_locate(size[0] * 0.9, size[1] * 0.2);
        bodyParts[5].setOdds_locate(size[0] * 0.1, size[1] * 0.2);
        weapon.setOdds_locate(size[0] * 0.1, size[1] * 0.2);
    }
    private void setOdds_drawRight(){
        bodyParts[1].setOdds_draw(-size[0]*0.19,-size[1]*0.44);
        bodyParts[2].setOdds_draw(-size[0]*0.2,-size[1]*0.1);
        bodyParts[3].setOdds_draw(-size[0]*0.25,-size[1]*0.1);
        bodyParts[4].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        bodyParts[5].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        weapon.setOdds_draw(-size[0]*0.33,size[1]*0.2);
    }

    private void locationLeft() {
        bodyParts[1].setXY(bodyParts[1].getOdds_locate()[0], bodyParts[1].getOdds_locate()[1]);
        bodyParts[2].setXY(bodyParts[2].getOdds_locate()[0], bodyParts[2].getOdds_locate()[1]);
        bodyParts[3].setXY(bodyParts[3].getOdds_locate()[0], bodyParts[3].getOdds_locate()[1]);
        bodyParts[4].setXY(bodyParts[4].getOdds_locate()[0], bodyParts[4].getOdds_locate()[1]);
        bodyParts[5].setXY(bodyParts[5].getOdds_locate()[0], bodyParts[5].getOdds_locate()[1]);
        weapon.setXY(weapon.getOdds_locate()[0], weapon.getOdds_locate()[1]);

    }

    private void locationRight() {
        bodyParts[1].setXY(bodyParts[1].getOdds_locate()[0], bodyParts[1].getOdds_locate()[1]);
        bodyParts[2].setXY(bodyParts[2].getOdds_locate()[0], bodyParts[2].getOdds_locate()[1]);
        bodyParts[3].setXY(bodyParts[3].getOdds_locate()[0], bodyParts[3].getOdds_locate()[1]);
        bodyParts[4].setXY(bodyParts[4].getOdds_locate()[0], bodyParts[4].getOdds_locate()[1]);
        bodyParts[5].setXY(bodyParts[5].getOdds_locate()[0], bodyParts[5].getOdds_locate()[1]);
        weapon.setXY(weapon.getOdds_locate()[0], weapon.getOdds_locate()[1]);
    }


    public void moveBody(double fast){
        movelegs(fast);
        looking();
    }
    public void movelegs(double fast) {
        double angle = fast/2;
        int limit = 30;

        if (bodyParts[2].getAngle() >= limit || bodyParts[2].getAngle() <= -limit/2) {
            bodyParts[2].setCourseMove();
            bodyParts[3].setCourseMove();
        }
        angle *= bodyParts[2].getCourseMove();
        bodyParts[2].setAngle(bodyParts[2].getAngle()+angle);
        bodyParts[3].setAngle(-bodyParts[2].getAngle());
        changePointsBodyWhenTorsoRotate();
    }
    public void looking(){
        double c = angleLook()* unit.getSide();
        double a = c * bodyParts[5].getCourseMove();
        double b = c * bodyParts[4].getCourseMove()/2;
        if (unit.getSide()==1 && b<0)b*=-1;
        setAnglePart(bodyParts[0],a/4);
        setAnglePart(bodyParts[1],a*0.75);
        setAnglePart(bodyParts[5],a);
        setAnglePart(bodyParts[4],b);
    }

    public double angleLook() {
        Point lookTarget = unit.getLookTarget().getPosition();
        double dX = (lookTarget.getX() - bodyParts[0].getPosition().getX());
        double dY = (lookTarget.getY() - bodyParts[0].getPosition().getY());

        double dC = (Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2)));
        double angle = Math.toDegrees(Math.asin(dY / dC));
        if (Double.isNaN(angle)) angle = 0;
        return angle;
    }

    private void rotateAllBody(double angle){
        rotateBody(angle);
        for (int i=1;i<6;i++)
            bodyParts[i].setAngle(angle);
        weapon.setAngle(angle);
    }
    private void rotateBody(double angle ){
        bodyParts[0].setAngle(bodyParts[0].getAngle() + angle);
        changePointsBodyWhenTorsoRotate();
    }
    private void rotatePart(ObjectImage objectImage, double angle){
        objectImage.setAngle(objectImage.getAngle()+angle);
        changePointsBodyWhenTorsoRotate();
    }

    private void rotatePart(BodyPart bodyPart, double angle, int limit){
        if (bodyParts[2].getAngle() >= limit || bodyParts[2].getAngle() <= -limit/2)
            bodyPart.setCourseMove();
        angle *= bodyPart.getCourseMove();
        bodyPart.setAngle(bodyPart.getAngle()+angle);
        changePointsBodyWhenTorsoRotate();
    }
    private void setAnglePart(BodyPart bodyPart,double angle){
        if (bodyPart.equals(bodyParts[5]))
            weapon.setAngle(angle);
        bodyPart.setAngle(angle);
        changePointsBodyWhenTorsoRotate();
    }

    private void changePointsBodyWhenTorsoRotate() {
        for(BodyPart bp: bodyParts) {
            bp.getPosition().setLocation(changePointWhenTorsoRotate(
                    bp.getOdds_locate()[0], bp.getOdds_locate()[1]));
            bp.setJoinToRotate(changePointWhenTorsoRotate(
                    bp.getOdds_locate()[0], bp.getOdds_locate()[1]));
        }
        weapon.getPosition().setLocation(changePointWhenTorsoRotate(
                weapon.getOdds_locate()[0], weapon.getOdds_locate()[1]));
        weapon.setJoinToRotate(changePointWhenTorsoRotate(
                weapon.getOdds_locate()[0], weapon.getOdds_locate()[1]));
    }
    private Point changePointWhenTorsoRotate(double x, double y){
        double x0 = bodyParts[0].getPosition().getX();
        double y0 = bodyParts[0].getPosition().getY();
        double radians = Math.toRadians(bodyParts[0].getAngle());
        double sinus = Math.sin(radians);
        double cosinus = Math.cos(radians);
        Point point= new Point(0,0);
        point.setLocation(x0 + (x * cosinus - y * sinus),
                y0 + (x * sinus + y * cosinus));
        return point;


    }

    public synchronized void changeSide(int side) {
        weapon.setImage(imChnger.mirrorImage(weapon.getImage()));
        for (BodyPart bp : unit.getBodyParts()) {
            bp.setImage(imChnger.mirrorImage(bp.getImage()));

        }
        resetPosition();
        if (side == 1) {
            setPositionRight();
        } else if (side == -1) {
            setPositionLeft();
        }

    }
}
