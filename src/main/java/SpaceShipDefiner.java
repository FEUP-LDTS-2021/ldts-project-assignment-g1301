import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

//State Pattern Implemented because the power-ups change the state of the spaceship
public interface SpaceShipDefiner {
    Integer getHealth();
    Position getPosition();
    Integer getVelocity();
    Integer getDamage();
    List<Shot> getShots();
    void set_shots (List<Shot> shots);
    List<PowerUp> getPowerUps();
    void removePowerUp();
    boolean isDead();
    void setHealth(Integer health);
    void setPosition(Position position);
    void setVelocity(Integer velocity);
    void setDamage(Integer damage);
    void draw(TextGraphics graphics) throws IOException;
    void moveLeft();
    void moveRight();
    void shoot();
}
