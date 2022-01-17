package spaceship.observer;

import spaceship.Spaceship;

public interface SpaceshipObserverDefiner {
    void caughtTPback(Spaceship spaceship);
    void usedTPback(Spaceship spaceship);
}
