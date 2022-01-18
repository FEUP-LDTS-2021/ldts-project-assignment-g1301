package com.shot;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.position.Position;

import java.io.IOException;

public interface ShotDefiner {
    Integer getDamage();
    Integer getWidth();
    Position getPosition();
    void moveDown();
    void moveUp();
    void draw(TextGraphics graphics) throws IOException;
}
