package settings;

import com.sun.glass.ui.Size;

import java.awt.*;

public class Sizes {
    public static int Screen_Width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int Screen_Height = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static double SIZE = Screen_Width*0.0003;
    public static double [] X_MAX_MIN_WALK = {-Screen_Width*0.3, Screen_Width*0};
    public static double [] Y_MAX_MIN_WALK = {-Screen_Height*0.05, Screen_Height*0.2};
    public static double [] Soldier_Size = {SIZE,SIZE};
    public static double [] LittleGuy_Size = {SIZE,SIZE*0.6};
    public static double [] A5MM = {SIZE*10,SIZE*10};
    public static double [] A7MM = {SIZE*100, SIZE*100};
    public static double [] MISSILE = {SIZE*15,SIZE*60};
    public static double [] Pistol = {SIZE*100,SIZE*50};
    public static double [] AK = {SIZE*190,SIZE*100};
    public static double [] Bazooka = {20,100};
    public static int LEVELWidth = 5000;
    public static int LEVELHeight = (int)(Screen_Height*0.45);
    public static double[] STREETSSIZE = {Screen_Width*0.25,Screen_Height*0.8};
    public static double Game_Speed = 3;
    public static double WALK_Speed = 2;
    public static double RUN_Speed = 5;
}
