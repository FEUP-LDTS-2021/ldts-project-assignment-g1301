public class ZigZagMovementStrategy implements MovementStrategy{
    @Override
    public void move(Enemy enemy) {
        if (enemy.isMovingDown()){
             enemy.moveUp();
             enemy.setMoveDown(false);
        } else {
            enemy.moveDown();
            enemy.setMoveDown(true);
        }
        enemy.moveRight();
    }
}
