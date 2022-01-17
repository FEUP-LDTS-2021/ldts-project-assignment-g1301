package arena;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import enemy.Enemy;
import enemy.strategy.*;
import position.Position;
import shot.Shot;
import spaceship.Spaceship;
import spaceship.observer.SpaceshipObserver;
import spell.template.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Arena implements ArenaDefiner {
    private Spaceship spaceship;
    private List<Enemy> enemies;
    private List<SpellTemplate> spells;
    private final Integer width;
    private final Integer height;
    private Integer level;
    private boolean movingRight;


    @Override
    public void setLevel(Integer level){
        this.level = level;
    }



    public Enemy createEnemy(Position pos){
        Enemy e;
        Integer health = 100 + (getLevel()/6)*100;
        switch (getLevel() % 6) {
            case 0:
                e = new Enemy(health, pos, new HorizontalMovementStrategy(), new NormalShotStrategy());
                break;
            case 1:
                e = new Enemy(health, pos, new ZigZagMovementStrategy(), new NormalShotStrategy());
                break;
            case 2:
                e = new Enemy(health, pos, new HorizontalMovementStrategy(), new DamageShotStrategy());
                break;
            case 3:
                e = new Enemy(health, pos, new ZigZagMovementStrategy(), new DamageShotStrategy());
                break;
            case 4:
                e = new Enemy(health, pos, new HorizontalMovementStrategy(), new BigShotStrategy());
                break;
            default:
                e = new Enemy(health, pos, new ZigZagMovementStrategy(), new BigShotStrategy());
                break;
        }
        return e;
    }

    @Override
    public List<Enemy> createEnemies(){
        for(Integer x=15;x<=135;x+=20){
            for (Integer y=2;y<=8;y+=2) {
                addEnemy(createEnemy(new Position(x,y)));
            }
        }
        level++;
        return enemies;
    }

    @Override
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }


    public SpellTemplate generateSpell(Position pos){
        Random random = new Random();
        int spellNr = random.nextInt(7);
        SpellTemplate spell;
        switch (spellNr) {
            case 0:
                spell = new SpellHealth(pos);
                break;
            case 1:
                spell = new SpellHealthDamage(pos);
                break;
            case 2:
                spell = new SpellGunDamage(pos);
                break;
            case 3:
                spell = new SpellLessGunDamage(pos);
                break;
            case 4:
                spell = new SpellInvincible(pos);
                break;
            case 5:
                spell = new SpellTPBack(pos);
                break;
            default:
                spell = new SpellNerfed(pos);
                break;
        }
        return spell;
    }
    @Override
    public void addSpell() {
        Random random = new Random();
        List<Integer> listX = new ArrayList<>();
        for(Integer i=0;i<width;i++){
            listX.add(i);
        }
        for(SpellTemplate spell:spells){
            listX.remove(spell.getPosition().getX());
        }
        if(listX.size()>1) {
            Position pos = new Position(listX.get(random.nextInt(listX.size() - 1)), getHeight() - 2);
            SpellTemplate spell = generateSpell(pos);
            spells.add(spell);
        }
        else if (listX.size()==1){
            Position pos = new Position(listX.get(0),getHeight()-2);
            SpellTemplate spell = generateSpell(pos);
            spells.add(spell);
        }
    }

    @Override
    public void checkActiveSpells() {
        for (int i = 0; i < spells.size(); i++){
            if (spells.get(i).getTime() + 10000 < System.currentTimeMillis()){
                spells.remove(i);
            }
        }
    }

    @Override
    public void setSpaceship(Spaceship s){
        spaceship = s;
    }

    @Override
    public Integer getLevel(){
        return level;
    }

    public Arena(Integer width, Integer height){
        this.width = width;
        this.height = height;
        this.level = 0;
        spaceship = new Spaceship(1000,100,new Position(width/2,height-2));
        spaceship.addObserver(new SpaceshipObserver());
        enemies = new ArrayList<>();
        spells = new ArrayList<>();
        movingRight = true;
    }

    @Override
    public List<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public List<SpellTemplate> getSpells() {
        return spells;
    }

    @Override
    public Spaceship getSpaceship() {
        return spaceship;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#22347D"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(new TerminalPosition(0,0), "Health : " + spaceship.getHealth());
        graphics.putString(new TerminalPosition(14,0), "Damage : " + spaceship.getDamage());
        graphics.putString(new TerminalPosition(28,0), "Score : " + spaceship.getScore());
        for (Enemy enemy : getEnemies()) {
            for (Shot shot : enemy.getShots()) {
                shot.draw(graphics);
            }
            enemy.draw(graphics);
        }
        for (SpellTemplate spell : getSpells()) {
            spell.draw(graphics);
        }
        spaceship.draw(graphics);
        for(Shot shot:spaceship.getShots()){
            shot.draw(graphics);
        }
    }


    public void giveSpaceShipSpell(SpellTemplate s){
        switch(s.getSymbol()){
            case 'I':
                spaceship.becomeInvincible();
                break;
            case 'D':
                spaceship.setDamage(spaceship.getDamage()+100);
                break;
            case 'H':
                spaceship.setHealth(spaceship.getHealth()+300);
                break;
            case 'X':
                if(!Objects.equals(spaceship.state, "invincible"))
                    spaceship.setHealth(spaceship.getHealth()-200);
                break;
            case 'L':
                spaceship.setDamage(spaceship.getDamage()-100);
                break;
            case 'N':
                spaceship.becomeNerfed();
                break;
            case 'T':
                spaceship.caughtTPBack(spaceship.getPosition());
                break;
            default:
                break;
        }
    }

    @Override
    public void checkCaughtSpell(){
        for(Integer i=0;i<spells.size();i++){
            if(getSpells().get(i).getPosition().getX().equals(spaceship.getPosition().getX())){
                giveSpaceShipSpell(getSpells().get(i));
                getSpells().remove(getSpells().get(i));
            }
        }
    }


    @Override
    public void updateSpaceShipState(){
        if(System.currentTimeMillis()-spaceship.last_transition_instant>=10000 &&
                !Objects.equals(spaceship.state, "normal")){
            spaceship.becomeNormal();
        }
    }

    @Override
    public void checkShotCollisions() {
        for (Integer i = 0;i<enemies.size();i++) {
            for (Integer j =0; j < enemies.get(i).getShots().size();j++) {
                for (Integer k=0; k< spaceship.getShots().size();k++) {
                    if (enemies.get(i).getShots().get(j).getPosition().getY().equals(spaceship.getShots().get(k).getPosition().getY())) {
                        Integer shot1WidthOffset = enemies.get(i).getShots().get(j).getWidth() / 2;
                        Integer shot2WidthOffset = spaceship.getShots().get(k).getWidth() / 2;
                        Integer shot1left = enemies.get(i).getShots().get(j).getPosition().getX() - shot1WidthOffset;
                        Integer shot1right = enemies.get(i).getShots().get(j).getPosition().getX() + shot1WidthOffset;
                        Integer shot2left = spaceship.getShots().get(k).getPosition().getX() - shot2WidthOffset;
                        Integer shot2right = spaceship.getShots().get(k).getPosition().getX() + shot2WidthOffset;
                        if (!((shot1left < shot2left && shot1right < shot2right) || (shot1left > shot2left && shot1right > shot2right))) {
                            spaceship.removeShot(spaceship.getShots().get(k));
                            enemies.get(i).removeShot(enemies.get(i).getShots().get(j));
                            j--;
                        }
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character){
            switch (key.getCharacter()) {
                case 'a':
                    if (spaceship.getPosition().getX() > 0)
                        spaceship.getPosition().setX(spaceship.getPosition().getX() - 1);
                    break;
                case 'd':
                    if (spaceship.getPosition().getX() < getWidth()-1)
                        spaceship.getPosition().setX(spaceship.getPosition().getX() + 1);
                    break;
                case 't':
                    spaceship.usedTPBack();
                    break;
                case ' ':
                    if(!Objects.equals(spaceship.state, "nerfed"))
                        spaceship.shoot();
                    break;
            }
        }
    }

    @Override
    public void checkShotsHitEnemies() {
        for (Integer i=0;i<spaceship.getShots().size();i++) {
            Integer shotWidthOffset = spaceship.getShots().get(i).getWidth() / 2;
            Integer shotLeft = spaceship.getShots().get(i).getPosition().getX() - shotWidthOffset;
            Integer shotRight = spaceship.getShots().get(i).getPosition().getX() + shotWidthOffset;

            for (Integer j=0;j<enemies.size();j++) {
                if (spaceship.getShots().get(i).getPosition().getY().equals(enemies.get(j).getPosition().getY()) &&
                        shotLeft <= enemies.get(j).getPosition().getX() &&
                        shotRight >= enemies.get(j).getPosition().getX()) {

                    enemies.get(j).setHealth(enemies.get(j).getHealth() - spaceship.getShots().get(i).getDamage());

                    spaceship.removeShot(spaceship.getShots().get(i));
                    i--;

                    if (enemies.get(j).getHealth()<=0) {
                        enemies.remove(enemies.get(j));
                        spaceship.setScore(spaceship.getScore() + 100 * level );
                    }
                    break;
                }
            }
        }
    }


    @Override
    public void checkShotsHitSpaceship() {
        for (Integer i=0;i<enemies.size();i++) {
            for (Integer j = 0; j < enemies.get(i).getShots().size();j++) {
                Integer shotWidthOffset = enemies.get(i).getShots().get(j).getWidth() / 2;
                Integer shotLeft = enemies.get(i).getShots().get(j).getPosition().getX() - shotWidthOffset;
                Integer shotRight = enemies.get(i).getShots().get(j).getPosition().getX() + shotWidthOffset;
                if (enemies.get(i).getShots().get(j).getPosition().getY().equals(spaceship.getPosition().getY()) &&
                        shotLeft <= spaceship.getPosition().getX() &&
                        shotRight >= spaceship.getPosition().getX()) {


                    if (!Objects.equals(spaceship.state, "invincible"))
                        spaceship.setHealth(spaceship.getHealth() - enemies.get(i).getShots().get(j).getDamage());

                    enemies.get(i).removeShot(enemies.get(i).getShots().get(j));
                    j--;
                }
            }
        }
    }

    @Override
    public void removeShotsOutOfBounds() {
        for (Integer i=0;i<spaceship.getShots().size();i++) {
            if (spaceship.getShots().get(i).getPosition().getY() < 0) {
                spaceship.removeShot(spaceship.getShots().get(i));
                i--;
            }
        }
        for (Integer i=0;i<enemies.size();i++) {
            for (Integer j=0;j<enemies.get(i).getShots().size();j++) {
                if (enemies.get(i).getShots().get(j).getPosition().getY() > getHeight()) {
                    enemies.get(i).removeShot(enemies.get(i).getShots().get(j));
                    j--;
                }
            }
        }
    }

    @Override
    public void moveEnemies() {
        if ((enemies.get(enemies.size() - 1).getPosition().getX() == width - 1 || !movingRight) && !(enemies.get(0).getPosition().getX() == 0)) {
            movingRight = false;
            moveEnemiesLeft();
        } else {moveEnemiesRight(); movingRight = true;}

    }

    @Override
    public void moveEnemiesLeft(){
        for (Enemy enemy: enemies){
            enemy.move(false);
        }
    }

    @Override
    public void moveEnemiesRight(){
        for (Enemy enemy: enemies){
            enemy.move(true);
        }
    }



    @Override
    public void moveShots(){

        for(Enemy enemy:enemies){
            for(Shot shot:enemy.getShots()){
                shot.moveDown();
            }
        }
        checkShotCollisions();
        checkShotsHitSpaceship();

        for(Shot shot:spaceship.getShots()){
            shot.moveUp();
        }
        checkShotCollisions();
        checkShotsHitEnemies();

        removeShotsOutOfBounds();
    }


    @Override
    public void createSpell(){
        if (Math.random() > 0.99)
            addSpell();
    }
    @Override
    public void shootEnemies(){
        for(int i=0; i<enemies.size();i++){
            if (Math.random() > 0.995){
                enemies.get(i).shoot();
            }
        }
    }

}

