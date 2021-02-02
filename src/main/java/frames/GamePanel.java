package frames;

import listeners.KeyGameListener;
import listeners.MouseGameListeners;
import object.ImageGetterAndChanger;
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
    private int periodOfTime;
    private boolean canDraw =false;
    private Cursor cursor;

    public GamePanel() {
        super();
        this.drawer = new Drawer(this);
        setVisible(true);
        this.setBackground(Color.GRAY);
        loadTime();
        setFrameSizeAndLocation();
        addListener();
        this.addMouseListener(MouseGameListeners.getInstance());
        objectsController = ObjectsController.getInstance();
        loadCursor();
        this.setCursor(cursor);
    }
    private void setFrameSizeAndLocation (){
        panelWindth=Sizes.Screen_Width;
        panelHeight=Sizes.Screen_Height;
        System.out.println("Panel size: Windth- "+panelWindth+ ", Height- "+panelHeight);
        setPreferredSize(new Dimension(panelWindth,panelHeight));
    }

    private void loadTime(){
        addTimer();
        time=0;
        periodOfTime=0;
    }
    private void addTimer (){
        this.myTimerDelay=50;
        myTimer = new Timer(myTimerDelay,gameTimer);
        myTimer.start();
    }

    ActionListener gameTimer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            redraw();
            time+=myTimer.getDelay();
            lastPoint=MouseInfo.getPointerInfo().getLocation();
            periodOfTime++;
            if (periodOfTime>=3) {
                showTimeLapseToKeyListener();
                periodOfTime=0;
            }
        }
    };
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (canDraw) drawer.drawAll(g2d);
        else if(objectsController.isCanPlay())
            tryGetPlayerStatus();
    }

    private void loadCursor(){
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(
                ImageGetterAndChanger.getInstance().getTransImg("weapons\\cursorShoot"),
                new Point(0,0),
                "cursorShoot");
    }
    private void redraw (){
        this.repaint();
    }
    private void addListener(){
        keylistener =new JButton("");
        this.add(keylistener);
        keylistener.addKeyListener(KeyGameListener.getInstance());
    }
    private void tryGetPlayerStatus(){
        drawer.loadPlayerStatus();
        this.canDraw = true;
    }
    private void showTimeLapseToKeyListener(){
        KeyGameListener.getInstance().timeLapse();
    }
}