import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.List;

public interface ArenaDefiner {

    void setLevel(Integer level);

    List<Enemy> createEnemies();

    void addEnemy(Enemy e);

    void addSpell();

    void setSpaceship(Spaceship s);

    Integer getLevel();

    List<Enemy> getEnemies();
    List<SpellTemplate> getSpells();
    Spaceship getSpaceship();
    Integer getWidth();
    Integer getHeight();
    void draw(TextGraphics graphics) throws IOException;
    void checkShotCollisions();

    boolean checkShotsHitSpaceship();

    void processKey(KeyStroke key);

    void checkShotsHitEnemies();

    void removeShotsOutOfBounds();

    void moveEnemies();

    boolean moveShots();

    void shootEnemies();

    void checkActiveSpells();
}
