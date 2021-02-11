package object.unit.behavior.enemy.move;

import object.unit.BodyController;
import object.unit.Move_Look_Point;
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
    private boolean blood;

    public Death(Unit unit, int ammoStrength) {
        this.unit = unit;
        setHighOfLastDamage();
        setRecoilCourse(ammoStrength);
        this.randomsIncrement = getRandomAnglesRotate();
    }
    {
        this.angle = 0;
        this.maxAngle = 80;
        this.increment = 4;
        this.recoil = new double[2];
        blood = false;
    }

    @Override
    public void move(Unit unit) {
        unit.alive = false;
        if (angle < maxAngle) {
            BodyController bc = unit.getBodyController();
            makeUnitRecoilOneStep();
            bc.rotatePart(unit.getBodyParts()[0],increment);
            for (int i = 1; i<6;i++) {
                bc.rotatePart(unit.getBodyParts()[i], randomsIncrement[i]);
            }

        }
        else if (!blood){
            Move_Look_Point bldPoint = new Move_Look_Point(new Point(this.unit.getPosition()));
            int r = new Random().nextInt(100)+50;
            if (increment>0) bldPoint.getPosition().translate((int)(-unit.getSize()[1]*r),(int)(unit.getSize()[0]*30));
            else if(increment<0) bldPoint.getPosition().translate((int)(unit.getSize()[1]*r),-(int)(unit.getSize()[0]*20));
            objectsController.getBloodSet().add(bldPoint);
            this.blood = true;
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
        int x0 = unit.getPosition().x;
        int xdp = unit.getDamagePoint().x;
        int y0size = (int) (unit.getSize()[0] * 70);
        this.falling = (unit.getSize()[0] * 10);
        if ((ydp - yo - y0size)>increment) increment*=(-1);
        if (x0-xdp<0)increment*=(-1);
    }
    private void setRecoilCourse(int ammoStrenght){
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
        if (this.recoil[1]<0) {
            if (angle < maxAngle * 0.5) {
                heigth += this.recoil[1];
            } else {
                heigth -= (this.recoil[1]);
            }
        }
        else heigth +=recoil[1];
        heigth += falling;
        return heigth;
    }
}
