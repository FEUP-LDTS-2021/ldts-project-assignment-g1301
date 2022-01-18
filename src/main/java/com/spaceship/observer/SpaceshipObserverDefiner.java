package com.spaceship.observer;

import com.spaceship.Spaceship;

public interface SpaceshipObserverDefiner {
    void caughtTPback(Spaceship spaceship);
    void usedTPback(Spaceship spaceship);
}
