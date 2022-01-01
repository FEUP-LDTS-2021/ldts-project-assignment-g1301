import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface SpellDefiner {
    void draw(TextGraphics graphics) throws IOException;
    Position getPosition();
    Character getSymbol();
    long getTime();
}
