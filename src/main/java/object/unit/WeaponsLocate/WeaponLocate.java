package object.unit.WeaponsLocate;

import object.factories.BodyPart;
import object.factories.Weapon;

public interface WeaponLocate {

    /**
     * set Angle for start
     * @return: [0]-to bodypart[4]; [1]-to bodypart[4]; [2] -to weapon
     */

    public boolean angleChanged();
    public double[] setAngleForStart();
    public void set_Odds_center_of_rotation_Left(BodyPart [] bodyParts, Weapon weapon, double[] size);
    public void set_Odds_center_of_rotation_Right(BodyPart [] bodyParts, Weapon weapon, double[] size);
    public void set_Odds_draw_Left(BodyPart [] bodyParts, Weapon weapon, double[] size);
    public void set_Odds_draw_Right(BodyPart [] bodyParts, Weapon weapon, double[] size);
}
