package object.unit.player;

import frames.PlayerStatus;
import object.enumTypes.RankType;
import object.factories.BodyFactory;
import listeners.MouseGameListeners;
import object.factories.BodyPart;
import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.factories.WeaponsFactory;
import object.unit.Unit;
import object.unit.behavior.enemy.attack.Attack;
import object.unit.behavior.enemy.looking.LookingPlayer;
import object.unit.behavior.enemy.move.MoveInterfejs;
import object.unit.behavior.enemy.move.Patrolls;
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
    private int whichWeapon=0;
    private int energy;
    private int energySuperAttack = 0;
    public Player() {

        super(build_Player_Position(),
                Sizes.Soldier_Size,
                build_Body_Parts(),
                1000,
                WeaponsFactory.create(WeaponsType.AK_47, build_Player_Position(), 120,null)
//                WeaponsFactory.create(
//                        WeaponsType.PISTOL,
//                        build_Player_Position(),
//                        120)
                );
        getWeapon().setUnit(this);
        energy = 100;
        weaponSet= new ArrayList<Weapon>();
        loadWeaponSet();
        this.setSide(1);
        loadData();
        MouseGameListeners.getInstance().addPlayer(this);
        for (BodyPart bp:getBodyParts()){
            bp.setPlayer(true);
        }
        setLookingInterfejs(new LookingPlayer());
        setAttackInerfejs(new Attack());
        this.playerStatus = new PlayerStatus(this);
        this.waitlegs = new Wait();
        this.movelegs = new PlayerMove();
    }
    private void loadData(){

    }
    private static Point build_Player_Position(){
        return new Point(Toolkit.getDefaultToolkit().getScreenSize().width/3,
                Toolkit.getDefaultToolkit().getScreenSize().height/2);
    }
    private static BodyPart[] build_Body_Parts(){
        return BodyFactory.getInstance().Create_Body(RankType.JARO, build_Player_Position());
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
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
    private void loadWeaponSet(){
        Weapon weapon = WeaponsFactory.create(
                WeaponsType.PISTOL,
                getPosition(),
                120,this);
        weaponSet.add(weapon);
        weaponSet.add(getWeapon());
        weapon = WeaponsFactory.create(WeaponsType.GRENADE,getPosition(),5,this);
        weaponSet.add(weapon);

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
        setWeapon(weaponSet.get(whichWeapon));
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
        this.energySuperAttack += value;
        if (this.energySuperAttack<0) this.energySuperAttack=0;
        else if (this.energySuperAttack>100) this.energySuperAttack = 100;
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
}
