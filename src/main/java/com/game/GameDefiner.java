package com.game;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface GameDefiner {

    void draw(TextGraphics graphics) throws IOException;

    void ScreenAndTerminalGenerator();

    void run() throws IOException;
}

