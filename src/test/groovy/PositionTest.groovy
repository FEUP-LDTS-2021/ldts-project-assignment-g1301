import spock.lang.Specification

class PositionTest extends Specification{
    def "getter_test"(){
        given:
        PositionDefiner p
        when:
        p = new Position(1,2)
        then:
        assert p.getX() == 1
        assert p.getY() == 2
    }
    def "setter_test"(){
        given:
        PositionDefiner p
        when:
        p = new Position(0,0)
        p.setX(1)
        p.setY(2)
        then:
        assert p.getX() == 1
        assert p.getY() == 2
    }
}
