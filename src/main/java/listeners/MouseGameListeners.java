package listeners;

import object.unit.player.Player;
import objectsController.ObjectsController;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseGameListeners extends MouseAdapter {
    private static MouseGameListeners mouseGameListeners = new MouseGameListeners();
    private Point clickpoint;
    private Player player;
    private MouseGameListeners() {
    }
    public static MouseGameListeners getInstance(){
        return mouseGameListeners;
    }
    public void addPlayer(Player player){
        this.player=player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        super.mouseClicked(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Press");
        super.mousePressed(e);
        if (e.getButton()==MouseEvent.BUTTON1) {
            player.mouseIsClick();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
        super.mouseReleased(e);
        if (e.getButton()==MouseEvent.BUTTON1) player.mouseRealeas();
    }

}
