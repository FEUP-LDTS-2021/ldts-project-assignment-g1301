package com.enemy.strategy;

import com.enemy.Enemy;

public class ZigZagMovementStrategy implements MovementStrategy {
    @Override
    public void move(Enemy enemy, boolean right) {
        if (enemy.isMovingDown()){
             enemy.moveUp();
             enemy.setMoveDown(false);
        } else {
            enemy.moveDown();
            enemy.setMoveDown(true);
        }
        if (right)
            enemy.moveRight();
        else enemy.moveLeft();
    }
}
