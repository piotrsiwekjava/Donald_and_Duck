package object.unit.player;

import object.enumTypes.WeaponsType;
import object.factories.Weapon;
import object.factories.WeaponsFactory;
import objectsController.ObjectsController;
import sound.Mixer;

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

    public boolean collectItem(String item_name) {
        WeaponsType type = WeaponsType.PISTOL;
        int count = 0;
        if (item_name.equals("AK_47")){
            type = WeaponsType.AK_47;
            count = 30;
        }
        else if(item_name.equals("PISTOL")) {
            type = WeaponsType.PISTOL;
            count = 24;
        }
        else if (item_name.equals("GRENADE")) {
            type = WeaponsType.GRENADE;
            count = 5;
        }

        if (haveYouThisWeapon(type, count)) {
            giveSound();
            return true;}
        else
            return addWeapon(type, count);
    }

    private void giveSound() {
        Mixer.getMainSoundPlayer().playNewMusicThread(18);
    }

    private boolean haveYouThisWeapon(WeaponsType type,int count) {
        for (Weapon w : player.getWeaponSet()) {
            if (w.getWeapontype().equals(type)) {
                addAmmo(w, count);
                return true;
            }
        }
        return false;
    }
    private void addAmmo(Weapon w, int count){
        w.setAllleftAmmo(w.getAllleftAmmo() + count);
    }
    private boolean addWeapon(WeaponsType type,int count){
        try {
            int ns = numberFreeSlotForWeapon();
            if (ns == -1) return false;
            Weapon w = WeaponsFactory.create(type, player.getPosition(), count, player);
            player.getWeaponSet().set(ns, w);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    private int numberFreeSlotForWeapon(){
        Weapon w;
        for (int i=0; i<4; i++) {
            w = player.getWeaponSet().get(i);
            if (w.getWeapontype().equals(WeaponsType.NOWEAPON))
                return i;
        }
        return -1;
    }
}
