package spell;

import com.googlecode.lanterna.graphics.TextGraphics;
import position.Position;

import java.io.IOException;

public interface SpellDefiner {
    void draw(TextGraphics graphics) throws IOException;
    Position getPosition();
    Character getSymbol();
    long getTime();
}
