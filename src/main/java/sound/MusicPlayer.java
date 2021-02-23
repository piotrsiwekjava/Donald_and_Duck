package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {


    void playMusic(String musicLocation){

        try{
            File musicPath = new File(musicLocation);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch (Exception e){

        }
    }


}
