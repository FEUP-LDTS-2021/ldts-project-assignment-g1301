public class SpaceshipObserver implements SpaceshipObserverDefiner {
    private Position pos;
    boolean can_use_tp;
    SpaceshipObserver(){
        pos = null;
        can_use_tp = false;
    }
    @Override
    public void caughtTPback(Spaceship spaceship) {
        pos = new Position(spaceship.getPosition().getX(),spaceship.getPosition().getY());
        can_use_tp=true;
    }

    @Override
    public void usedTPback(Spaceship spaceship) {
        if(can_use_tp) {
            spaceship.setPosition(pos);
            can_use_tp = false;
        }
    }
}
