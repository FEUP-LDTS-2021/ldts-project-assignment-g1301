package spell.template;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import position.Position;
import spell.template.SpellTemplate;

import java.io.IOException;

public class SpellNerfed extends SpellTemplate {
    public SpellNerfed(Position pos) {
        this.pos = pos;
        this.symbol = 'N';
        this.time = System.currentTimeMillis();
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFE0"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "N");
    }
}
