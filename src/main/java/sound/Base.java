package sound;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.util.ArrayList;

public abstract class Base implements Playing{
    private String currentMusicLocation;
    protected ArrayList<String> musicNames;
    protected String pathToFile;
    private MP3Player mp3_player;
    private PlayerThread player_thread;

    public Base (){
        this.musicNames = new ArrayList<String>();
        loadPathToSoundTrack();
        player_thread = new PlayerThread();
        player_thread.start();
    }

    @Override
    public void play() {
        mp3_player.play();
    }

    @Override
    public void stop() {
        mp3_player.stop();
    }

    @Override
    public void changeMusic(int number) {
        currentMusicLocation = pathToFile + musicNames.get(number);
    }

    private void loadPathToSoundTrack() {
        pathToFile = "C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\D&DvsC\\src\\main\\resources\\music\\music";
        addToMusicList();
    }

    protected abstract void addToMusicList();


    class PlayerThread extends Thread{
        public void run(){
            try{
                mp3_player = new MP3Player(new File(currentMusicLocation));
            }catch(Exception e){ System.err.println(e);}
        }
    }




}
