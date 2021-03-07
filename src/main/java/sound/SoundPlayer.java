package sound;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.util.ArrayList;

public class SoundPlayer {
    private MusicThread soundTrackThread;
    private MP3Player mp3Player;
    private ArrayList <File> musicFiles;
    SoundPlayer(Mixer mixer ) {
        this.musicFiles=mixer.getMusicFiles();
    }
    public synchronized MusicThread playNewMusicThread(int number) {
        soundTrackThread = new MusicThread(number);
        soundTrackThread.start();
        return soundTrackThread;
    }
    public synchronized void startPlay(int number) {
        try {
            mp3Player.stop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        mp3Player = new MP3Player(musicFiles.get(number));
        mp3Player.play();
        mp3Player.setRepeat(true);
    }

    public synchronized void stopPlay(){
        mp3Player.stop();
    }

    public class MusicThread extends Thread {
        private MP3Player mp3_player;
        private int number;

        public MusicThread() {
        }

        public MusicThread(int number) {
            this.number = number;
        }

        public void run() {
            try {
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
    }
}
