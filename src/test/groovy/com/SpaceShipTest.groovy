package com

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.position.Position
import com.spaceship.SpaceShipDefiner
import com.spaceship.Spaceship
import spock.lang.Specification

class SpaceShipTest extends Specification {
    private Position pos

    def "setup"() {
        pos = new Position(2, 2)
    }

    def "is_alive_test"() {
        given:
        SpaceShipDefiner s
        when:
        s = new Spaceship(10, 1,  pos)
        then:
        assert !s.isDead()
    }

    def "is_dead_test"() {
        given:
        SpaceShipDefiner s
        when:
        s = new Spaceship(0, 1, pos)
        then:
        assert s.isDead()
    }

    def "move_left_test"() {
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1,pos)
        when:
        s.moveLeft()
        then:
        assert s.getPosition().getX() == 1
    }

    def "move_right_test"() {
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1, pos)
        when:
        s.moveRight()
        then:
        assert s.getPosition().getX() == 3
    }
    def "shoot"(){
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1, pos)
        when:
        Integer x = s.getShots().size()
        s.shoot()
        then:
        assert s.getShots().size() == x+1
    }
    /*
    def "spaceship_draw_test"() {
        given:
            Screen screen;
            TerminalSize terminalSize = new TerminalSize(150, 50);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();
            def graphics = screen.newTextGraphics();
            SpaceShipDefiner s
        when:
            s = new Spaceship(10, 1, pos)
            s.draw(graphics);
        then:
            assert graphics.getCharacter(pos.x, pos.y).getCharacter() == ('_' as char);
    }
     */
}
