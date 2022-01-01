import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena implements ArenaDefiner {
    private Spaceship spaceship;
    private List<Enemy> enemies;
    private List<SpellTemplate> spells;
    private final Integer width;
    private final Integer height;
    private Integer level;


    @Override
    public void setLevel(Integer level){
        this.level = level;
    }

    @Override
    public List<Enemy> createEnemies(){
        for(Integer x=15;x<=135;x+=20){
            for (Integer y=2;y<=8;y+=2) {
                Integer health = 100 + (getLevel()/6)*100;
                switch (getLevel() % 6) {
                    case 0 -> addEnemy(new Enemy(health, new Position(x, y), new HorizontalMovementStrategy(), new NormalShotStrategy()));
                    case 1 -> addEnemy(new Enemy(health, new Position(x, y), new ZigZagMovementStrategy(), new NormalShotStrategy()));
                    case 2 -> addEnemy(new Enemy(health, new Position(x, y), new HorizontalMovementStrategy(), new DamageShotStrategy()));
                    case 3 -> addEnemy(new Enemy(health, new Position(x, y), new ZigZagMovementStrategy(), new DamageShotStrategy()));
                    case 4 -> addEnemy(new Enemy(health, new Position(x, y), new HorizontalMovementStrategy(), new BigShotStrategy()));
                    default -> addEnemy(new Enemy(health, new Position(x, y), new ZigZagMovementStrategy(), new BigShotStrategy()));
                }
            }
        }
        level++;
        return enemies;
    }

    @Override
    public void addEnemy(Enemy e) {
        enemies.add(e);
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
            int spellNr = random.nextInt(5);
            SpellTemplate spell = switch (spellNr) {
                case 0 -> new SpellHealth(pos);
                case 1 -> new SpellHealthDamage(pos);
                case 2 -> new SpellGunDamage(pos);
                case 3 -> new SpellLessGunDamage(pos);
                case 4 -> new SpellInvincible(pos);
                default -> new SpellTPBack(pos);
            };
            spells.add(spell);
        }
        else if (listX.size()==1){
            Position pos = new Position(listX.get(0),getHeight()-2);
            int spellNr = random.nextInt(5);
            SpellTemplate spell = switch (spellNr) {
                case 0 -> new SpellHealth(pos);
                case 1 -> new SpellHealthDamage(pos);
                case 2 -> new SpellGunDamage(pos);
                case 3 -> new SpellLessGunDamage(pos);
                case 4 -> new SpellInvincible(pos);
                default -> new SpellTPBack(pos);
            };
            spells.add(spell);
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

    Arena(Integer width,Integer height){
        this.width = width;
        this.height = height;
        this.level = 0;
        spaceship = new Spaceship(1000,1,100,new Position(width/2,height-2));
        enemies = new ArrayList<>();
        spells = new ArrayList<>();
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


    @Override
    public void checkCaughtSpell(){
        for(Integer i=0;i<spells.size();i++){
            if(getSpells().get(i).getPosition().getX().equals(spaceship.getPosition().getX())){
                switch(getSpells().get(i).getSymbol()){
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
                        if(spaceship.state.getClass()!=InvincibleSpaceShipState.class)
                            spaceship.setHealth(spaceship.getHealth()-200);
                        break;
                    case 'L':
                        spaceship.setDamage(spaceship.getDamage()-100);
                        break;
                    case 'N':
                        spaceship.becomeNerfed();
                        break;

                }
                getSpells().remove(getSpells().get(i));
            }
        }
    }


    @Override
    public void updateSpaceShipState(){
        if(System.currentTimeMillis()-spaceship.last_transition_instant>=10000 &&
        spaceship.state.getClass()!=NormalSpaceShipState.class){
            spaceship.becomeNormal();
        }
    }

    @Override
    public void checkShotCollisions() {
        for (Integer i = 0;i<enemies.size();i++) {
            for (Integer j =0; j < enemies.get(i).getShots().size();j++) {
                for (Integer k=0; k<spaceship.getShots().size();k++) {
                    if (enemies.get(i).getShots().get(j).getPosition().getY().equals(spaceship.getShots().get(k).getPosition().getY())) {
                        Integer shot1WidthOffset = enemies.get(i).getShots().get(j).getWidth() / 2;
                        Integer shot2WidthOffset = spaceship.getShots().get(k).getWidth() / 2;
                        Integer shot1left = enemies.get(i).getShots().get(j).getPosition().getX() - shot1WidthOffset;
                        Integer shot1right = enemies.get(i).getShots().get(j).getPosition().getX() + shot1WidthOffset;
                        Integer shot2left = spaceship.getShots().get(k).getPosition().getX() - shot2WidthOffset;
                        Integer shot2right = spaceship.getShots().get(k).getPosition().getX() + shot2WidthOffset;
                        if (!(shot1left < shot2left && shot1right < shot2right || shot1left > shot2left && shot1right > shot2right)) {
                            spaceship.removeShot(spaceship.getShots().get(k));
                            k--;
                            enemies.get(i).removeShot(enemies.get(i).getShots().get(j));
                            j--;
                        }
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
                    if (spaceship.getPosition().getX() < getWidth())
                        spaceship.getPosition().setX(spaceship.getPosition().getX() + 1);
                    break;
                case ' ':
                    if(spaceship.state.getClass()!=NerfedSpaceShipState.class)
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

                    if (enemies.get(j).isDead()) {
                        enemies.remove(enemies.get(j));
                    }
                }
            }
        }
    }


    @Override
    public boolean checkShotsHitSpaceship() {
        for (Integer i=0;i<enemies.size();i++) {
            for (Integer j = 0; j < enemies.get(i).getShots().size();j++) {
                Integer shotWidthOffset = enemies.get(i).getShots().get(j).getWidth() / 2;
                Integer shotLeft = enemies.get(i).getShots().get(j).getPosition().getX() - shotWidthOffset;
                Integer shotRight = enemies.get(i).getShots().get(j).getPosition().getX() + shotWidthOffset;
                if (enemies.get(i).getShots().get(j).getPosition().getY().equals(spaceship.getPosition().getY()) &&
                        shotLeft <= spaceship.getPosition().getX() &&
                        shotRight >= spaceship.getPosition().getX()) {


                    if(spaceship.state.getClass()!= InvincibleSpaceShipState.class)
                        spaceship.setHealth(spaceship.getHealth() - enemies.get(i).getShots().get(j).getDamage());

                    enemies.get(i).removeShot(enemies.get(i).getShots().get(j));
                    j--;

                    if (spaceship.isDead())
                        return false;
                }
            }
        }
        return true;
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
        for (Enemy enemy: enemies){
            enemy.move();
            if (enemy.getPosition().getX() > width){
                enemy.getPosition().setX(0);
            }
        }
    }



    @Override
    public boolean moveShots(){

        for(Enemy enemy:enemies){
            for(Shot shot:enemy.getShots()){
                shot.moveDown();
            }
        }
        checkShotCollisions();
        if(!checkShotsHitSpaceship())
            return false;

        for(Shot shot:spaceship.getShots()){
            shot.moveUp();
        }
        checkShotCollisions();
        checkShotsHitEnemies();

        removeShotsOutOfBounds();
        return true;
    }

    @Override
    public void shootEnemies(){
        for(int i=0; i<enemies.size();i++){
            if (Math.random() > 0.6){
                enemies.get(i).shoot();
            }
        }
    }
}

