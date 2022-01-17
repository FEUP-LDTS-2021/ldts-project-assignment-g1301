import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spaceship implements SpaceShipDefiner{

    String state;
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
        this.state = "normal";
        tpObserver = new SpaceshipObserver();
    }

    @Override
    public Integer getHealth() {
        return this.health;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }


    @Override
    public Integer getDamage() {
        return this.damage;
    }

    @Override
    public List<Shot> getShots() {
        return this.shots;
    }

    @Override
    public  Integer getScore(){ return this.score;}

    @Override
    public boolean isDead() {
        return this.health<=0;
    }

    @Override
    public void setHealth(Integer health) {
        this.health=health;
    }

    @Override
    public void setPosition(Position position) {
        this.position=position;
    }

    @Override
    public void setDamage(Integer damage) {
        this.damage=damage;
    }

    @Override
    public void setScore(Integer score){this.score = score;}

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(this.position.getX(), this.position.getY()), "_");
    }

    @Override
    public void moveLeft() {
        this.position.setX(position.getX()-1);
    }

    @Override
    public void moveRight() {
        this.position.setX(position.getX()+1);
    }

    @Override
    public void shoot() {
        Position pos = new Position(this.position.getX(),this.position.getY());
        pos.setY(pos.getY()-1);
        Shot bullet = new Shot(getDamage(),1,1,pos);
        this.shots.add(bullet);
    }

    @Override
    public void removeShot(Shot shot) {
        this.shots.remove(shot);
    }

    @Override
    public void addObserver(SpaceshipObserver spaceshipObserver) {
        tpObserver = spaceshipObserver;
    }

    void becomeInvincible(){
        this.state= "invincible";
        this.last_transition_instant = System.currentTimeMillis();
    }

    void becomeNormal(){
        this.state ="normal";
    }

    void becomeNerfed() {
        this.state = "nerfed";
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
