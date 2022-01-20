package com.menu;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface MenuDefiner {
    void draw(TextGraphics graphics) throws IOException;

    void ScreenAndTerminalGenerator();

    void interactions() throws IOException;

    void drawGameOver(TextGraphics graphics, String score) throws IOException;
}
