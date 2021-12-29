import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spaceship implements SpaceShipDefiner{
    private SpaceShipState state;
    private Integer health, velocity,damage;
    private Position position;
    private List<SpellTemplate> spells;
    private List<Shot> shots;

    public Spaceship(Integer health,Integer velocity,Integer damage, Position position){
        this.health = health;
        this.velocity = velocity;
        this.damage = damage;
        this.position = position;
        this.shots = new ArrayList<>();
        this.state = new NormalSpaceShipState(this);
    }

    @Override
    public Integer getHealth() {
        return this.state.getHealth();
    }

    @Override
    public Position getPosition() {
        return this.state.getPosition();
    }

    @Override
    public Integer getVelocity() {
        return  this.state.getVelocity();
    }

    @Override
    public Integer getDamage() {
        return this.state.getDamage();
    }

    @Override
    public List<Shot> getShots() {
        return this.state.getShots();
    }

    @Override
    public void set_shots(List<Shot> shots) {
        this.state.set_shots(shots);
    }

    @Override
    public List<SpellTemplate> getSpells() {
        return this.state.getSpells();
    }

    @Override
    public void removePowerUp() {
        this.state.removePowerUp();
    }

    @Override
    public boolean isDead() {
        return this.state.getDamage()<=0;
    }

    @Override
    public void setHealth(Integer health) {
        this.state.setHealth(health);
    }

    @Override
    public void setPosition(Position position) {
        this.state.setPosition(position);
    }

    @Override
    public void setVelocity(Integer velocity) {
        this.state.setVelocity(velocity);
    }

    @Override
    public void setDamage(Integer damage) {
        this.state.setDamage(damage);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        this.state.draw(graphics);
    }

    @Override
    public void moveLeft() {
        this.state.moveLeft();
    }

    @Override
    public void moveRight() {
        this.state.moveRight();
    }

    @Override
    public void shoot() {
        this.state.shoot();
    }
    void becomeInvincible(){
        this.state= new InvincibleSpaceShipState(this);
    }
    void becomeNormal(){
        this.state = new NormalSpaceShipState(this);
    }
}
