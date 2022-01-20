package com.menu;

import com.game.Game;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;

import java.awt.*;
import java.io.IOException;

import static java.lang.System.exit;

public class Menu implements MenuDefiner {
    Screen screen;
    Terminal terminal;
    public boolean play,ended,play_red;
    public Menu() throws IOException {
        play = true;
        play_red = true;
        ended = false;
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(19,2,"SPACE INVADERS");
        if(play_red)
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        else
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(24,6,"PLAY");
        if(!play_red)
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        else
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(24,9,"QUIT");
    }

    @Override
    public void ScreenAndTerminalGenerator(){
        try{
            AWTTerminalFontConfiguration cfg = new SwingTerminalFontConfiguration(true,
                    AWTTerminalFontConfiguration.BoldMode.NOTHING, new Font(Font.MONOSPACED, Font.PLAIN, 40));
            terminal = new DefaultTerminalFactory()
                    .setForceAWTOverSwing(true)
                    .setInitialTerminalSize(new TerminalSize(50,15))
                    .setTerminalEmulatorFontConfiguration(cfg)
                    .createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void interactions() throws IOException {
        String score= "0";
        ScreenAndTerminalGenerator();
        while (true) {
            TextGraphics graphics = screen.newTextGraphics();
            this.screen.clear();
            if(play) this.draw(graphics);
            if(ended)this.drawGameOver(graphics,score);
            this.screen.refresh();
            KeyStroke key = screen.readInput();
            if (key != null) {
                switch (key.getKeyType()) {
                    case Enter:
                        if (!ended && play_red){
                            screen.close();
                            play = true;
                            ended = false;
                            Game g = Game.getInstance();
                            play = true;
                            g.run();
                            play = false;
                            ended = true;
                            score = g.arena.getSpaceship().getScore().toString();
                            ScreenAndTerminalGenerator();
                        }
                        else{
                            screen.close();
                            exit(0);
                        }
                        break;
                    case EOF:
                        screen.close();
                        exit(0);
                        break;
                    case Character :
                        if(key.getCharacter() == 'q'){
                                screen.close();
                                exit(0);
                        }
                        break;
                    case ArrowUp:
                        play_red = true;
                    break;
                    case ArrowDown:
                        play_red = false;
                    break;
                    default:break;
                }
            }
        }
    }

    @Override
    public void drawGameOver(TextGraphics graphics, String score) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(20,2,"GAME OVER");
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(16,6,"Your Score was: " + score);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.putString(23,9,"QUIT");
    }

}
