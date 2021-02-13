
package threads;

        import object.factories.Weapon;
        import object.unit.Unit;

public class Attack_Runnable implements Runnable {

    private Unit unit;
    public Thread thread;

    public Attack_Runnable(Unit unit) {
        this.thread = new Thread(this);
        this.unit = unit;
    }
    public static Attack_Runnable Attack(Unit unit) {
        Attack_Runnable wt = new Attack_Runnable(unit);
        wt.thread.start();
        return wt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (!unit.isAlive() || !unit.seePlayer) {
                    break;
                }
                this.unit.attack();
                Weapon weapon = this.unit.getWeapon();
                Thread.sleep((long) weapon.getFireSpeed());


            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}