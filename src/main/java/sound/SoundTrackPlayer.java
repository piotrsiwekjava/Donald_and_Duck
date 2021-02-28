package sound;

public class SoundTrackPlayer extends SoundPlayer {

    public SoundTrackPlayer() {
        changeMusic(2);
    }

    @Override
    protected void addToMusicList() {
        musicNames.add("\\fight.mp3");
        musicNames.add("\\menu.mp3");
        musicNames.add("\\nofight.mp3");
        musicNames.add("\\victory.mp3");
    }
}
