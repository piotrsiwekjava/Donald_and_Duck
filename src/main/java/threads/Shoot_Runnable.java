
package threads;

        import object.factories.Weapon;
        import object.unit.Unit;

        import java.awt.*;

public class Shoot_Runnable implements Runnable {

    private Weapon weapon;
    private Unit unit;
    public Thread thread;

    public Shoot_Runnable(Weapon weapon) {
        this.thread = new Thread(this);
        this.weapon = weapon;
        this.unit = weapon.getUnit();
    }

    public static Shoot_Runnable Shoot(Weapon weapon) {
        Shoot_Runnable wt = new Shoot_Runnable(weapon);
        wt.thread.start();
        return wt;
    }

    @Override
    public void run() {
        try {
            while (unit.isAlive() && !Thread.currentThread().isInterrupted()) {
                if (!unit.getWeapon().equals(weapon)) break;
                if (!weapon.shooting || weapon.getAllleftAmmo() <= 0) {
                    break;
                }
                this.weapon.triggerPull();
                Thread.sleep((long) weapon.getFireSpeed());


            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}