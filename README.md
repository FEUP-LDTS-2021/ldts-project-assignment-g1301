

## L_TURMA13_GRUPO01 - SPACEINVADERS

**Example**:

In this old school game you play the role of a spaceship trying to survive an attack of flying monsters. Your ultimate goal is to KILL THEM ALL!!!  

In this fixed shooter the spaceship moves a laser cannon horizontally across the bottom of the screen and fires at aliens overhead. 

This project was developed by João Reis (up202007227) Pedro Gomes (up202006322) Rui Pires (up202008252).

### IMPLEMENTED FEATURES
Moving left: the spaceship will move one square to the left when the "a"  key is pressed.

Moving right: the spaceship will move one square to the right when the "d"  key is pressed.

Shooting: the spaceship will shoot one bullet in a vertical line when the space bar key is pressed.

Spells/PowerUps: During the game, and at random, 7 different types of spells/powerups can spawn on the ground near the spaceship. To resume briefly, 8 of them are implemented:

	Health Spell: Increases the spaceship health a bit.
	DamageIncrease Spell: Increases the spaceship's damage, permanently.
	InvincibleState Spell: Makes the spaceship immortal for 10secs, which means it can't take damage.
	NerfedState Spell: Makes the spaceship unable to shoot any bullet for 10secs.
	HealthReducer Spell: Takes a fraction of the spaceship's health points.
	DamageReducer Spell: Reduces the spaceship's damage, permanently.
	
Different waves of enemies: There are infinite waves of enemys, which means the user could play forever if he doesn't lose. The movement of the enemies can also vary depending on the wave. They can move on a straight line simply to the right, or they can reproduce a zig-zag like motion up and down while also moving to the right. The enemies health scales with the number of the wave in which he's a part of. 
	
	
### PLANNED FEATURES

For the future we are planning to implement our last spell which will be the "TeleportBackSpell". It will basically allow the user to teleport to the place where he first caught the spell, by pressing the "t" key character.
We're also planning to create a user menu interface.
And we are planning to also add information on the top of the game regarding the players health, spaceship damage and his current score (1point for each alien killed).
![How the game will look like after the previous feauture is implemented:](https://imgur.com/V3GO6tz)
https://imgur.com/V3GO6tz


### DESIGN



------

**Problem in Context**



**The Pattern**


**Implementation**

**Consequences**


#### KNOWN CODE SMELLS AND REFACTORING SUGGESTIONS


**Example of such a subsection**:

------

#### DATA CLASS


### TESTING
![Test coverage report](https://imgur.com/wfIhVpC)
https://imgur.com/wfIhVpC
Note that getters and setters were not tested due to the triviality of the matter.


### SELF-EVALUATION
So far up until this point, the workflow was very dynamic between all group members and the work was distributed equally. Everyone was motivated to contribute to the assignment.

**Example**:



