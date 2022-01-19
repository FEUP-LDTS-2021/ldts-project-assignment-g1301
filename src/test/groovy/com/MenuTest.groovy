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
    def "menu_draw_play_red"() {
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
    def "menu_draw_exit_red"() {
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
}
