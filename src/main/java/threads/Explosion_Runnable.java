package threads;

import listeners.KeyGameListener;
import object.ImageGetterAndChanger;
import object.factories.Obstacle;
import objectsController.ObjectsController;

import java.awt.image.BufferedImage;

public class Explosion_Runnable implements Runnable {
    private Obstacle explosion;
    private int whichImage;
    public Thread thread;
    public Explosion_Runnable(Obstacle o, String str) {
        this.thread = new Thread(this, str);
        this.explosion = o;
        this.whichImage=1;
    }
    public static Explosion_Runnable Explosion(Obstacle o, String str){
        Explosion_Runnable bf = new Explosion_Runnable(o,str);
        bf.thread.start();
        return bf;
    }
    @Override
    public void run() {
        try
        {

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(200);
                if (whichImage==8) {
                    removeExplosion();
                    break;
                }
                changeImage();
            }
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private void changeImage(){
        BufferedImage image = ImageGetterAndChanger.getInstance().getTransImg(changedImagePath());
        this.explosion.setImage(image);
    }
    private String changedImagePath() {
    String iP = this.explosion.getImagePath();
        iP = iP.substring(0, iP.length()-1);
        iP+=(++whichImage);
    return iP;
    }
    private void removeExplosion(){
        ObjectsController.getInstance().removeThisObject(explosion);
        KeyGameListener.getInstance().unregisterObjectG(explosion);
    }
}
