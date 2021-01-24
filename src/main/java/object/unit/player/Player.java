package object.unit.player;

import frames.PlayerStatus;
import listeners.KeyGameListener;
import object.enumTypes.RankType;
import object.factories.BodyFactory;
import listeners.MouseGameListeners;
import object.factories.BodyPart;
import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.factories.WeaponsFactory;
import object.unit.Unit;
import object.unit.behavior.enemy.attack.Attack;
import object.unit.behavior.enemy.looking.LookingInterfejs;
import object.unit.behavior.enemy.looking.LookingPlayer;
import objectsController.ObjectsController;
import settings.Sizes;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player extends Unit {
    private ArrayList<Weapon> weaponSet;
    private PlayerStatus playerStatus;
    private int whichWeapon=0;
    public Player() {
        super(build_Player_Position(),
                Sizes.Soldier_Size,
                build_Body_Parts(),
                100000,null
                );
        weaponSet= new ArrayList<Weapon>();
        this.setSide(1);
        loadData();
        MouseGameListeners.getInstance().addPlayer(this);
        for (BodyPart bp:getBodyParts()){
            bp.setPlayer(true);
        }
        setLookingInterfejs(new LookingPlayer());
        setAttackInerfejs(new Attack());
        this.playerStatus = new PlayerStatus(this);
    }
    private void loadData(){

    }
    private static Point build_Player_Position(){
        return new Point(Toolkit.getDefaultToolkit().getScreenSize().width/3,
                Toolkit.getDefaultToolkit().getScreenSize().height/2);
    }
    private static BodyPart[] build_Body_Parts(){
        return BodyFactory.getInstance().Create_Body(RankType.DONALD, build_Player_Position());
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
        System.out.println(ObjectsController.getInstance().getBulletSet().size());
        System.out.println(getWeapon().getAllleftAmmo());
    }
    private void setWeaponSet(){
        Weapon weapon = WeaponsFactory.create(
                WeaponsType.AK_47,
                build_Player_Position(),
                120);
        weaponSet.add(weapon);
    }
    private void setWeapon(){

        setWeapon(weaponSet.get(whichWeapon));
    }
    private void setWeapon(int number){
        if (number==0 && number<weaponSet.size())
            whichWeapon = number;
        setWeapon(weaponSet.get(whichWeapon));
    }


    public void mouseIsClick(){
        attack();
    }
    public void mouseRealeas(){
        getWeapon().shooting=false;
    }
}
