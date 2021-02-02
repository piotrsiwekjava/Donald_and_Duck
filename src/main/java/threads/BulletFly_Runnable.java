package threads;

import listeners.KeyGameListener;
import object.factories.Ammo;
import object.factories.AmmoGrenade;
import objectsController.ObjectsController;

import java.awt.*;

public class BulletFly_Runnable implements Runnable {
    private Ammo ammo;
    public Thread thread;
    public BulletFly_Runnable(Ammo ammo, String str) {
        this.thread = new Thread(this, str);
        this.ammo = ammo;
    }
    public static BulletFly_Runnable Fly(Ammo ammo, String str){
        BulletFly_Runnable bf = new BulletFly_Runnable(ammo,str);
        bf.thread.start();
        return bf;
    }
    @Override
    public void run() {
        try
        {

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("BulletFly Runnable pos: " + this.ammo.getClass() + this.ammo.getPosition());
                this.ammo.fly();
                Thread.sleep(10);
                int x = Toolkit.getDefaultToolkit().getScreenSize().width;
                int y = Toolkit.getDefaultToolkit().getScreenSize().height;
                if (this.ammo.isBlocked || ammo.getPosition().getX() > (100 + x)
                        || ammo.getPosition().getX() < (100 - x)
                        || ammo.getPosition().getY() > (100 + y)
                        || ammo.getPosition().getY() < (100 - y)) {
                    removeAmmo();
                    break;
                }
                if (ammo instanceof AmmoGrenade && ((AmmoGrenade) ammo).isExploded()) {
                    removeAmmo();
                    break;
                }

            }
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void removeAmmo(){
        ObjectsController.getInstance().removeBullet(ammo);
        KeyGameListener.getInstance().unregisterObjectG(ammo);
    }
}
