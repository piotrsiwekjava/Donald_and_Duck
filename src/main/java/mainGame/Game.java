package mainGame;

import level.Level;
import object.enumTypes.UnitType;
import object.unit.player.Player;
import object.unit.player.PlayerFactory;
import objectsController.ObjectsController;

public class Game {

    private Level level;
    private Player player;
    private ObjectsController objectsController;
    public Game(){
        this.player = givePlayer(UnitType.JARO);
        this.level = giveLevel(5);

        objectsController = ObjectsController.getInstance();
        set_player_and_level_in_objectController();
    }

    private Player givePlayer(UnitType type){
        PlayerFactory playerFactory = new PlayerFactory();
        return playerFactory.buildPlayer(type);
    }
    private Level giveLevel(int number){
        return new Level(number,"desert");
    }

    private void set_player_and_level_in_objectController() {
        objectsController.setLevel(level);
        objectsController.setPlayer(player);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
