import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public interface SpaceShipState {
    Integer getHealth();
    Position getPosition();
    Integer getDamage();
    List<Shot> getShots();
    boolean isDead();
    void setHealth(Integer health);
    void setPosition(Position position);
    void setDamage(Integer damage);
    void draw(TextGraphics graphics) throws IOException;
    void moveLeft();
    void moveRight();
    void shoot();
    void removeShot(Shot shot);
}
