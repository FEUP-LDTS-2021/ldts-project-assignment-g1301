package com.game;

import com.arena.Arena;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static java.lang.System.exit;

public class Game implements GameDefiner {
    Screen screen;
    private Arena arena;
    private static Game game;
    private Game(){
        arena = new Arena(150,50);
    }

    public static Game getInstance(){
        if(game==null)
            game = new Game();
        return game;
    }

    @Override
    public void draw() throws IOException {
        this.screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        arena.draw(graphics);
        this.screen.refresh();
    }


    @Override
    public void ScreenAndTerminalGenerator(){
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() throws IOException {
        ScreenAndTerminalGenerator();
        InputThread myThread = new InputThread();
        myThread.screen = this.screen;
        myThread.start();
        while (true) {
            if (arena.getEnemies().size() == 0)
                arena.createEnemies();
            arena.moveEnemies();
            draw();
            if (arena.getSpaceship().isDead()) {
                screen.close();
                System.out.println("game.Game Over");
                exit(0);
                break;
            }
            KeyStroke key = myThread.key;
            try {
                myThread.sleep(15);
            } catch (InterruptedException f) {
                f.printStackTrace();
            }
            if (key != null) {
                if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')||(key.getKeyType() == KeyType.EOF)) {
                    screen.close();
                    exit(0);
                }
                arena.processKey(key);
                myThread.key = null;
            }
            arena.moveShots();
            arena.shootEnemies();
            arena.checkShotCollisions();
            arena.checkShotsHitEnemies();
            arena.checkShotsHitSpaceship();
            arena.checkActiveSpells();
            arena.createSpell();
            arena.checkCaughtSpell();
            arena.updateSpaceShipState();
            try {
                myThread.sleep(15);
            } catch (InterruptedException f) {
                f.printStackTrace();
            }
        }
    }
}