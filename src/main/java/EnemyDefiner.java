import com.googlecode.lanterna.graphics.TextGraphics;

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
    void move();
    void normalShot();
    void bigShot();
    void damageShot();
    void moveRight();
    void setMoveRight(boolean moveRight);
    boolean isMovingDown();
    void setMoveDown(boolean moveDown);
    void moveDown();
    void moveUp();
    void shoot();
    void removeShot(Shot enemyShot);
}
