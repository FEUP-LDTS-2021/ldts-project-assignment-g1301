public class ZigZagMovementStrategy implements MovementStrategy{
    @Override
    public void move(Enemy enemy) {
        if (enemy.isMovingDown()){
             enemy.moveUp();
        } else {
            enemy.moveDown();
        }
        enemy.moveRight();
    }
}
