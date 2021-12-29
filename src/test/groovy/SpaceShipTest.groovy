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
        s = new Spaceship(10, 1, 1, pos)
        then:
        assert !s.isDead()
    }

    def "is_dead_test"() {
        given:
        SpaceShipDefiner s
        when:
        s = new Spaceship(0, 1, 1, pos)
        then:
        assert s.isDead()
    }

    def "move_left_test"() {
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1, 1, pos)
        when:
        s.moveLeft()
        then:
        assert s.getPosition().getX() == 1
    }

    def "move_right_test"() {
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1, 1, pos)
        when:
        s.moveRight()
        then:
        assert s.getPosition().getX() == 3
    }
    def "shoot"(){
        given:
        SpaceShipDefiner s
        s = new Spaceship(10, 1, 1, pos)
        when:
        Integer x = s.getShots().size()
        s.shoot()
        then:
        assert s.getShots().size() == x+1
    }
}
