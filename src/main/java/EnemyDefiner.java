import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public interface EnemyDefiner {
    Integer getHealth();
    Position getPosition();
    Integer getVelocity();
    Integer getDamage();
    List<Shot> getShots();
    void setShots(List<Shot> shots);
    void setWith(Integer with);
    String getColor();
    boolean isDead();
    void setHealth(Integer health);
    void setPosition(Position position);
    void setVelocity(Integer velocity);
    void setDamage(Integer damage);
    void setDead();
    void draw(TextGraphics graphics);
    void moveLeft();
    void moveRight();
    void shoot();
}
