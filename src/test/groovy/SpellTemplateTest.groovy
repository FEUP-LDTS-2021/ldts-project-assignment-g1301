import spock.lang.Specification

class SpellTemplateTest extends Specification{
    private Position pos;

    def "setup"(){
        pos = Mock(Position);
    }

    def "SpellHealth Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellHealth(pos);
        then:
            assert s.getSymbol()=='H';
    }

    def "SpellGunDamage Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellGunDamage(pos);
        then:
            assert s.getSymbol()=='D';
    }

    def "SpellHealthDamage Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellHealthDamage(pos);
        then:
            assert s.getSymbol()=='X';
    }

    def "SpellLessGunDamage Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellLessGunDamage(pos);
        then:
            assert s.getSymbol()=='L';
    }

    def "SpellTPBack Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellTPBack(pos);
        then:
            assert s.getSymbol()=='T';
    }

    def "SpellInvincible Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellInvincible(pos);
        then:
            assert s.getSymbol()=='I';
    }


    def "SpellNerfed Test"(){
        given:
            SpellTemplate s;
        when:
            s = new SpellNerfed(pos);
        then:
            assert s.getSymbol()=='N';
    }
}
