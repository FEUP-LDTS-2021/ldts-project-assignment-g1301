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
import com.shot.Shot
import com.shot.ShotDefiner
import spock.lang.Specification

class ShotTest extends Specification{
    private Position pos;
    def "setup"(){
        pos = Mock(Position)
        pos.getX() >> 1
        pos.getY() >> 2
    }
    def "Damage getter test"(){
        given:
        ShotDefiner s;
        when:
            s = new Shot(1,2,pos);
        then:
            assert s.getDamage() == 1
    }
    def "Radius getter test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,pos);
        then:
            assert s.getWidth() == 2
    }
    def "position.Position getter Test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,pos)
        then:
            assert s.getPosition().getX()==1
            assert s.getPosition().getY()==2
    }
    def "Move down Test"() {
        given:
            ShotDefiner s;
        when:
            s = new Shot(1, 2, new Position(1, 2))
            Integer oldY = s.getPosition().getY()
            s.moveDown()
        then:
            assert s.getPosition().getY() == oldY + 1
    }



    def "Normal shot draw"(){
        given:
            def graphics = Mock(TextGraphics)
            ShotDefiner s = new Shot(0,0,pos);
        when:
            s.draw(graphics);
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            1 * graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "|");
    }

    def "Big shot draw"(){
        given:
            def graphics = Mock(TextGraphics)
            ShotDefiner s = new Shot(0,3,pos);
        when:
            s.draw(graphics);
        then:
            2 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            1 * graphics.putString(new TerminalPosition(pos.getX()-1, pos.getY()), "|");
            1 * graphics.putString(new TerminalPosition(pos.getX(), pos.getY()), "|");
            1 * graphics.putString(new TerminalPosition(pos.getX()+1, pos.getY()), "|");
    }

}
