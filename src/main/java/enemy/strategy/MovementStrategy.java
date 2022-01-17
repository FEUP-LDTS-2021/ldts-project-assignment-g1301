package enemy.strategy;

import enemy.Enemy;

public interface MovementStrategy {
    void move(Enemy enemy, boolean right);
}
