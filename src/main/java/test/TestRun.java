package test;

import frames.GameFrame;
import object.unit.player.Player;
import objectsController.ObjectsController;

import java.io.IOException;

public class TestRun {
    public static void main(String[] args) throws IOException {
        TestRun testRun = new TestRun();
        try {
            testRun.newGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void newGame() throws InterruptedException {
        GameFrame gframe = new GameFrame();
        gframe.setVisible(true);

        Player player = new Player();
        ObjectsController objCon = ObjectsController.getInstance();
        objCon.setPlayer(player);
        player.attack();

    }
}
