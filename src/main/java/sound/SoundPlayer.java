package sound;

import jaco.mp3.player.MP3Player;
import object.unit.player.Player;

import java.io.File;
import java.util.ArrayList;

public abstract class SoundPlayer implements Playing{
    private String currentMusicLocation;
    protected ArrayList<String> musicNames;
    protected String pathToFile;

    public SoundPlayer(){
        this.musicNames = new ArrayList<String>();
        loadPathToSoundTrack();

    }

    @Override
    public void play() {
        PlayerThread player_thread = new PlayerThread(currentMusicLocation);
        player_thread.start();
    }

    @Override
    public void stop() {
        System.out.println("Sound Player + wciskam stop");
//        mp3_player.stop();
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

}
