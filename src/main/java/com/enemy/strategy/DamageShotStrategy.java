package com.enemy.strategy;

import com.enemy.Enemy;

public class DamageShotStrategy implements ShootingStrategy {
    @Override
    public void shoot(Enemy enemy) {
        enemy.damageShot();
    }
}
