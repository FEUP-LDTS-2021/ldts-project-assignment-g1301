package com.spell.template;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;

import java.io.IOException;

public class SpellInvincible extends SpellTemplate {
    public SpellInvincible(Position pos){
        this.pos = pos;
        this.symbol = 'I';
        this.time = System.currentTimeMillis();
    }
    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F9D71C"));
        graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "I");
    }
}
