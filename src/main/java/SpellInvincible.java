import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class SpellInvincible extends SpellTemplate{
    SpellInvincible(Position pos){
        this.pos = pos;
        this.symbol = 'I';
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#4682B4"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "I");
    }
}
