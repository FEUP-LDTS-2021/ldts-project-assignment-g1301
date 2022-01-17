package enemy.strategy;

import enemy.Enemy;

public class DamageShotStrategy implements ShootingStrategy {
    @Override
    public void shoot(Enemy enemy) {
        enemy.damageShot();
    }
}
