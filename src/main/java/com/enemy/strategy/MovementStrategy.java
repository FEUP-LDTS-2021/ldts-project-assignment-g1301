package com.enemy.strategy;

import com.enemy.Enemy;

public interface MovementStrategy {
    void move(Enemy enemy, boolean right);
}
