package threads;

import object.unit.Unit;
import objectsController.ObjectsController;

public class Live_Walk_Look_Runnable implements Runnable {

    private Unit unit;
    public Thread thread;

    public Live_Walk_Look_Runnable(Unit unit, String str) {
        this.thread = new Thread(this, str);
        this.unit = unit;

    }

    public static Live_Walk_Look_Runnable walk(Unit unit, String str) {
        Live_Walk_Look_Runnable wt = new Live_Walk_Look_Runnable(unit, str);
        wt.thread.start();
        return wt;
    }

    @Override
    public void run() {
        try {

            while (!Thread.currentThread().isInterrupted()) {
                this.unit.move();
                this.unit.look();
                Thread.sleep(40);
                if (!unit.isAlive()) {
                    ObjectsController.getInstance().removeThisObject(unit);
                    Thread.sleep(1000);
                    unit = null;
                    break;
                }


            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
