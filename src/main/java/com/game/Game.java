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
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.System.exit;

public class Game implements GameDefiner {
    Screen screen;
    public Arena arena;
    private static Game game;
    private Game(){
        arena = new Arena(150,40);
    }

    public static Game getInstance(){
        if(game==null)
            game = new Game();
        return game;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        arena.draw(graphics);
    }


    @Override
    public void ScreenAndTerminalGenerator(){
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            terminalFactory.setForceAWTOverSwing(true);
            terminalFactory.setTerminalEmulatorFontConfiguration(loadOverkillFont());
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
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
            this.screen.clear();
            TextGraphics graphics = screen.newTextGraphics();
            draw(graphics);
            this.screen.refresh();
            if (arena.getSpaceship().isDead()) {
                screen.close();
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
            arena.shootEnemies(new Random());
            arena.checkShotCollisions();
            arena.checkShotsHitEnemies();
            arena.checkShotsHitSpaceship();
            arena.checkActiveSpells();
            arena.createSpell(new Random());
            arena.checkCaughtSpell();
            arena.updateSpaceShipState();
            try {
                myThread.sleep(15);
            } catch (InterruptedException f) {
                f.printStackTrace();
            }
        }
    }
    public AWTTerminalFontConfiguration loadOverkillFont() throws FontFormatException, IOException {
        File fontFile = new File("src/main/resources/SpaceInvadersFont.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 21);
        return AWTTerminalFontConfiguration.newInstance(loadedFont);
    }
}