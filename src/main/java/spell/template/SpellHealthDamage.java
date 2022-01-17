package spell.template;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import position.Position;
import spell.template.SpellTemplate;

import java.io.IOException;

public class SpellHealthDamage extends SpellTemplate {
    public SpellHealthDamage(Position pos){
        this.pos = pos;
        this.symbol = 'X';
        this.time = System.currentTimeMillis();
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "X");
    }

}
