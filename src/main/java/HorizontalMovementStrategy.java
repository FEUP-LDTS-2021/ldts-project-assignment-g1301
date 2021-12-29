public class HorizontalMovementStrategy implements MovementStrategy{
    @Override
    public void move(Enemy enemy) {
        if (enemy.isMovingRight()){
            if (enemy.getPosition().getX() < 20){
                enemy.moveRight();
            } else {
                enemy.moveLeft();
                enemy.setMoveRight(false);
            }
        } else {
            if (enemy.getPosition().getX() > 0){
                enemy.moveLeft();
            } else {
                enemy.moveRight();
                enemy.setMoveRight(true);
            }
        }
    }
}
