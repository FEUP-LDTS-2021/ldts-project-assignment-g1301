import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements EnemyDefiner{
    private Position position;
    private Integer health,damage, velocity;
    private Integer with;
    boolean dead;
    List<Shot> shots;
    Enemy(Integer health, Position position, Integer velocity, Integer damage){
        this.position = position;
        this.velocity = velocity;
        this.damage = damage;
        dead = false;
        with = 1;
        shots = new ArrayList<>();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    @Override
    public Integer getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    @Override
    public Integer getDamage() {
        return damage;
    }

    @Override
    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    @Override
    public Integer getHealth() {
        return health;
    }

    @Override
    public void setHealth(Integer health) {
        this.health = health;
    }

    @Override
    public List<Shot> getShots() {
        return shots;
    }

    @Override
    public void setDead() {
        dead = true;
    }

    @Override
    public void moveLeft() {
        position.setX(position.getX() - 1);
    }

    @Override
    public void moveRight() {
        position.setX(position.getX() + 1);
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void shoot() {
        Shot s = new Shot(damage, with, velocity, position);
        shots.add(s);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(getColor()));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "R");
    }

    @Override
    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public void setWith(Integer with) {
        this.with = with;
    }

    @Override
    public String getColor() {
        String result;
        switch (velocity){
            case 1:
                result =  "#FFFFFF";
                break;
            case 2:
                result =  "#FFFF00";
                break;
            case 3:
                result =  "#00FFFF";
                break;
            default:
                result = "#FFFFFF";
                break;
        }
        return result;
    }
}
