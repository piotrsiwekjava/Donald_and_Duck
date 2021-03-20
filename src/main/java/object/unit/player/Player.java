package object.unit.player;

import frames.PlayerStatus;
import listeners.KeyGameListener;
import listeners.MouseGameListeners;
import object.factories.BodyPart;
import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.unit.Unit;
import object.unit.behavior.enemy.attack.Attack;
import object.unit.behavior.enemy.looking.LookingPlayer;
import object.unit.behavior.enemy.move.PlayerMove;
import object.unit.behavior.enemy.move.Wait;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Unit {
    private ArrayList<Weapon> weaponSet;
    private PlayerStatus playerStatus;
    private PlayerMove movelegs;
    private Wait waitlegs;
    private int whichWeapon = 0;
    private int energy = 100;
    private int energySuperAttack = 0;
    public Player(Point startpoint, BodyPart[] bodyparts,ArrayList<Weapon>weaponSet, int points) {

        super(startpoint,
                Sizes.Soldier_Size,
                bodyparts,
                100,
                weaponSet.get(0),
                points
                );
        this.weaponSet= weaponSet;
        setPlayerStartStatus();

    }

    private void setPlayerStartStatus(){
        this.setSide(1);
        MouseGameListeners.getInstance().addPlayer(this);
        for (BodyPart bp:getBodyParts()){
            bp.setPlayer(true);
        }
        assign_owner_to_weapons();
        setLookingInterfejs(new LookingPlayer());
        setAttackInerfejs(new Attack());
        this.playerStatus = new PlayerStatus(this);
        this.waitlegs = new Wait();
        this.movelegs = new PlayerMove();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void setXY(double ix, double iy) {
        this.getPosition().setLocation(getBodyParts()[0].getPosition());

    }

    @Override
    public void attack() {
        super.attack();
    }

    @Override
    protected void death(int ammoStrenght) {
        super.death(ammoStrenght);
        playerDeath();
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    private void assign_owner_to_weapons(){
        for(Weapon weapon : weaponSet){
            weapon.setUnit(this);
        }
    }

    public void swiftWeapon(){
        if (weaponSet.size()>(whichWeapon+1))whichWeapon++;
        else whichWeapon=0;
        changeWeapon();
    }
    public void swiftWeapon(int number){
        if (number>=0 && number<weaponSet.size())
            whichWeapon = number;
        changeWeapon();
    }
    private void changeWeapon(){
        this.getWeapon().shooting=false;
        Weapon wNext = weaponSet.get(whichWeapon);
        if (wNext.getWeapontype().equals(WeaponsType.NOWEAPON))
            swiftWeapon();
        else setWeapon(wNext);
        getBodyController().setWeapon(getWeapon());
        playerStatus.changeWeapon();
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public void changeEnergy(int value) {
        this.energy+=value;
        if (this.energy<0)energy=0;
        else if (this.energy>100) energy=100;
    }

    public int getEnergySuperAttack() {
        return energySuperAttack;
    }

    public void setEnergySuperAttack(int energySuperAttack) {
        this.energySuperAttack = energySuperAttack;
    }
    public void changeEnergySuperAttack(int value) {
        if (this.energySuperAttack < 0)
            this.energySuperAttack = 0;
        if (this.energySuperAttack < 100)
            this.energySuperAttack += value;
        else {
            this.energySuperAttack = 100;
            for (Weapon w : weaponSet) {
                if (w.isSuperweapon()) w.addOnlyOneAmmo();
            }
        }
    }

    public void setMoveInterfejsInWait(){
        this.setMoveInterfejs(waitlegs);
    }
    public void setMoveInterfejsInMove(){
        this.setMoveInterfejs(movelegs);
    }

    public void mouseIsClick(){
        int x = (int)(MouseInfo.getPointerInfo().getLocation().getX()-this.getPosition().getX());
        int y = (int)(MouseInfo.getPointerInfo().getLocation().getY()-this.getPosition().getY());
        if (Math.abs(x)>this.getSize()[0]*100 || Math.abs(y)>this.getSize()[1]*100)
            attack();
    }
    public void mouseRealeas(){
        getWeapon().triggerRelease();
    }

    public ArrayList<Weapon> getWeaponSet() {
        return weaponSet;
    }
    private void playerDeath(){
        KeyGameListener.getInstance().setCanGaming(false);
        ObjectsController.getInstance().setEnemySeePlayer(false);
    }
}
