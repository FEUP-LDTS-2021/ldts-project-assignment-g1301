import spock.lang.Specification
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.input.KeyStroke



class ArenaTest extends Specification{
    private Arena arena
    def "setup"(){
        arena = new Arena(10,10)
    }
    def "check_normal_shot_collision_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10, new Position(2,1),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(10,1,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 0
    }

    def "check_big_shot_collision_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,1),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(10,1,1,new Position(3,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 0
    }

    def "process_key_type_test"(){
        given:
            KeyStroke key1 = Mock(KeyStroke)
            KeyStroke key2 = Mock(KeyStroke)
            KeyStroke key3 = Mock(KeyStroke)
            key1.getCharacter() >> 'a'
            key1.getKeyType() >> KeyType.Character
            key2.getCharacter() >> 'd'
            key2.getKeyType() >> KeyType.Character
            key3.getCharacter() >> ' '
            key3.getKeyType() >> KeyType.Character
            SpaceShipDefiner spaceship1,spaceship2,spaceship3
            Arena arena1,arena2,arena3
             Integer x
        when:
            arena1 = new Arena(10,10)
            arena2 = new Arena(10,10)
            arena3 = new Arena(10,10)
            spaceship1 = new Spaceship(10,1,1,new Position(2,2))
            spaceship2 = new Spaceship(10,1,1,new Position(2,2))
            spaceship3 = new Spaceship(10,1,1,new Position(2,2))
            x= spaceship3.getShots().size()
            arena1.setSpaceship(spaceship1)
            arena2.setSpaceship(spaceship2)
            arena3.setSpaceship(spaceship3)
            arena1.processKey(key1)
            arena2.processKey(key2)
            arena3.processKey(key3)
        then:
            assert spaceship1.getPosition().getX() == 1
            assert spaceship2.getPosition().getX() == 3
            assert spaceship3.getShots().size() == x+1
    }

    def "check_shot_hits_enemies_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(10,1,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            s.shoot()
            s.getShots()[0].moveUp()
            s.getShots()[0].moveUp()
            arena.checkShotsHitEnemies()
        then:
            assert e.getHealth() == 9
    }

    def "check_shot_hits_spaceships_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(110,1,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            e.getShots()[0].moveDown()
            e.getShots()[0].moveDown()
            arena.checkShotsHitSpaceship()
        then:
            assert  s.getHealth() == 10
    }

    def "move_enemies_frontier_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,new Position(10,4), new HorizontalMovementStrategy(), new NormalShotStrategy())
            arena.addEnemy(e)
            arena.moveEnemies()
        then:
            assert e.getPosition().getX() == 0
    }

    def "move_enemies_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,new Position(2,4), new HorizontalMovementStrategy(), new NormalShotStrategy())
            arena.addEnemy(e)
            arena.moveEnemies()
        then:
            assert e.getPosition().getX() == 3
    }

    def "move_shots_test"(){
        given:
            SpaceShipDefiner s
        when:
            s = new Spaceship(10,1,1,new Position(2,2))
            arena.setSpaceship(s)
            s.shoot()
            arena.moveShots()
        then:
            assert s.getShots()[0].getPosition().getY() == 0
    }

    def "remove_shots_out_of_bounds_test"(){
        given:
            SpaceShipDefiner s
            EnemyDefiner e
        when:
            s = new Spaceship(10,1,1,new Position(2,0))
            e = new Enemy(10,new Position(3,10),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s.shoot()
            e.shoot()
            arena.setSpaceship(s)
            arena.addEnemy(e)
            arena.removeShotsOutOfBounds()
        then:
            assert s.getShots().isEmpty()
            assert e.getShots().isEmpty()
    }

    def "add_enemy_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,new Position(1,2), new HorizontalMovementStrategy(), new NormalShotStrategy())
            arena.addEnemy(e)
        then:
            assert arena.getEnemies().size() == 1
    }

    def "create_enemy_position"(){
        given:
        EnemyDefiner e
        when:
        e = new Enemy(10,new Position(1,2), new HorizontalMovementStrategy(), new NormalShotStrategy())
        arena.addEnemy(e)
        then:
        assert arena.getEnemies().get(0).getPosition().getY() == 2
        assert arena.getEnemies().get(0).getPosition().getX() == 1
    }


    def "create_enemy_test_level0"(){
        given:
            arena.setLevel(0)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==HorizontalMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==NormalShotStrategy
    }

    def "create_enemy_test_level1"(){
        given:
            arena.setLevel(1)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==ZigZagMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==NormalShotStrategy
    }

    def "create_enemy_test_level2"(){
        given:
            arena.setLevel(2)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==HorizontalMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==DamageShotStrategy
    }

    def "create_enemy_test_level3"(){
        given:
            arena.setLevel(3)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==ZigZagMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==DamageShotStrategy
    }

    def "create_enemy_test_level4"(){
        given:
            arena.setLevel(4)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==HorizontalMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==BigShotStrategy
    }

    def "create_enemy_test_level5"(){
        given:
           arena.setLevel(5)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==ZigZagMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==BigShotStrategy
    }

    def "create_enemy_test_level6"(){
        given:
            arena.setLevel(6)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getHealth()==200
    }



    def "correct_row_test"(){
        when:
            arena.addSpell()
        then:
            assert arena.getSpells().get(arena.getSpells().size()-1).getPosition().getY()==arena.getHeight()-2
    }


    def "add_spell_test"(){
        given:
            Integer numberOfSpells = arena.getSpells().size()
        when:
            arena.addSpell()
        then:
            assert arena.getSpells().size()==numberOfSpells+1
    }

    def "doesnt_add_spell_when_all_pos_are_occupied"(){
        given:
            for(Integer i=0;i<arena.getWidth();i++) {
                arena.addSpell()
            }
            Integer numberOfSpells = arena.getSpells().size()
        when:
            arena.addSpell()
        then:
            assert arena.getSpells().size()==numberOfSpells

    }

    def "add_spell_in_new_pos_test"() {
        given:
        List<Position> occupiedPositions = new ArrayList<>()
        for (Integer i = 0; i <= 3; i++) {
            arena.addSpell()
        }
        occupiedPositions.add(arena.getSpells().get(arena.getSpells().size() - 1).getPosition())
        when:
            arena.addSpell()
        then:
            for (Position pos : occupiedPositions) {
                assert arena.getSpells().get(arena.getSpells().size() - 1).getPosition().getX() != pos.getX()
            }
    }
}
