
package threads;

        import object.unit.Unit;

public class Attack_Runnable implements Runnable {

    private Unit unit;
    public Thread thread;

    public Attack_Runnable(Unit unit) {
        this.thread = new Thread(this);
        this.unit = unit;
    }
    public static Attack_Runnable Shoot(Unit unit) {
        Attack_Runnable wt = new Attack_Runnable(unit);
        wt.thread.start();
        return wt;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (unit.isAlive() && unit.seePlayer) {
                    break;
                }
                this.unit.attack();
                Thread.sleep(10);


            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}