package com.spell.template;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;

import java.io.IOException;

public class SpellGunDamage extends SpellTemplate {
    public SpellGunDamage(Position pos){
        this.pos = pos;
        this.symbol = 'D';
        this.time = System.currentTimeMillis();
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F50575"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "P");
    }

}
