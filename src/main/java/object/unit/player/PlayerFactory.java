package object.unit.player;


import object.enumTypes.UnitType;
import object.enumTypes.WeaponsType;
import object.factories.BodyFactory;
import object.factories.BodyPart;
import object.factories.Weapon;
import object.factories.WeaponsFactory;

import java.awt.*;
import java.util.ArrayList;

public class PlayerFactory {

    public PlayerFactory() {
    }

    public Player buildPlayer(UnitType type){
        Player player = null;

        Point startPoint = build_Player_Position();
        BodyPart [] bodyParts = build_Body_Parts(type);
        ArrayList <Weapon> weaponSet = loadWeaponSet(startPoint);
        int playerPoints = 0;

        player = new Player(startPoint,bodyParts,weaponSet,playerPoints);


        return player;
    }

    private Point build_Player_Position(){
        return new Point(Toolkit.getDefaultToolkit().getScreenSize().width/3,
                Toolkit.getDefaultToolkit().getScreenSize().height/2);
    }
    private BodyPart[] build_Body_Parts(UnitType type){
        return BodyFactory.getInstance().Create_Body(type, build_Player_Position());
    }


    private ArrayList<Weapon> loadWeaponSet(Point startpoint){
        ArrayList<Weapon> weaponSet = new ArrayList<>();
        Weapon weapon = WeaponsFactory.create(WeaponsType.PISTOL, build_Player_Position(), 100,null);
        weaponSet.add(weapon);
        weapon = WeaponsFactory.create(WeaponsType.GRENADE,startpoint,5,null);
        weaponSet.add(weapon);
        weapon = WeaponsFactory.create(WeaponsType.KONSTYTUCJA,startpoint,0,null);
        weaponSet.add(weapon);
        weapon = WeaponsFactory.create(WeaponsType.NOWEAPON,startpoint,0,null);
        weaponSet.add(weapon);

        return weaponSet;

    }
}
