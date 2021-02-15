package object.unit.player;

import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.factories.WeaponsFactory;
import objectsController.ObjectsController;

import java.lang.reflect.Type;

public class Collector {

    private int amount_of_points;
    private Player player;
    private static Collector instance = new Collector(0);

    private Collector(int points) {
        this.amount_of_points = points;
        this.player = ObjectsController.getInstance().getPlayer();
    }

    public static Collector getInstance() {
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getAmount_of_points() {
        return amount_of_points;
    }

    public void addPoints(int points) {
        this.amount_of_points += points;
        player.changeHp(points);
    }

    public void setAmount_of_points(int amount_of_points) {
        this.amount_of_points = amount_of_points;
    }

    public boolean collectWeapon(WeaponsType type, int count) {

        for (Weapon w : player.getWeaponSet()) {
            if (w.getWeapontype().equals(type)) {
                addAmmo(w, count);
                return true;
            }
        }
        if (player.getWeaponSet().size() < 4) {
            addWeapon(type, count);
            return true;
        }
        return false;
    }
    private void addAmmo(Weapon w, int count){
        w.setAllleftAmmo(w.getAllleftAmmo() + count);
    }
    private void addWeapon(WeaponsType type,int count){
        Weapon w = WeaponsFactory.create(type,player.getPosition(),count,player);
        player.getWeaponSet().add(w);
    }
}
