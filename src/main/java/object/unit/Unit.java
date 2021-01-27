package object.unit;

import object.factories.BodyPart;
import object.ObjectGame;
import object.factories.Weapon;
import object.unit.behavior.enemy.attack.AttackInerfejs;
import object.unit.behavior.enemy.attack.NoAttack;
import object.unit.behavior.enemy.looking.LookingEnemy;
import object.unit.behavior.enemy.looking.LookingInterfejs;
import object.unit.behavior.enemy.move.MoveInterfejs;
import object.unit.behavior.enemy.move.Wait;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;

public class Unit extends ObjectGame {
    private ObjectsController objectsController;
    private BodyPart[] bodyParts;
    public boolean alive;
    private int hp;
    private Weapon weapon;
    private Move_Look_Target moveTarget;
    private Move_Look_Target lookTarget;
    private BodyController bodyController;
    private MoveInterfejs moveInterfejs;
    private AttackInerfejs attackInerfejs;
    private LookingInterfejs lookingInterfejs;
    /**
     * side - direct of looking -> -1 =Left ; 1=Right
     * course - direct of moving -> -1 =Left ; 1=Right
     */
    private int side;
    private int course;
    private double ownLegfast;

    public Unit(Point position, double [] size, BodyPart[] bodyParts, int hp, Weapon weapon) {
        super(position, size);
        this.objectsController = ObjectsController.getInstance();
        this.bodyParts=bodyParts;
        this.hp = hp;
        this.weapon = weapon;
        initiation();
    }
    private void initiation(){
        this.alive=true;
        this.moveTarget = new Move_Look_Target(this.getPosition());
        this.lookTarget = new Move_Look_Target(MouseInfo.getPointerInfo().getLocation());
        lookingInterfejs = new LookingEnemy();
        moveInterfejs = new Wait();
        attackInerfejs =new NoAttack();
        this.bodyController = new BodyController(this);
        this.side = -1;
        this.weapon.setUnit(this);
        this.ownLegfast = Sizes.Leg_Speed;
    }
    public boolean isAlive(){
        return alive;
    }
    public int getHp() {
        return hp;
    }

    public void changeHp(int value) {
        this.hp+= value;
        if(hp<=0) alive=false;
    }
    public void attack(){
        this.weapon.shooting=true;
        attackInerfejs.attack(weapon);
    }
    public void stopAtck(){this.weapon.shooting =false;}
    public void move() {
        moveInterfejs.move(this);
    }
    public void look(){
        lookingInterfejs.look(this);
    }



    public void setMoveInterfejs(MoveInterfejs moveIn){
        this.moveInterfejs =moveIn;
    }

    public void setAttackInerfejs(AttackInerfejs attackInerfejs) {
        this.attackInerfejs = attackInerfejs;
    }

    public void setLookingInterfejs(LookingInterfejs lookingInterfejs) {
        this.lookingInterfejs = lookingInterfejs;
    }

    public Move_Look_Target getMoveTarget() {
        return moveTarget;
    }

    public void setMoveTarget(Point point) {
        this.moveTarget.setPosition(point);
    }

    public Move_Look_Target getLookTarget() {
        return lookTarget;
    }

    public void setLookTarget(Point newPoint) {
        this.lookTarget.setPosition(newPoint);
    }

    public BodyPart[] getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(BodyPart[] bodyParts) {
        this.bodyParts = bodyParts;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int getSide() {
        return side;
    }

    public synchronized void setSide(int side2) {
        this.side = side2;
        this.bodyController.changeSide(side2);

    }

    @Override
    public void setXY(double ix, double iy) {
        super.setXY(ix, iy);
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public BodyController getBodyController(){
        return this.bodyController;
    }

    public void setOwnLegfast(double ownLegfast){
        this.ownLegfast = ownLegfast;
    }

    public double getOwnLegfast(){
        return this.ownLegfast;
    }
}
