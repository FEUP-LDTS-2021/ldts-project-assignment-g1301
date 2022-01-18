package com.enemy;

import com.enemy.strategy.MovementStrategy;
import com.enemy.strategy.ShootingStrategy;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;
import com.shot.Shot;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements EnemyDefiner {
    private Position position;
    private final MovementStrategy movementStrategy;
    private final ShootingStrategy shootingStrategy;
    private Integer health;
    List<Shot> shots;
    boolean movingRight;
    boolean movingDown;

    public Enemy(Integer health, Position position, MovementStrategy movementStrategy, ShootingStrategy shootingStrategy) {
        this.position = position;
        this.health = health;
        this.movementStrategy = movementStrategy;
        this.shootingStrategy = shootingStrategy;
        this.shots = new ArrayList<Shot>();
        this.movingRight = true;
        this.movingDown = false;
    }

    @Override
    public MovementStrategy getMovementStrategy(){
        return movementStrategy;
    }

    @Override
    public ShootingStrategy getShootingStrategy(){
        return shootingStrategy;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }


    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public List<Shot> getShots() {
        return shots;
    }

    @Override
    public void move(boolean moveRight) {
        movementStrategy.move(this, moveRight);
    }

    @Override
    public void moveRight() {
        position.setX(position.getX() + 1);
    }

    @Override
    public void moveLeft(){position.setX(position.getX() - 1);}

    @Override
    public void moveUp() {
        position.setY(position.getY() - 1);
    }

    @Override
    public void moveDown() {
        position.setY(position.getY() + 1);
    }


    @Override
    public void setMoveRight(boolean moveRight) {
        movingRight = moveRight;
    }

    @Override
    public boolean isMovingDown() {
        return movingDown;
    }

    @Override
    public void setMoveDown(boolean moveDown) {
        movingDown = moveDown;
    }

    @Override
    public boolean isDead() {
        return health<=0;
    }

    @Override
    public void normalShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() + 1);
        shots.add(new Shot(100, 1,  aux));
    }

    @Override
    public void bigShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() +1);
        shots.add(new Shot(100, 3,  aux));
    }

    @Override
    public void damageShot() {
        Position aux = new Position(position.getX(),position.getY());
        aux.setY(aux.getY() +1);
        shots.add(new Shot(300, 1, aux));
    }

    @Override
    public void shoot() {
        shootingStrategy.shoot(this);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "R");
    }

    @Override
    public void removeShot(Shot enemyShot) {
        shots.remove(enemyShot);
    }
}
