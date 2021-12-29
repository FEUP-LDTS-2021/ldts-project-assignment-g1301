import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public class SpellGunDamage extends SpellTemplate {
    SpellGunDamage(Position pos){
        this.pos = pos;
        this.symbol = 'D';
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#C0C0C0"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "D");
    }

}
