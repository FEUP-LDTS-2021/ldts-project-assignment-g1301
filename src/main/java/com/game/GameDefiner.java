package com.game;

import java.io.IOException;

public interface GameDefiner {
    void draw() throws IOException;

    void ScreenAndTerminalGenerator();

    void run() throws IOException;
}

