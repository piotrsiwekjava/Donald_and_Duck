package object.unit.behavior.enemy.move;

import object.unit.BodyController;
import object.unit.Unit;
import objectsController.ObjectsController;

import java.awt.*;
import java.util.Random;

public class Death implements MoveInterfejs {
    private ObjectsController objectsController = ObjectsController.getInstance();
    private Unit unit;
    private int angle;
    private int maxAngle;
    private int increment;
    private int[] randomsIncrement;
    private double falling;
    private double[] recoil;

    public Death(Unit unit, int ammoStrength) {
        this.unit = unit;
        this.angle = 0;
        this.maxAngle = 80;
        this.increment = 4;
        this.recoil = new double[2];
        setHighOfLastDamage();
        setRecoilCourse(ammoStrength);
        this.randomsIncrement = getRandomAnglesRotate();
    }

    @Override
    public void move(Unit unit) {
        unit.alive = false;
        if (angle < maxAngle) {
            BodyController bc = unit.getBodyController();
            System.out.println(recoil[0]+"//"+recoil[1]);
            makeUnitRecoilOneStep();
            bc.rotatePart(unit.getBodyParts()[0],increment);
            for (int i = 1; i<6;i++) {
                bc.rotatePart(unit.getBodyParts()[i], randomsIncrement[i]);
            }

        }
        this.angle += Math.abs(increment);
        unit.getBodyController().changePointsBodyWhenTorsoRotate();
    }

    private int[] getRandomAnglesRotate(){
        int [] ri = new int [6];
        for (int i = 1; i<6; i++)
            ri[i] = new Random().nextInt(Math.abs(increment*2))-increment;
        return ri;
    }
    private void setHighOfLastDamage(){
        int yo = unit.getPosition().y;
        int ydp = unit.getDamagePoint().y;
        int y0size = (int) (unit.getSize()[0] * 70);
        this.falling = (unit.getSize()[0] * 10);
        if ((ydp - yo - y0size)>increment) increment*=(-1);
    }
    private void setRecoilCourse(int ammoStrenght){
        System.out.println(ammoStrenght);
        Point punit = unit.getPosition();
        Point pammo = unit.getDamagePoint();
        double dX=(punit.getX()-pammo.getX());
        double dY=(punit.getY()-pammo.getY());
        double dC=(Math.sqrt(Math.pow(dX,2)+Math.pow(dY,2)));
        recoil[0] = (dX* ammoStrenght)/(dC*10);
        recoil[1] = (dY* ammoStrenght)/(dC*10);
    }
    private void makeUnitRecoilOneStep(){
        unit.getBodyParts()[0].setXY(recoil[0], getRecoilOneStep());
    }
    private double getRecoilOneStep(){
        double heigth = 0;
        if (angle>maxAngle*0.5) {
            this.recoil[0] = this.recoil[1] + this.recoil[1];
        }
        else {
            this.recoil[0] = this.recoil[1] - this.recoil[1];
        }
        heigth = this.recoil[0]+falling;
        return heigth;
    }
}
