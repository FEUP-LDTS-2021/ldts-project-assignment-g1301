package com

import com.arena.Arena
import com.arena.ArenaDefiner
import com.googlecode.lanterna.TerminalPosition
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import com.enemy.strategy.BigShotStrategy
import com.enemy.strategy.DamageShotStrategy
import com.enemy.Enemy
import com.enemy.EnemyDefiner
import com.enemy.strategy.NormalShotStrategy
import com.enemy.strategy.HorizontalMovementStrategy
import com.enemy.strategy.ZigZagMovementStrategy
import com.position.Position
import com.shot.Shot
import com.spaceship.SpaceShipDefiner
import com.spaceship.Spaceship
import com.spaceship.observer.SpaceshipObserver
import com.spell.template.SpellGunDamage
import com.spell.template.SpellHealth
import com.spell.template.SpellHealthDamage
import com.spell.template.SpellInvincible
import com.spell.template.SpellLessGunDamage
import com.spell.template.SpellNerfed
import com.spell.template.SpellTPBack
import com.spell.template.SpellTemplate
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
            s = new Spaceship(10,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 0 && s.getShots().size()==0
    }

    def "check_big_shot_collision_test_right"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,1),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(10,1,new Position(3,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 0 && s.getShots().size()==0
    }

    def "check_big_shot_collision_test_left"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(3,1),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(10,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 0 && s.getShots().size()==0
    }

    def "shots_dont_collide"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(1,1),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(10,1,new Position(3,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            s.shoot()
            e.getShots()[0].moveDown()
            s.getShots()[0].moveUp()
            arena.checkShotCollisions()
        then:
            assert e.getShots().size() == 1 && s.getShots().size()==1
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
            spaceship1 = new Spaceship(10,1,new Position(2,2))
            spaceship2 = new Spaceship(10,1,new Position(2,2))
            spaceship3 = new Spaceship(10,1,new Position(2,2))
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

    def "process_key_leftmost_pos"(){
        given:
            KeyStroke key = Mock(KeyStroke)
            key.getCharacter() >> 'a'
            key.getKeyType() >> KeyType.Character
            SpaceShipDefiner s
            Arena a
        when:
            a = new Arena(10,10)
            s = new Spaceship(10,1,new Position(0,2))
            a.setSpaceship(s)
            a.processKey(key)
        then:
            assert s.getPosition().getX()==0
    }

    def "process_key_rightmost_pos"(){
        given:
            KeyStroke key = Mock(KeyStroke)
            key.getCharacter() >> 'd'
            key.getKeyType() >> KeyType.Character
            SpaceShipDefiner s
            Arena a
        when:
            a = new Arena(10,10)
            s = new Spaceship(10,1,new Position(a.width-1,2))
            a.setSpaceship(s)
            a.processKey(key)
        then:
            assert s.getPosition().getX()==a.width-1
    }


    def "check_shot_hits_enemies_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(10,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            s.shoot()
            s.getShots()[0].moveUp()
            s.getShots()[0].moveUp()
            arena.checkShotsHitEnemies()
        then:
            assert e.getHealth() == 9 && s.score==0
    }

    def "check_shots_killed_enemy"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(10,10,new Position(2,5))
            arena.setLevel(1)
            arena.addEnemy(e)
            arena.setSpaceship(s)
            s.shoot()
            s.getShots()[0].moveUp()
            s.getShots()[0].moveUp()
            arena.checkShotsHitEnemies()
        then:
            assert arena.enemies.size()==0 && s.score == 100
    }

    def "check_misses_spaceship_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(100,10,new Position(4,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            e.getShots()[0].moveDown()
            e.getShots()[0].moveDown()
            arena.checkShotsHitSpaceship()
        then:
            assert s.getHealth()==100 && e.getShots().size()==1
    }

    def "check_shot_hits_spaceships_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(110,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            e.getShots()[0].moveDown()
            e.getShots()[0].moveDown()
            arena.checkShotsHitSpaceship()
        then:
            assert  s.getHealth() == 10 && e.getShots().size()==0
    }

    def "check_big_shot_hits_spaceships_test_left"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(110,1,new Position(1,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            e.getShots()[0].moveDown()
            e.getShots()[0].moveDown()
            arena.checkShotsHitSpaceship()
        then:
            assert  s.getHealth() == 10 && e.getShots().size()==0
    }

    def "check_big_shot_hits_spaceships_test_right"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(10,new Position(2,2),new HorizontalMovementStrategy(),new BigShotStrategy())
            s = new Spaceship(110,1,new Position(3,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            e.shoot()
            e.getShots()[0].moveDown()
            e.getShots()[0].moveDown()
            arena.checkShotsHitSpaceship()
        then:
            assert  s.getHealth() == 10 && e.getShots().size()==0
    }

    def "move_enemies_frontier_test"(){
        given:
            EnemyDefiner e
        when:
            e = new Enemy(10,new Position(9,4), new HorizontalMovementStrategy(), new NormalShotStrategy())
            arena.addEnemy(e)
            arena.moveEnemies()
        then:
            assert e.getPosition().getX() == 8
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
            def a = Spy(new Arena(10,10))
        when:
            s = new Spaceship(10,1,new Position(2,10))
            a.setSpaceship(s)
            def e = new Enemy(100,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            a.addEnemy(e)
            e.shoot()
            s.shoot()
            a.moveShots()
        then:
            assert s.getShots()[0].getPosition().getY() == 8 && e.getShots()[0].getPosition().getY()==4
            2*a.checkShotCollisions()
            1*a.checkShotsHitSpaceship()
            1*a.checkShotsHitEnemies()
            1*a.removeShotsOutOfBounds()
    }



    def "remove_shots_out_of_bounds_test"(){
        given:
            SpaceShipDefiner s
            EnemyDefiner e
        when:
            s = new Spaceship(10,1,new Position(2,0))
            e = new Enemy(10,new Position(3,10),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s.shoot()
            s.shoot()
            e.shoot()
            e.shoot()
            arena.setSpaceship(s)
            arena.addEnemy(e)
            arena.removeShotsOutOfBounds()
        then:
            assert s.getShots().isEmpty()
            assert e.getShots().isEmpty()
    }


    def "doesnt_remove_shouts_out_of_bounds_test"(){
        given:
            SpaceShipDefiner s
            EnemyDefiner e
        when:
            s = new Spaceship(10,1,new Position(2,1))
            e = new Enemy(10,new Position(3,9),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s.shoot()
            e.shoot()
            arena.setSpaceship(s)
            arena.addEnemy(e)
            arena.removeShotsOutOfBounds()
        then:
            assert s.getShots().size()==1
            assert e.getShots().size()==1
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
            def aux = arena.level
            def enemies =arena.createEnemies()
        then:
            assert arena.getEnemies().get(0).getMovementStrategy().getClass()==HorizontalMovementStrategy
            assert arena.getEnemies().size() == 28 && arena.level == aux + 1
            assert arena.getEnemies().get(0).getShootingStrategy().getClass()==NormalShotStrategy
            assert !enemies.empty
    }

    def "create_enemy_test_level1"(){
        given:
            arena.setLevel(1)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()== ZigZagMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()==NormalShotStrategy
    }

    def "create_enemy_test_level2"(){
        given:
            arena.setLevel(2)
        when:
            arena.createEnemies()
        then:
            arena.getEnemies().get(0).getMovementStrategy().getClass()==HorizontalMovementStrategy
            arena.getEnemies().get(0).getShootingStrategy().getClass()== DamageShotStrategy
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
            assert arena.spells[0].getPosition().getY() == arena.getHeight() -2
    }

    def "add_many_spells_test"(){
        when:
            for (int i = 0 ; i < arena.width; i++){
                arena.addSpell()
            }
        arena.addSpell()
        then:
            assert arena.getSpells().size() == arena.width
            assert arena.spells[arena.spells.size() - 1].getPosition().getY() == arena.getHeight() - 2
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
            assert arena.getSpells().size() == arena.width

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

    def "check_caught_Health"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            def oldHealth = arena.spaceship.getHealth();
            arena.spells.add(new SpellHealth(new Position(0,0)))
            arena.spells.add(new SpellHealth(new Position(2,2)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.getHealth()==oldHealth+300
            assert arena.getSpells().size()== 1
    }

    def "check_caught_Damage"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            def oldHealth = arena.spaceship.getHealth();
            arena.spells.add(new SpellHealthDamage(new Position(0,0)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.getHealth()==oldHealth-200
            assert arena.getSpells().size()==0
    }

    def "check_became_invincible"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            arena.spells.add(new SpellInvincible(new Position(0,0)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.state=="invincible"
            assert arena.getSpells().size()==0
    }

    def "check_became_nerfed"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            arena.spells.add(new SpellNerfed(new Position(0,0)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.state=="nerfed"
            assert arena.getSpells().size()==0
    }

    def "check_more_damage"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            def oldDamage = arena.spaceship.getDamage();
            arena.spells.add(new SpellGunDamage(new Position(0,0)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.getDamage()==oldDamage+100
            assert arena.getSpells().size()==0
    }

    def "check_less_damage"(){
        given:
            arena.spaceship.setPosition(new Position(0,0))
            def oldDamage = arena.spaceship.getDamage();
            arena.spells.add(new SpellLessGunDamage(new Position(0,0)))
        when:
            arena.checkCaughtSpell();
        then:
            assert arena.spaceship.getDamage()==oldDamage-100
            assert arena.getSpells().size()==0
    }

    def "update_spaceship_state_test_more_than_10s"(){
        when:
            arena.getSpaceship().becomeInvincible();
            arena.getSpaceship().last_transition_instant = System.currentTimeMillis()-10001
            //set instant where spaceship became invincible to 10 seconds ago
            arena.updateSpaceShipState();
        then:
            assert arena.getSpaceship().state == "normal"
    }

    def "update_spaceship_state_test_10s"(){
        when:
            arena.getSpaceship().becomeInvincible();
            arena.getSpaceship().last_transition_instant = System.currentTimeMillis()-10000
            //set instant where spaceship became invincible to 10 seconds ago
            arena.updateSpaceShipState();
        then:
            assert arena.getSpaceship().state == "normal"
    }

    def "check_active_spells_test"(){
        when:
            arena.addSpell()
            arena.checkActiveSpells()
        then:
            assert arena.getSpells().size() == 1
    }


    def "check_non_active_spells_test_more_than_10s"(){
        when:
            arena.addSpell()
            arena.getSpells().get(0).time = System.currentTimeMillis()-10001
            // set spell as if it was created 10 seconds ago (so it is removed)
            arena.checkActiveSpells()
        then:
           assert arena.getSpells().size() == 0
    }


    def "check_non_active_spells_test_10s"(){
        when:
        arena.addSpell()
        arena.getSpells().get(0).time = System.currentTimeMillis()-10000
        // set spell as if it was created 10 seconds ago (so it is removed)
        arena.checkActiveSpells()
        then:
        assert arena.getSpells().size() == 0
    }

    def "check_tp_back"() {
        given:
            Position old_pos = new Position(1, 2)
            Position new_pos = new Position(5, 10)
            SpellTemplate spell = new SpellTPBack(old_pos)
            arena.spells.add(spell)
            Spaceship s = new Spaceship(1000, 100, new Position(1, 2))
            SpaceshipObserver observer = Spy(SpaceshipObserver)
            s.tpObserver = observer
            arena.setSpaceship(s)
            KeyStroke key = Mock(KeyStroke)
            key.getCharacter() >> 't'
            key.getKeyType() >> KeyType.Character
        when:
            arena.checkCaughtSpell()
            s.setPosition(new_pos)
            arena.processKey(key)
        then:
            assert (s.getPosition().getX() == old_pos.getX() && s.getPosition().getY() == old_pos.getY())
            1 * observer.caughtTPback(s)
            1 * observer.usedTPback(s)
    }


    def "arena_draw"(){
        given:
            def graphics = Mock(TextGraphics)
            Arena a = new Arena(150,50)
            List<Enemy> enemies = new ArrayList<>()
            List<Shot> shots = new ArrayList<>()
            for (int i=0;i<5;i++)
                shots.add(Mock(Shot))
            List<SpellTemplate> spells = new ArrayList<>()
            Spaceship ss = Mock(Spaceship)
            ss.getShots() >> shots
            a.setSpaceship(ss)
            for (int i=0;i<5;i++){
                List<Shot> shots2 = new ArrayList<>()
                for (int j=0;j<5;j++)
                    shots2.add(Mock(Shot))
                Enemy e = Mock(Enemy)
                e.getShots() >> shots2
                enemies.add(e)
            }
            a.enemies = enemies
            for (int i=0;i<5;i++) {
                SpellTemplate spell = Mock(SpellTemplate)
                spells.add(spell)
            }
            a.spells = spells
            ss.getScore()>>100
            ss.getDamage()>>100
            ss.getHealth()>>100
        when:
            a.draw(graphics)
        then:
            1 * graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            1 * graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(a.getWidth(), a.getHeight()), ' ');
            1 * graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFE"));
            1 * graphics.putString(new TerminalPosition(0,0), "Health : " + a.getSpaceship().getHealth());
            1 * graphics.putString(new TerminalPosition(14,0), "Damage : " + a.getSpaceship().getDamage());
            1 * graphics.putString(new TerminalPosition(28,0), "Score : " + a.getSpaceship().getScore());
            for (Enemy enemy : enemies) {
                1 * enemy.draw(graphics)
                for(Shot s:enemy.getShots())
                    1*s.draw(graphics)
            }
            for (SpellTemplate spell : spells) {
                1 * spell.draw(graphics);
            }
            1 * ss.draw(graphics);
            for (Shot s:ss.getShots())
                1*s.draw(graphics)
    }

    def "kill_enemy_test"(){
        given:
            EnemyDefiner e
            SpaceShipDefiner s
        when:
            e = new Enemy(1,new Position(2,2),new HorizontalMovementStrategy(),new NormalShotStrategy())
            s = new Spaceship(10,1,new Position(2,5))
            arena.addEnemy(e)
            arena.setSpaceship(s)
            def aux = arena.getEnemies().size()
            s.shoot()
            s.getShots()[0].moveUp()
            s.getShots()[0].moveUp()
            arena.checkShotsHitEnemies()

        then:
            assert e.getHealth() == 0 && arena.getEnemies().size() == aux -1 && s.getShots().size()==0
    }

    def "arena_test"(){
        given:
            ArenaDefiner a
        when:
             1 + 1
        then:
            assert arena.getSpaceship().getPosition().getX() == arena.width / 2
            assert arena.getSpaceship().getPosition().getY() == arena.height - 2
    }


    def "create spell"(){
        given:
            def previousSpells = arena.getSpells().size()
            def random = Mock(Random)
            random.nextInt(100)>>99
        when:
            arena.createSpell(random)
        then:
            assert arena.getSpells().size() == previousSpells +1
    }

    def "doesnt create spell"(){
        given:
            def previousSpells = arena.getSpells().size()
            def random = Mock(Random)
            random.nextInt(100)>>5
        when:
            arena.createSpell(random)
        then:
            assert arena.getSpells().size() == previousSpells
    }

    def "enemies shoot"(){
        given:
            List<Enemy> enemies = new ArrayList<>()
            for(int i=0;i<4;i++)
                enemies.add(Mock(Enemy))
            arena.enemies = enemies
            def random = Mock(Random)
            random.nextInt(200) >>> [2,2,199,2] >> {throw new InternalError()}
        when:
            arena.shootEnemies(random)
        then:
            1 * enemies.get(2).shoot()
    }
}

