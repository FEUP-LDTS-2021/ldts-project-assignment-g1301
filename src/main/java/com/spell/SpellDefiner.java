package com.spell;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;

import java.io.IOException;

public interface SpellDefiner {
    void draw(TextGraphics graphics) throws IOException;
    Position getPosition();
    Character getSymbol();
    long getTime();
}
