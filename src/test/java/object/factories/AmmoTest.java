package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.AmmoType;
import object.enumTypes.UnitType;
import object.enumTypes.WhichWeaponType;
import object.unit.Unit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Tag("ammo")
class AmmoTest {

    private static BufferedImage image;
    private Unit unit;
    private Ammo ammo;

    @BeforeAll
    static void setUpForStart(){
        String path = "weapons\\bL";
        image = ImageGetterAndChanger.getInstance().getImage(path);
    }
    @BeforeEach
    void setUp() {
        createUnit();
        createGoodAmmo();
    }


    @AfterEach
    void tearDown() {
        unit = null;
        ammo = null;
    }

    @Tag("throws")
    @Test
    void creatingAmmoWithout_Point_ShouldThrowException(){
        assertThrows(NullPointerException.class,
                () -> new Ammo(null,null, null, null,0,10,unit));
    }

    @Tag("throws")
    @Test
    void creatingAmmoWithout_Unit_ShouldThrowException(){
        assertThrows(NullPointerException.class,
                () -> new Ammo(null,new Point(), null, null,0,0,null));
    }

    @DisplayName("Different types of ammunition can be created")
    @ParameterizedTest
    @MethodSource("giveDifferentValues")
    void givenAmmoShouldBeExisted(AmmoType type, Point point,int damage, int speed){
        //given
        Ammo ammo = new Ammo(type,point,null,null, damage,speed,unit);

        //then
        assertThat(ammo,notNullValue());
    }
    private static Stream<Arguments> giveDifferentValues() {
        return Stream.of(
                Arguments.of(AmmoType.A5MM,new Point(10,10), 10, 20),
                Arguments.of(AmmoType.A7MM,new Point(-110,-110), 1000, 20),
                Arguments.of(AmmoType.MISSILE,new Point(1000,1000), -20, 20)
        );
    }

    @Test
    void afterUseMethod_SetXY_AmmoShouldChangePosition() {

        //given

        double x = ammo.getPosition().getX();
        double y = ammo.getPosition().getY();

        //when
        ammo.setXY(5,5);

        //then
        assertThat(ammo.getPosition().getX(),is(x+5));
        assertThat(ammo.getPosition().getY(),is(y+5));
    }

    @RepeatedTest(10)
    @Test
    void afterUseMethod_setFly_AmmoShouldChange_nX_nY () {

        //given
        //ammo create in BeforeEach
        double x = ammo.getnX();
        double y = ammo.getnY();
        Point target = unit.getLookTarget().getPosition();
        target.setLocation(new Point(1000,1000));

        //when
        ammo.setFly();

        //then
        assertThat(ammo.getnX(),not(x));
        assertThat(ammo.getnY(),not(y));
    }

    @RepeatedTest(10)
    @Test
    synchronized void afterUseMethod_fly_AmmoShouldChangePosition() {
        //given
        double x = ammo.getPosition().getX();
        double y = ammo.getPosition().getY();
        int dx = (int)Math.floor(ammo.getnX() + 0.5D);
        int dy = (int)Math.floor(ammo.getnY() + 0.5D);

        //when
        ammo.fly();

        //then
        assertThat(ammo.getPosition().getX(),is(x+dx));
        assertThat(ammo.getPosition().getY(),is(y+dy));

    }

    @Tag("enemy")
    @Test
    void afterUseMethod_getDamageObject_WhenAmmoIsCloseToEnemyUnit_AmmoShouldMakeDamageToEnemy() {
        //given
        int dmg = ammo.getAmmoDamage();
        Unit enemy = SoldierFactory.create(UnitType.SOlDIER,WhichWeaponType.SoldierAK,new Point(250,250),1);
        int enemyHp = enemy.getHp();
        ammo.setPosition(enemy.getPosition());

        //when
        ammo.getDamageObject();

        //then
        assertThat(enemy.getHp(),greaterThanOrEqualTo(enemyHp-dmg));

        //finally
        enemy=null;
    }

    @Test
    void afterUseMethod_getDamageObject_WhenAmmoIs__NOT__CloseToEnemyUnit_AmmoShould__NOT__MakeDamageToEnemy() {
        //given
        int dmg = ammo.getAmmoDamage();
        Unit enemy = SoldierFactory.create(UnitType.SOlDIER,WhichWeaponType.SoldierAK,new Point(250,250),1);
        int enemyHp = enemy.getHp();

        //when
        ammo.getDamageObject();

        //then
        assertThat(enemy.getHp(),is(enemyHp));

        //finally
        enemy=null;
    }

    @Test
    void newlyAmmoShouldHasSpeed () {
        //given
        double speedAmmo = ammo.getSpeed();

        //then
        assertThat(true, is(speedAmmo>0));
    }

    private void createUnit(){
        unit = SoldierFactory.create(UnitType.SOlDIER,WhichWeaponType.SoldierAK,new Point(0,0),1);
    }

    private void createGoodAmmo() {
        ammo = new Ammo(null,new Point(10,10), null, null,0,10,unit);
    }

    @AfterAll
    static void allCleanUp(){
        image=null;
    }
}