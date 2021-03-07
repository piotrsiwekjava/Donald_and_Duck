package sound;

import jaco.mp3.player.MP3Player;
import settings.Pathes_and_Links;

import java.io.*;
import java.util.ArrayList;

public class SoundPlayer{
    private static SoundPlayer soundPlayer = new SoundPlayer();
    private String currentMusicLocation;
    protected ArrayList<String> musicNames;
    protected ArrayList<File> musicFiles;
    protected String pathToFile;
    private MusicThread soundTrackThread;
    private MP3Player mp3Player;

    private SoundPlayer() {
        this.musicNames = new ArrayList<String>();
        this.musicFiles = new ArrayList<File>();
        loadMusic();
    }

    public static SoundPlayer getMainSoundPlayer() {
        return soundPlayer;
    }
    public static SoundPlayer getNewSoundPlayer(){
        return new SoundPlayer();
    }

    public synchronized void startPlay(int number) {
        System.out.println("Sound Player będę grał");
        changeMusic(number);
        try {
            mp3Player.stop();
        }
        catch (Exception e){}
        mp3Player = new MP3Player(new File(currentMusicLocation));
        mp3Player.play();
        mp3Player.setRepeat(true);
        System.out.println("Sound Player powinnienem grać");
    }

    public synchronized void stopPlay(){
        mp3Player.stop();
    }

    public synchronized void changeMusic(int number) {
        currentMusicLocation = pathToFile + musicNames.get(number);
    }

    private void loadMusic() {
        pathToFile = Pathes_and_Links.pathToMusic;
        addToMusicList();
        addToFileList();
    }

    protected void addToMusicList() {
        musicNames.add("music\\menu.mp3");
        musicNames.add("music\\nofight.mp3");
        musicNames.add("music\\fight.mp3");
        musicNames.add("music\\victory.mp3");
        musicNames.add("sounds\\akreload.mp3");
        musicNames.add("sounds\\akshoot.mp3");
        musicNames.add("sounds\\akfly.mp3");
        musicNames.add("sounds\\pistolreload.mp3");
        musicNames.add("sounds\\pistolshoot.mp3");
        musicNames.add("sounds\\pistolfly.mp3");
        musicNames.add("sounds\\hitHuman1.mp3");
        musicNames.add("sounds\\hitHuman2.mp3");
        musicNames.add("sounds\\hitMiss.mp3");
    }
    protected void addToFileList() {
        for (String str : musicNames) {
            try {
//                BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(pathToFile+str));
//                musicFiles.add(bIS);
                File file = new File(pathToFile + str);
                musicFiles.add(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized MusicThread playNewMusicThread(int number) {
        soundTrackThread = new MusicThread(number);
        soundTrackThread.start();
        return soundTrackThread;
    }

    public class MusicThread extends Thread {
        private MP3Player mp3_player;
        private int number;

        public MusicThread() {
        }

        public MusicThread(int number) {
            this.number = number;
            changeMusic(number);
        }

        public void run() {
            try {
                System.out.println("Music thread zaczynam grać");
                mp3_player = new MP3Player(musicFiles.get(number));
                mp3_player.play();
                try {
                    if (number < 4) {
                        mp3_player.setRepeat(true);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void stopplay(){
            mp3_player.stop();
        }
        public void changePlay(int number){
            mp3_player.addToPlayList(musicFiles.get(number));
        }

    }
}
