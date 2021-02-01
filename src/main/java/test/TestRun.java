package test;

import frames.GameFrame;
import object.enumTypes.RankType;
import object.enumTypes.UnitType;
import object.factories.SoldierFactory;
import object.unit.player.Player;
import objectsController.ObjectsController;

import java.awt.*;
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
