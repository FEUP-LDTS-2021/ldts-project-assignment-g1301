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
            e = new Enemy(10,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
            e.setDead()
        then:
            assert e.isDead()
    }


    def "move_horizontal_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new HorizontalMovementStrategy(),new NormalShotStrategy())
            e.move()
        then:
            assert e.getPosition().getX() == 3
    }


    def "move_zig_zag_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,pos,new ZigZagMovementStrategy(), new NormalShotStrategy());
            e.move();
        then:
            assert e.getPosition().getY() == 3
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
            assert e.getShots()[0].damage == 2
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
}
