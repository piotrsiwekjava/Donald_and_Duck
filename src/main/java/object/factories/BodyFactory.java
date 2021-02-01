package object.factories;

import object.ImageGetterAndChanger;
import object.enumTypes.RankType;
import settings.Sizes;
import java.awt.*;
import java.awt.image.*;
import java.util.Random;

public class BodyFactory {

    private Point position;
    private double [] size;
    private int nr;
    private static BodyFactory bodyFactory = new BodyFactory();
    private ImageGetterAndChanger imageGetterAndChanger = ImageGetterAndChanger.getInstance();


    private BodyFactory (){
    }
    public static BodyFactory getInstance(){
        return bodyFactory;
    }

    public BodyPart[] Create_Body(RankType rankType, Point position){
        this.position=position;
        this.size = Sizes.Soldier_Size;

        switch (rankType){
            case OFICER:
                nr = 1;
                return getBodyParts();

            case SOlDIER:
                nr = new Random().nextInt(3) + 2;
                return getBodyParts();
            case JARO:
                nr = 11;
                this.size = Sizes.LittleGuy_Size;
                return getBodyParts();
            case DONALD:
                nr = 12;
                return getBodyParts();
            case OWNPLAYER:
                nr = 13;
            default:
                throw new UnsupportedOperationException("No such type");
        }
    }
    private BodyPart[] getBodyParts(){
        BodyPart [] bodyParts = new BodyPart[6];
        bodyParts[0]=addTorso();
        bodyParts[1]=addHead();
        BodyPart [] legs = addLegs();
        bodyParts[2]=legs[0];
        bodyParts[3]=legs[1];
        BodyPart [] arms = addArms();
        bodyParts[4] = arms[0];
        bodyParts[5] = arms[1];

        return bodyParts;
    }


    private BodyPart addTorso() {
        return new BodyPart(position,size,
                imageGetterAndChanger.getTransImg("bodyparts\\torso"+nr)
        );
    }
    private BodyPart addHead(){
        return new BodyPart(position,size,
                imageGetterAndChanger.getTransImg("bodyparts\\head"+nr)
                );
    }
    private BodyPart[] addLegs() {
        BodyPart [] bodyParts = new BodyPart[2];
        BufferedImage leg = imageGetterAndChanger.getTransImg("bodyparts\\legs"+nr);
        bodyParts[0] = new BodyPart(position,size,leg);
        bodyParts[1] = new BodyPart(position,size,leg);
        return bodyParts;
    }

    private BodyPart[] addArms(){
        BodyPart [] bodyParts = new BodyPart[2];
        BufferedImage arm = imageGetterAndChanger.getTransImg("bodyparts\\arms"+nr);
        bodyParts[0] = new BodyPart(position,size,arm);
        bodyParts[1] = new BodyPart(position,size, imageGetterAndChanger.mirrorImage(arm));

        return bodyParts;
    }




}
