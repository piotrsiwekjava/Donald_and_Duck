package object;

import object.unit.Move_Look_Point;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


@Tag("objectImage")
@DisplayName("Test cases for ObjectImage - class extends from ObjectGame")
class ObjectImageTest {

    private Point p;
    double [] size;
    BufferedImage image;

    @BeforeEach
    void initializeObject() {
        System.out.println("Before each");
        p = new Point(1,1);
        size =  new double[]{2,2};
        String path = "weapons\\bL";
        image = ImageGetterAndChanger.getInstance().getImage(path);
    }

    @AfterEach
    void cleanUp() {
        System.out.println("After each");
        p = null;
        size = null;
        image = null;
    }

    @Test
    void newlyObjectImageShouldHasPositionSizeAndImage() {

        //given

        ObjectImage objectImage = new ObjectImage(p,size,image);

        //then
        assertThat(objectImage, notNullValue());
        assertThat(objectImage.getPosition(),notNullValue());
        assertThat(objectImage.getPosition(),is(p));
        assertThat(objectImage.getSize(),notNullValue());
        assertThat(objectImage.getSize(),is(size));
        assertThat(objectImage.getImage(),notNullValue());
        assertThat(objectImage.getImage(),is(image));
    }

    @Test
    void newlyObjectImageifHasnullPositionShouldThrowException(){
        //given
        Point p = null;

        //then
        assertThrows(NullPointerException.class, () -> new ObjectImage(p,size,image));
    }

    @Test
    void newlyObjectImageWithNullSizeShouldHasNullValueSize(){
        //given
        double [] size = null;

        //when
        ObjectImage objectImage =  new ObjectImage(p,size,image);

        //then
        assertThat(objectImage.getSize(),nullValue());

    }

    @Test
    void moveObjectHasThatSameinShiftedPointofObject(){
        //given
        ObjectImage objectImage =  new ObjectImage(p,size,image);
        double x = objectImage.getPosition().getX();
        double y = objectImage.getPosition().getY();

        //when
        objectImage.setXY(5,5);

        //then
        assertThat(objectImage.getPosition().getX(), is(x+5));
        assertThat(objectImage.getPosition().getY(), is(y+5));
    }


}