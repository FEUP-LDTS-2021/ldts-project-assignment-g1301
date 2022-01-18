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
import com.spell.template.SpellGunDamage
import com.spell.template.SpellHealth
import com.spell.template.SpellHealthDamage
import com.spell.template.SpellInvincible
import com.spell.template.SpellLessGunDamage
import com.spell.template.SpellNerfed
import com.spell.template.SpellTPBack
import com.spell.template.SpellTemplate
import spock.lang.Specification

class SpellTemplateTest extends Specification{
    private Position mockPos
    private Position realPos

    def "setup"(){
        mockPos = Mock(Position)
        realPos = new Position(1,2)

    }

    def "SpellHealth Test"(){
        given:
        SpellTemplate s
        when:
            s = new SpellHealth(mockPos)
        then:
            assert s.getSymbol()=='H'
    }

    def "SpellGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellGunDamage(mockPos)
        then:
            assert s.getSymbol()=='D'
    }

    def "SpellHealthDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellHealthDamage(mockPos)
        then:
            assert s.getSymbol()=='X'
    }

    def "SpellLessGunDamage Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellLessGunDamage(mockPos)
        then:
            assert s.getSymbol()=='L'
    }

    def "SpellTPBack Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellTPBack(mockPos)
        then:
            assert s.getSymbol()=='T'
    }

    def "SpellInvincible Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellInvincible(mockPos)
        then:
            assert s.getSymbol()=='I'
    }


    def "SpellNerfed Test"(){
        given:
            SpellTemplate s
        when:
            s = new SpellNerfed(mockPos)
        then:
            assert s.getSymbol()=='N'
    }

    def "SpellHealth Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellHealth(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#00FF7F"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "H");
    }

    def "SpellGunDamage Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellGunDamage(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#C0C0C0"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "D");
    }

    def "SpellHealthDamage Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellHealthDamage(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "X");
    }

    def "SpellLessGunDamage Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellLessGunDamage(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "L");
    }

    def "SpellTPBack Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellTPBack(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "T");
    }

    def "SpellInvincible Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellInvincible(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#4682B4"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "I");
    }


    def "SpellNerfed Draw Test"(){
        given:
            def graphics = Mock(TextGraphics)
            SpellTemplate s
        when:
            s = new SpellNerfed(realPos)
            s.draw(graphics)
        then:
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFE0"));
            1 * graphics.putString(new TerminalPosition(realPos.getX(), realPos.getY()), "N");
    }

}
