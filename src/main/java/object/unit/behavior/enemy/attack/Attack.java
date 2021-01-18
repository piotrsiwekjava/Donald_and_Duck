package object.unit.behavior.enemy.attack;

import object.factories.Weapon;
import threads.Shoot_Runnable;

import java.awt.*;

public class Attack implements AttackInerfejs{
    @Override
    public void attack(Weapon weapon) {
        Shoot_Runnable.Shoot(weapon);
    }
}
