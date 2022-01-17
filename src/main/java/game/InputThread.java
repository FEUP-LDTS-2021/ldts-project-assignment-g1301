package game;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class InputThread extends Thread {
    Screen screen;
    KeyStroke key;

    @Override
    public void run() {
        while(true){
            try {
                key = screen.readInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
