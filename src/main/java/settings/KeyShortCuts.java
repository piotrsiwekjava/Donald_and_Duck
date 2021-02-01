package settings;


import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyShortCuts {
    public static int UP = 87;  //38 up
    public static int DOWN = 83;    //40 down
    public static int LEFT = 65;       //37 left
    public static int RIGHT = 68;       //39 right
    public static int RUN = 16;     //16 L-shift
    public static char SWITHGUN = 'q'; // 81 - Q
    public static char fstWeapons = '1'; //49 - 1
    public static char secWeapons = '2'; //50 - 2
    public static char thirdWeapons = '3'; // 51 - 3
    public static char fourthWeapons = '4'; // 52 - 4

    public KeyShortCuts() {
    }
    public void changeKey(String type,String which){
        int int_whith = Integer.parseInt(which);
        if ("UP".equals(type)) {
            UP = int_whith;
        } else if ("DOWN".equals(type)) {
            DOWN = int_whith;
        } else if ("LEFT".equals(type)) {
            LEFT = int_whith;
        } else if ("RIGHT".equals(type)) {
            RIGHT = int_whith;
        } else if ("RUN".equals(type)) {
            RUN = int_whith;
        }
    }
}
