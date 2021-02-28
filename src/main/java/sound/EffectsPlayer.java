package sound;

import object.enumTypes.WeaponsType;

public class EffectsPlayer extends SoundPlayer{
    private String type;
//    public EffectsPlayer(String type){
//        this.type = type;
//    }
    @Override
    protected void addToMusicList() {
        musicNames.add(type+"reload.mp3");
        musicNames.add(type+"shoot.mp3");
        musicNames.add(type+"fly.mp3");
    }
}
