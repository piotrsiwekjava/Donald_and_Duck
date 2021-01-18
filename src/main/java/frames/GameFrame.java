package frames;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Piotr Siwek
 */

public class GameFrame extends JFrame {

    private GamePanel gamePanel;

    public GameFrame(){
        super();
        this.setDefaultCloseOperation(3);
        initComponents();
        setFrameSizeAndLocation();
        newOpen();
    }

    public void newOpen (){
        startGame();
        setVisible(true);
    }
    private void initComponents (){
    }

    private void setFrameSizeAndLocation (){
        int systemWindth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int systemHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(systemWindth,systemHeight);
        System.out.println(this.getSize());
    }
    private void buildGUI (){
        gamePanel = new GamePanel();
    }
    private void addGamePanel (){
        this.add(gamePanel);
    }

    private void startGame (){
        buildGUI();
        addGamePanel();
        setVisible(true);
    }
}
