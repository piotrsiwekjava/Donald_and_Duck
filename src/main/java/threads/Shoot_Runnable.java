
package threads;

        import object.factories.Weapon;

        import java.awt.*;

public class Shoot_Runnable implements Runnable {

    private Weapon weapon;
    public Thread thread;

    public Shoot_Runnable(Weapon weapon) {
        this.thread = new Thread(this);
        this.weapon = weapon;
    }

    public static Shoot_Runnable Shoot(Weapon weapon) {
        Shoot_Runnable wt = new Shoot_Runnable(weapon);
        wt.thread.start();
        return wt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                this.weapon.triggerPull();
                Thread.sleep((long) weapon.getFireSpeed());
                if (!weapon.shooting || weapon.getAllleftAmmo()<=0) {
                    break;
                }

            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}