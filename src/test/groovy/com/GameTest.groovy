package com

import com.arena.Arena
import com.game.Game
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.menu.Menu
import com.menu.MenuDefiner
import spock.lang.Specification

class GameTest extends Specification{
    def "singleton_test"(){
        when:
            Game g1 = Game.getInstance()
            Game g2 = Game.getInstance()
        then:
            g1==g2
    }

    def "game_test"(){
        given:
            def graphics = Mock(TextGraphics)
            Game g = Game.getInstance()
            Arena a = Mock(Arena)
            g.arena = a
        when:
            g.draw(graphics)
        then:
            1*a.draw(graphics)
    }

    def "loadOverkillFont"(){
        given:
            Game g = Game.getInstance()
        when:
            def x = g.loadOverkillFont()
        then:
            assert x!=null
    }
}