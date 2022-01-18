package com.spaceship;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;
import com.shot.Shot;
import com.spaceship.observer.SpaceshipObserver;

import java.io.IOException;
import java.util.List;

public interface SpaceShipDefiner {
    //State Pattern was used in the spaceship class
    Integer getHealth();
    Position getPosition();
    Integer getDamage();
    Integer getScore();
    List<Shot> getShots();
    boolean isDead();
    void setHealth(Integer health);
    void setPosition(Position position);
    void setDamage(Integer damage);
    void setScore(Integer score);
    void draw(TextGraphics graphics) throws IOException;
    void moveLeft();
    void moveRight();
    void shoot();
    void removeShot(Shot shot);

    void addObserver(SpaceshipObserver spaceshipObserver);

    void caughtTPBack(Position pos);

    void usedTPBack();
}
