public class HorizontalMovementStrategy implements MovementStrategy{
    @Override
    public void move(Enemy enemy) {
        enemy.moveRight();
    }
}
