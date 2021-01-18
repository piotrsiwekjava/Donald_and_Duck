package settings;


import java.awt.event.KeyEvent;

public class KeyShortCuts {
    public static int UP = 87;  //38up
    public static int DOWN = 83;    //40down
    public static int LEFT = 65;       //37left
    public static int RIGHT = 68;       //39right
    public static int RUN = 16;     //16shift

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
