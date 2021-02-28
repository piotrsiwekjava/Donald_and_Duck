package sound;

import jaco.mp3.player.MP3Player;
import settings.Pathes_and_Links;

import java.io.File;
import java.util.ArrayList;

public class SoundPlayer{
    private static SoundPlayer soundPlayer = new SoundPlayer();
    private String currentMusicLocation;
    protected ArrayList<String> musicNames;
    protected String pathToFile;
    private MusicThread soundTrackThread;
    private MP3Player mp3Player;

    private SoundPlayer() {
        this.musicNames = new ArrayList<String>();
        loadPathToSoundTrack();

    }

    public synchronized void startPlaySoundTrack(int number) {
        System.out.println("Sound Player będę grał");
        changeMusic(number);
//        soundTrackThread = new MusicThread();
//        soundTrackThread.start();
        try {
            mp3Player.stop();
        }
        catch (Exception e){}
        mp3Player = new MP3Player(openNewFile(currentMusicLocation));
        mp3Player.play();
        mp3Player.setRepeat(true);
        System.out.println("Sound Player powinnienem grać");
    }

    public synchronized void stopPlay() {
        System.out.println("Sound Player + wciskam stop");
    }

    public synchronized void changeMusic(int number) {
        currentMusicLocation = pathToFile + musicNames.get(number);
    }

    public static SoundPlayer getMainSoundPlayer() {
        return soundPlayer;
    }
    public static SoundPlayer getNewSoundPlayer(){
        return new SoundPlayer();
    }

    private void loadPathToSoundTrack() {
        pathToFile = Pathes_and_Links.pathToMusic;
        addToMusicList();
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

    public synchronized MusicThread playNewMusicThread(int number) {
        soundTrackThread = new MusicThread(number);
        soundTrackThread.start();
        return soundTrackThread;
    }

    private File openNewFile(String currentMusicLocation) {
        return new File(currentMusicLocation);
    }

    class MusicThread extends Thread {
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
                mp3_player = new MP3Player(openNewFile(currentMusicLocation));
                mp3_player.play();
                try {
                    if (number < 4) {
                        System.out.println("Music thread powtarzam");
                        mp3_player.setRepeat(true);
                    }
                }
                catch (Exception e){
                    System.out.println("Music thread nie powtarzam");
                    e.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println("Music thread nie gram");
                e.printStackTrace();
            }
        }

    }
}
