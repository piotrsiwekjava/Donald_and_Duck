package frames;

import listeners.KeyGameListener;
import listeners.MouseGameListeners;
import objectsController.ObjectsController;
import settings.Sizes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Piotr Siwek
 */

class GamePanel extends JPanel {


    private int myTimerDelay;
    private Timer myTimer;
    private Point lastPoint;
    private int panelWindth;
    private int panelHeight;
    public Timer getMyTimer(){
        return myTimer;
    }
    public int getTime(){
        return time;
    }
    private ObjectsController objectsController;
    private JButton keylistener;
    private Drawer drawer;
    private int time;

    public GamePanel() {
        super();
        this.drawer = new Drawer(this);
        setVisible(true);
        this.setBackground(Color.GRAY);
        addTimer();
        time=0;
        setFrameSizeAndLocation();
        addListener();
        this.addMouseListener(MouseGameListeners.getInstance());
        objectsController = ObjectsController.getInstance();
    }
    private void setFrameSizeAndLocation (){
        panelWindth=Sizes.Screen_Width;
        panelHeight=Sizes.Screen_Height;
        System.out.println("Panel size: Windth- "+panelWindth+ ", Height- "+panelHeight);
        setPreferredSize(new Dimension(panelWindth,panelHeight));
    }

    private void addTimer (){
        this.myTimerDelay=100;
        myTimer = new Timer(myTimerDelay,gameTimer);
        myTimer.start();
    }

    ActionListener gameTimer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            redraw();
            time+=myTimer.getDelay();
            lastPoint=MouseInfo.getPointerInfo().getLocation();
        }
    };
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawer.drawAll(g2d);
    }

    private void redraw (){
        this.repaint();
    }
    private void addListener(){
        keylistener =new JButton("");
        this.add(keylistener);
        keylistener.addKeyListener(KeyGameListener.getInstance());
    }
}