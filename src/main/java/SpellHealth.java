import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class SpellHealth extends SpellTemplate {
    SpellHealth(Position pos){
        this.pos = pos;
        this.symbol = 'H';
        this.time = System.currentTimeMillis();
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF7F"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "H");
    }

}
