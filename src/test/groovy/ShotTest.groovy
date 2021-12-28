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
            s = new Shot(1,2,3,pos);
        then:
            s.getDamage() == 1
    }
    def "Radius getter test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos);
        then:
            s.getWidth() == 2
    }
    def "Velocity getter Test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos);
        then:
            s.getVelocity() == 3
    }
    def "Position getter Test"(){
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,2,3,pos)
        then:
            s.getPosition().getX()==1
            s.getPosition().getY()==2
    }
    def "Move down Test"() {
        given:
            ShotDefiner s;
        when:
            s = new Shot(1, 2, 3, new Position(1, 2))
            Integer oldY = s.getPosition().getY()
            s.moveDown()
        then:
            s.getPosition().getY() == oldY + 1
    }


    def "Get color Test White"() {
        given:
            ShotDefiner s;
        when:
            s = new Shot(1,3,1,pos);
        then:
            s.getColor() == "FFFFFF";
    }

    def "Get color Test Yellow"() {
        given:
            ShotDefiner s;
        when:
           s = new Shot(1,3,2,pos);
        then:
            s.getColor() == "FFFF00";
    }

    def "Get color Test Blue"() {
        given:
           ShotDefiner s;
        when:
           s = new Shot(1,3,3,pos);
        then:
           s.getColor() == "00FFFF";
    }

}
