package com.arena;

import com.enemy.Enemy;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.spaceship.Spaceship;
import com.spell.template.SpellTemplate;

import java.io.IOException;
import java.util.List;

public interface ArenaDefiner {

    void setLevel(Integer level);

    List<Enemy> createEnemies();

    void addEnemy(Enemy e);

    void addSpell();

    void setSpaceship(Spaceship s);

    Integer getLevel();

    List<Enemy> getEnemies();

    List<SpellTemplate> getSpells();

    Spaceship getSpaceship();

    Integer getWidth();

    Integer getHeight();

    void draw(TextGraphics graphics) throws IOException;

    void checkCaughtSpell();

    void checkShotCollisions();

    void checkShotCollisionsHelper(int i,int j);

    void CollisionRemover(int i,int j,int k,List<Integer>shots);

    void checkShotsHitSpaceship();

    void processKey(KeyStroke key);

    void checkShotsHitEnemies();

    void removeShotsOutOfBounds();

    void removeShotsOutOfBoundsHelper();

    void moveEnemies();

    void moveShots();

    void shootEnemies();

    void updateSpaceShipState();

    void checkActiveSpells();

    void createSpell();

    void moveEnemiesLeft();

    void moveEnemiesRight();
}
