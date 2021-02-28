package sound;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.util.ArrayList;

public class SoundPlayer implements PlayingInterfejs {
    private String currentMusicLocation;
    protected ArrayList<String> musicNames;
    protected String pathToFile;
    private MusicThread musicThread;

    public SoundPlayer(){
        this.musicNames = new ArrayList<String>();
        loadPathToSoundTrack();

    }

    @Override
    public void startPlay() {
        musicThread = new MusicThread();
        musicThread.start();
    }

    @Override
    public void stopPlay() {
        System.out.println("Sound Player + wciskam stop");
//        mp3_player.stop();
    }

    @Override
    public void changeMusic(int number) {
        currentMusicLocation = pathToFile + musicNames.get(number);
    }

    private void loadPathToSoundTrack() {
        pathToFile = "C:\\Users\\Hores di En'Claws\\Desktop\\Java\\ProjektyRozne\\D&DvsC\\src\\main\\resources\\music";
        addToMusicList();
    }

    protected void addToMusicList(){
        musicNames.add("\\music\\fight.mp3");
        musicNames.add("\\music\\menu.mp3");
        musicNames.add("\\music\\nofight.mp3");
        musicNames.add("\\music\\victory.mp3");
        musicNames.add("\\sounds\\akreload.mp3");
        musicNames.add("akshoot.mp3");
        musicNames.add("akfly.mp3");
        musicNames.add(type)
    }

    public synchronized MusicThread playNewMusicThread(int number){
        musicThread = new MusicThread();
        musicThread.start();
        return musicThread;
    }

    class MusicThread extends Thread implements PlayingInterfejs {
        private MP3Player mp3_player;

        public void run() {
            try {

                    mp3_player = new MP3Player(new File(currentMusicLocation));
                        mp3_player.play();
                        mp3_player.setRepeat(true);

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        @Override
        public void startPlay() {

        }

        @Override
        public void stopPlay() {
            this.interrupt();
        }

        @Override
        public void changeMusic(int number) {

        }
    }
}
