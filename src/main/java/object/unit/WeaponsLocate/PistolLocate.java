package object.unit.WeaponsLocate;

import object.factories.BodyPart;
import object.factories.Weapon;

public class PistolLocate implements WeaponLocate{

    public PistolLocate (){}

    @Override
    public boolean angleChanged() {
        return false;
    }

    @Override
    public double[] setAngleForStart() {
        return new double[]{0,0,0};
    }

    @Override
    public void set_Odds_center_of_rotation_Left(BodyPart[] bodyParts, Weapon weapon, double[] size) {
        bodyParts[4].set_Odds_center_of_rotation(size[0]*0, size[1]*0.2);
        bodyParts[5].set_Odds_center_of_rotation(size[0]*0.9, size[1]*0.2);
        weapon.set_Odds_center_of_rotation(size[0] * 0.9, size[1] * 0.2);
    }

    @Override
    public void set_Odds_center_of_rotation_Right(BodyPart[] bodyParts, Weapon weapon, double []size) {
        bodyParts[4].set_Odds_center_of_rotation(size[0] * 0.9, size[1] * 0.2);
        bodyParts[5].set_Odds_center_of_rotation(size[0] * 0.1, size[1] * 0.2);
        weapon.set_Odds_center_of_rotation(size[0] * 0.1, size[1] * 0.2);
    }

    @Override
    public void set_Odds_draw_Left(BodyPart[] bodyParts, Weapon weapon, double[] size) {
        bodyParts[4].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        bodyParts[5].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        weapon.setOdds_draw(-size[0]*0.45,size[1]*0.35);
        weapon.set_odds_from_barrelTip_to_Center(-size[0]*0.55,size[1]*0.38);
    }

    @Override
    public void set_Odds_draw_Right(BodyPart[] bodyParts, Weapon weapon, double[] size) {
        bodyParts[4].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        bodyParts[5].setOdds_draw(-size[0]*0.3,-size[1]*0.1);
        weapon.setOdds_draw(size[0]*0.1,size[1]*0.35);
        weapon.set_odds_from_barrelTip_to_Center(size[0]*0.55,size[1]*0.38);
    }
}
