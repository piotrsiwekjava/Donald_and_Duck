package run;

import frames.GameFrame;
import mainGame.Game;

public class DemoRun {
    public static void main(String[] args){
        DemoRun demoRun = new DemoRun();
        try {
            demoRun.newGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void newGame() throws InterruptedException {

        new Game();

        GameFrame gframe = new GameFrame();
        gframe.setVisible(true);
    }


}
