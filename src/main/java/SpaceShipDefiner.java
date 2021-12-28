import com.googlecode.lanterna.graphics.TextGraphics;

public interface SpaceShipDefiner {
    Integer getHealth();
    Position getPosition();
    Integer getVelocity();
    Integer getDamage();
    List<Shot> getShots();
    List<PowerUp> getPowerUps();
    void removePowerUp();
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
    void removeShoot();
}
