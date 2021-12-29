import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvincibleSpaceShipState implements SpaceShipState {

    private final Spaceship spaceship;

    public InvincibleSpaceShipState(Spaceship s){
        this.spaceship = s;
    }

    @Override
    public Integer getHealth() {
        return spaceship.getHealth();
    }

    @Override
    public Position getPosition() {
        return spaceship.getPosition();
    }

    @Override
    public Integer getVelocity() {
        return spaceship.getVelocity();
    }

    @Override
    public Integer getDamage() {
        return spaceship.getDamage();
    }

    @Override
    public List<Shot> getShots() {
        return spaceship.getShots();
    }

    @Override
    public void set_shots(List<Shot> shots) {
        spaceship.set_shots(shots);
    }

    @Override
    public List<SpellTemplate> getSpells() {
        return spaceship.getSpells();
    }

    @Override
    public void removePowerUp() {

    }

    @Override
    public boolean isDead() {
        return spaceship.getHealth()<=0;
    }

    @Override
    public void setHealth(Integer health) {
        spaceship.setHealth(health);
    }

    @Override
    public void setPosition(Position position) {
        spaceship.setPosition(position);
    }

    @Override
    public void setVelocity(Integer velocity) {
        spaceship.setVelocity(velocity);
    }

    @Override
    public void setDamage(Integer damage) {
        spaceship.setDamage(damage);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("ffffff"));
        graphics.putString(new TerminalPosition(spaceship.getPosition().getX(), spaceship.getPosition().getY()), "_");
    }

    @Override
    public void moveLeft() {
        spaceship.getPosition().setX(spaceship.getPosition().getX()-1);
    }

    @Override
    public void moveRight() {
        spaceship.getPosition().setX(spaceship.getPosition().getX()+1);
    }

    @Override
    public void shoot() {
        Position pos = spaceship.getPosition();
        pos.setY(pos.getY()-1);
        Shot bullet = new Shot(1,1,1,pos);
        spaceship.getShots().add(bullet);
    }
}
