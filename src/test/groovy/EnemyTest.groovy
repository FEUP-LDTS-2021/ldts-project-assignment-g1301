import groovyjarjarantlr4.v4.runtime.tree.ErrorNode
import spock.lang.Specification

class EnemyDefinerTest extends Specification{
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
            !e.isDead()
    }

    def "is_dead_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setDead()
        then:
            e.isDead()
    }


    def "move_left_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.moveLeft()
        then:
            e.getPosition().getX() == 1
    }

    def "move_right_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.moveRight()
        then:
            e.getPosition().getX() == 3
    }

    def "blue_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setVelocity(3)
        then:
            e.getColor() == "#00FFFF"
    }

    def "yellow_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
            e.setVelocity(2)
        then:
            e.getColor() == "#FFFF00"
    }

    def "white_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,1,1)
        then:
            e.getColor() == "#FFFFFF"
    }
}
