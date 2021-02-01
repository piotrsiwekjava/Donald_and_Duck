package level;

import object.BackGround;
import object.ImageGetterAndChanger;
import object.factories.Building;
import object.factories.BuildingsFactory;
import object.factories.Street;
import object.factories.StreetsFactory;
import objectsController.ObjectsController;

import java.awt.image.BufferedImage;

public class Level {
    private final int number;
    private final String name;
    private BackGround background;
    private Street[] streets;
    private Building[] buildings;
    private int distance;
    private int enemyRespawn; //distanceOfLastRespawn;
    private ObjectsController objController;

    public Level(int number, String name) {
        this.number = number;
        this.name = name;
        build();
        this.distance = 0;
        this.enemyRespawn=0;
        this.objController = ObjectsController.getInstance();
        objController.setLevel(this);
    }
    private void build(){
        buildBack();
        buildStreets();
        buildBuldings();
    }
    private void buildBack(){
    BufferedImage image = ImageGetterAndChanger.getInstance().getImage("level\\"+name+"back");
    this.background = new BackGround(image);
    }
    private void buildBuldings(){
        this.buildings = BuildingsFactory.createBuldings();
    }
    private void buildStreets(){
    this.streets = StreetsFactory.create(name);
    }

    public BackGround getBackground() {
        return background;
    }

    public Street[] getStreets() {
        return streets;
    }

    public void setStreets(Street[] streets) {
        this.streets = streets;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public void setBuildings(Building[] buildings) {
        this.buildings = buildings;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void pass200m_and_AddEnemy(int addTodistance) {
        this.distance += addTodistance;
        this.enemyRespawn += addTodistance;
        if (enemyRespawn>200){
            objController.addUnitToGame();
            enemyRespawn=0;
        }
    }
}
