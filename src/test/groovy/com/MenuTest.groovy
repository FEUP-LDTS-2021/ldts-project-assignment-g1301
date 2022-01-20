package com

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration
import com.menu.Menu
import com.menu.MenuDefiner
import spock.lang.Specification

import java.awt.Font

class MenuTest extends Specification{
    def "start_menu_draw_play_red"() {
        given:
            def graphics = Mock(TextGraphics);
            MenuDefiner m;
        when:
            m = new Menu();
            m.draw(graphics);
        then:
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"))
            1*graphics.putString(19,2,"SPACE INVADERS")
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"))
            1*graphics.putString(24,6,"PLAY")
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"))
            1*graphics.putString(24,9,"QUIT")
    }
    def "start_menu_draw_exit_red"() {
        given:
            def graphics = Mock(TextGraphics);
            MenuDefiner m;
        when:
            m = new Menu();
            m.play_red = false
            m.draw(graphics);
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"))
            1 * graphics.putString(19,2,"SPACE INVADERS")
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"))
            1 * graphics.putString(24,6,"PLAY")
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"))
            1 * graphics.putString(24,9,"QUIT")
    }

    def "end_menu_draw"(){
        given:
            def graphics = Mock(TextGraphics);
            MenuDefiner m;
            String s;
        when:
            m = new Menu();
            m.drawGameOver(graphics,s);
        then:
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
            1*graphics.putString(20,2,"GAME OVER");
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            1*graphics.putString(16,6,"Your Score was: " + s);
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            1*graphics.putString(23,9,"QUIT");
    }

}
