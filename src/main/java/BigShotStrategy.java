public class BigShotStrategy implements ShootingStrategy{
    @Override
    public void shoot(Enemy enemy) {
        enemy.bigShot();
    }
}
