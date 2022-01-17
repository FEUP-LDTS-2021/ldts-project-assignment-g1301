package enemy;

import com.googlecode.lanterna.graphics.TextGraphics;
import enemy.strategy.MovementStrategy;
import enemy.strategy.ShootingStrategy;
import position.Position;
import shot.Shot;

import java.util.List;

public interface EnemyDefiner {
    Integer getHealth();
    Position getPosition();
    List<Shot> getShots();
    MovementStrategy getMovementStrategy();
    ShootingStrategy getShootingStrategy();
    String getColor();
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
