import groovyjarjarantlr4.v4.runtime.tree.ErrorNode
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
            e = new Enemy(10,pos,1,1)
        then:
            assert !e.isDead()
    }

    def "is_dead_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setDead()
        then:
            assert e.isDead()
    }


    def "move_left_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.moveLeft()
        then:
            assert e.getPosition().getX() == 1
    }

    def "move_right_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.moveRight()
        then:
            assert e.getPosition().getX() == 3
    }

    def "blue_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setVelocity(3)
        then:
            assert e.getColor() == "#00FFFF"
    }

    def "yellow_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setVelocity(2)
        then:
            assert e.getColor() == "#FFFF00"
    }

    def "white_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
        then:
            assert e.getColor() == "#FFFFFF"
    }

    def "shot_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            Integer x = e.getShots().size()
            e.shoot();
        then:
            assert e.getShots().size() == x+ 1
    }
}
