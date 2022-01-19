package com

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
}