import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public interface SpaceShipDefiner {
    //State Pattern was used in the spaceship class
    Integer getHealth();
    Position getPosition();
    Integer getVelocity();
    Integer getDamage();
    List<Shot> getShots();
    void set_shots (List<Shot> shots);
    public List<SpellTemplate> getSpells();
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
    void removeShot(Shot shot);
}
