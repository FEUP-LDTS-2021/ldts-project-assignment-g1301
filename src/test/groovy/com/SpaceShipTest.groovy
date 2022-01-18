package com

import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
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

    def "spaceship_draw_test"() {
        given:
            def graphics = Mock(TextGraphics)
            SpaceShipDefiner s = new Spaceship(10, 1, pos)
        when:
            s.draw(graphics);
        then:
            1*graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            1*graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "_");
    }

    def "get_score_test"(){
        given:
            SpaceShipDefiner s
        when:
            s = new Spaceship(10, 1,  pos)
        then:
            assert s.getScore() == 0
    }

    def "set_score_test"(){
        given:
            SpaceShipDefiner s
        when:
            s = new Spaceship(10, 1,  pos)
            s.setScore(100)
        then:
            assert s.getScore() == 100
    }

    def "become_normal_test"(){
        given:
            SpaceShipDefiner s
        when:
            s = new Spaceship(10, 1,  pos)
            s.becomeNormal()
        then:
            assert s.state == "normal"
    }
}
