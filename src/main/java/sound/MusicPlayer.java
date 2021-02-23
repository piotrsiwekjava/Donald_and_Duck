package sound;

import object.unit.player.Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class MusicPlayer {

    private String currentMusicLocation;
    private ArrayList<String> musicNames;
    private String pathToFile;

    public MusicPlayer(){
        this.musicNames =new ArrayList<String>();
        loadPathToSoundTrack();
        changeMusic(2);
    }
    private void loadPathToSoundTrack(){
        pathToFile = "C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\D&DvsC\\src\\main\\resources\\music\\music";
        musicNames.add("\\fight.au");
        musicNames.add("\\menu.au");
        musicNames.add("\\nofight.au");
        musicNames.add("\\victory.au");
    }


    public void playMusic(){
        System.out.println("MusicPlay "+ currentMusicLocation);

        try{
            File musicPath = new File(currentMusicLocation);

            if (musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void changeMusic(int number){
        currentMusicLocation = pathToFile+ musicNames.get(number);
    }


}
