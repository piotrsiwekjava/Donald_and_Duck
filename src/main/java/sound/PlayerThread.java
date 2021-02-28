package sound;

import jaco.mp3.player.MP3Player;

import java.io.File;

public class PlayerThread extends Thread{
    private MP3Player mp3_player;
    private String currentMusicLocation;

    public PlayerThread(String currentMusicLocation) {
        this.currentMusicLocation=currentMusicLocation;
    }

    public void run(){
        try{
            mp3_player = new MP3Player(new File(currentMusicLocation));
            mp3_player.play();
        }catch(Exception e){ System.err.println(e);}
    }
}
