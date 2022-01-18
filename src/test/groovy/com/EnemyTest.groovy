package com

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.enemy.strategy.BigShotStrategy
import com.enemy.strategy.DamageShotStrategy
import com.enemy.Enemy
import com.enemy.EnemyDefiner
import com.enemy.strategy.NormalShotStrategy
import com.enemy.strategy.HorizontalMovementStrategy
import com.enemy.strategy.ZigZagMovementStrategy
import com.position.Position
import spock.lang.Specification

class EnemyTest extends Specification{
    private Position pos
    def "setup"(){
        pos = new Position(2,2)
    }

    def "is_alive_test"(){
        given:
        EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
        then:
            assert !e.isDead()
    }

    def "is_dead_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(0,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
        then:
            assert e.isDead()
    }


    def "move_horizontal_right_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
            e.move(true)
        then:
            assert e.getPosition().getX() == 3
    }


    def "move_zig_zag_test_up"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new ZigZagMovementStrategy(), new NormalShotStrategy());
            e.move(true)
        then:
            assert e.getPosition().getY() == 3
    }

    def "move_zig_zag_test_down"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new ZigZagMovementStrategy(), new NormalShotStrategy());
            e.move(true)
            e.move(true)
        then:
            assert e.getPosition().getY() == 2
    }

    def "normal_shot_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
            e.shoot()
        then:
            assert e.getShots().size() == 1

    }

    def "damage_shot_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(), new DamageShotStrategy())
            e.shoot()
        then:
            assert e.getShots()[0].damage == 300
    }

    def "big_shot_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(), new BigShotStrategy())
            e.shoot()
        then:
            assert e.getShots()[0].width == 3
    }

    def "enemy_draw"(){
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
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(), new DamageShotStrategy())
            e.draw(graphics);
        then:
            assert graphics.getCharacter(pos.x, pos.y).getCharacter()== ('R' as char);
    }


    def "set_position_test"(){
        given:
        EnemyDefiner e
        when:
        e = new Enemy(10, pos, new HorizontalMovementStrategy(), new DamageShotStrategy())
        e.setPosition(new Position(3,3))
        then:
        assert e.getPosition().getY() == 3 && e.getPosition().getX() == 3
    }

    def "set_move_right_test"(){
        given:
        EnemyDefiner e
        when:
        e = new Enemy(10, pos, new HorizontalMovementStrategy(), new DamageShotStrategy())
        e.setMoveRight(false)
        then:
        assert e.movingRight == false
    }
}
