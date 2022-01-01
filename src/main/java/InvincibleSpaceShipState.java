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
        return spaceship.health;
    }

    @Override
    public Position getPosition() {
        return spaceship.position;
    }

    @Override
    public Integer getVelocity() {
        return spaceship.velocity;
    }

    @Override
    public Integer getDamage() {
        return spaceship.damage;
    }

    @Override
    public List<Shot> getShots() {
        return spaceship.shots;
    }

    @Override
    public void set_shots(List<Shot> shots) {
        spaceship.shots= shots;
    }

    @Override

    public List<SpellTemplate> getSpells() {
        return spaceship.spells;
    }

    @Override
    public void removePowerUp() {

    }

    @Override
    public boolean isDead() {
        return spaceship.health<=0;
    }

    @Override
    public void setHealth(Integer health) {
        spaceship.health=health;
    }

    @Override
    public void setPosition(Position position) {
        spaceship.position=position;
    }

    @Override
    public void setVelocity(Integer velocity) {
        spaceship.velocity = velocity;
    }

    @Override
    public void setDamage(Integer damage) {
        spaceship.damage = damage;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(spaceship.getPosition().getX(), spaceship.getPosition().getY()), "_");
    }

    @Override
    public void moveLeft() {
        spaceship.position.setX(spaceship.getPosition().getX()-1);
    }

    @Override
    public void moveRight() {
        spaceship.position.setX(spaceship.getPosition().getX()+1);
    }

    @Override
    public void shoot() {
        Position pos = new Position(spaceship.position.getX(),spaceship.position.getY());
        pos.setY(pos.getY()-1);
        Shot bullet = new Shot(getDamage(),1,1,pos);
        spaceship.shots.add(bullet);
    }

    @Override
    public void removeShot(Shot shot){
        spaceship.shots.remove(shot);
    }
}
