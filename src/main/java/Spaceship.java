import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spaceship implements SpaceShipDefiner{

    SpaceShipState state;
    Integer health, damage;
    Integer score = 0;
    Position position;
    List<Shot> shots;
    long last_transition_instant;
    SpaceshipObserver tpObserver;
    public Spaceship(Integer health,Integer damage, Position position){
        this.health = health;
        this.damage = damage;
        this.position = position;
        this.shots = new ArrayList<>();
        this.state = new NormalSpaceShipState(this);
        tpObserver = new SpaceshipObserver();
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
    public Integer getDamage() {
        return this.state.getDamage();
    }

    @Override
    public List<Shot> getShots() {
        return this.state.getShots();
    }

    @Override
    public  Integer getScore(){ return this.score;}

    @Override
    public boolean isDead() {
        return this.state.isDead();
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
    public void setDamage(Integer damage) {
        this.state.setDamage(damage);
    }

    @Override
    public void setScore(Integer score){this.score = score;}

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

    @Override
    public void removeShot(Shot shot) {
        this.state.removeShot(shot);
    }

    @Override
    public void addObserver(SpaceshipObserver spaceshipObserver) {
        tpObserver = spaceshipObserver;
    }

    void becomeInvincible(){
        this.state= new InvincibleSpaceShipState(this);
        this.last_transition_instant = System.currentTimeMillis();
    }

    void becomeNormal(){
        this.state = new NormalSpaceShipState(this);
    }

    void becomeNerfed() {
        this.state = new NerfedSpaceShipState(this);
        this.last_transition_instant = System.currentTimeMillis();
    }

    @Override
    public void caughtTPBack(Position pos){
        tpObserver.caughtTPback(this);
    }

    @Override
    public void usedTPBack(){
        tpObserver.usedTPback(this);
    }
}
