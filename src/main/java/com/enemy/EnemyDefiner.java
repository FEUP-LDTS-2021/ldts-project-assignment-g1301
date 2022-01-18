package com.enemy;

import com.enemy.strategy.MovementStrategy;
import com.enemy.strategy.ShootingStrategy;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;
import com.shot.Shot;

import java.util.List;

public interface EnemyDefiner {
    Integer getHealth();
    Position getPosition();
    List<Shot> getShots();
    MovementStrategy getMovementStrategy();
    ShootingStrategy getShootingStrategy();
    boolean isDead();
    void setHealth(Integer health);
    void setPosition(Position position);
    void draw(TextGraphics graphics);
    void move(boolean moveRight);
    void normalShot();
    void bigShot();
    void damageShot();
    void moveRight();
    void moveLeft();
    void setMoveRight(boolean moveRight);
    boolean isMovingDown();
    void setMoveDown(boolean moveDown);
    void moveDown();
    void moveUp();
    void shoot();
    void removeShot(Shot enemyShot);
}
