import position.Position
import spaceship.Spaceship
import spaceship.observer.SpaceshipObserver
import spock.lang.Specification

class SpaceshipObserverTest extends Specification{
    def "addSpaceshipObserver_no_action"(){
        given:
            Spaceship spaceship = new Spaceship(1,1,new Position(10,10));
        SpaceshipObserver observer = Mock(SpaceshipObserver);
        when:
            spaceship.addObserver(observer);
        then:
            0*observer.caughtTPback(spaceship)
            0*observer.usedTPback(spaceship)
    }
    def "addSpaceshipObserver_caught_tp_back"(){
        given:
        Spaceship spaceship = new Spaceship(1,1,new Position(10,10));
            SpaceshipObserver observer = Mock(SpaceshipObserver);
        when:
            spaceship.addObserver(observer);
            spaceship.caughtTPBack(null);
            spaceship.caughtTPBack(null)
        then:
            2*observer.caughtTPback(spaceship)
            0*observer.usedTPback(spaceship)
    }
    def "addSpaceshipObserver_used_tp_back"(){
        given:
            Spaceship spaceship = new Spaceship(1,1,new Position(10,10));
            SpaceshipObserver observer = Mock(SpaceshipObserver);
        when:
            spaceship.addObserver(observer);
            spaceship.usedTPBack();
        then:
            0*observer.caughtTPback(spaceship)
            1*observer.usedTPback(spaceship)
    }

}
