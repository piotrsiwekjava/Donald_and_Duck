package sound;

import jaco.mp3.player.MP3Player;
import settings.Pathes_and_Links;

import java.io.*;
import java.util.ArrayList;

public class Mixer {
    private static Mixer mixer = new Mixer();
    protected ArrayList<String> musicNames;
    protected ArrayList<File> musicFiles;
    protected String pathToFile;
    private static SoundPlayer soundPlayer = new SoundPlayer(mixer);
    private static SoundPlayer secondPlayer = new SoundPlayer(mixer);

    private Mixer() {
        this.musicNames = new ArrayList<String>();
        this.musicFiles = new ArrayList<File>();
        loadMusic();
    }

    public static SoundPlayer getMainSoundPlayer() {
        return soundPlayer;
    }

    public static SoundPlayer getSecondPlayer() {
        return secondPlayer;
    }
    public static SoundPlayer getNewSoundPlayer(){
        return new SoundPlayer(mixer);
    }


    private void loadMusic() {
        pathToFile = Pathes_and_Links.pathToMusic;
        addToMusicList();
        addToFileList();
    }

    protected void addToMusicList() {
        musicNames.add("music\\menu.mp3");      //0
        musicNames.add("music\\nofight.mp3");   //1
        musicNames.add("music\\fight.mp3");     //2
        musicNames.add("music\\victory.mp3");   //3
        musicNames.add("sounds\\akreload.mp3"); //4
        musicNames.add("sounds\\akshoot.mp3");  //5
        musicNames.add("sounds\\akfly.mp3");    //6
        musicNames.add("sounds\\pistolreload.mp3");//7
        musicNames.add("sounds\\pistolshoot.mp3");//8
        musicNames.add("sounds\\pistolfly.mp3");//9
        musicNames.add("sounds\\hitHuman1.mp3");//10
        musicNames.add("sounds\\hitHuman2.mp3");//11
        musicNames.add("sounds\\hitMiss.mp3");//12
        musicNames.add("sounds\\hitmetal1.mp3");//13
        musicNames.add("sounds\\hitmetal2.mp3");//14
        musicNames.add("sounds\\grenadeexplosion.mp3");//15
        musicNames.add("sounds\\grenadefly.mp3");//16
        musicNames.add("sounds\\grenedeshoot.mp3");//17
        musicNames.add("sounds\\metalpick.mp3");//18
        musicNames.add("sounds\\humandead.mp3");//19
        musicNames.add("sounds\\metaldead.mp3");//20
    }
    protected void addToFileList() {
        for (String str : musicNames) {
            try {
                File file = new File(pathToFile + str);
                musicFiles.add(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ArrayList<File> getMusicFiles() {
        return musicFiles;
    }
}
