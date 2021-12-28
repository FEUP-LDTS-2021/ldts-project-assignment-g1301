import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spaceship implements SpaceShipDefiner{
    private Integer health, velocity,damage;
    private Position position;
    private String state;
    private List<PowerUp> powerUps;
    private List<Shot> shots;

    public Spaceship(Integer health,Integer velocity,Integer damage, Position position){
        this.health = health;
        this.velocity = velocity;
        this.damage = damage;
        this.position = position;
        shots = new ArrayList<>();
    }

    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Integer getVelocity() {
        return velocity;
    }

    @Override
    public Integer getDamage() {
        return damage;
    }

    @Override
    public List<Shot> getShots() {
        return shots;
    }

    @Override
    public void set_shots(List<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    @Override
    public void removePowerUp() {

    }

    @Override
    public boolean isDead() {
        return health<=0;
    }

    @Override
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    @Override
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("000000"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "_");
    }

    @Override
    public void moveLeft() {
        position.setX(position.getX()-1);
    }

    @Override
    public void moveRight() {
        position.setX(position.getX()+1);
    }

    @Override
    public void shoot() {
        Position pos = position;
        pos.setY(pos.getY()-1);
        Shot bullet = new Shot(1,1,1,pos);
        shots.add(bullet);
    }
}
