package process;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import characters.Enemy;
import characters.Reborn;
import config.Config;
import map.Block;
import map.Map;
import objet.Sword;
import objet.Potion;

public class ElementManager {
	private Map map;
	Block position = new Block(0, 0);

	private Reborn reborn = new Reborn(position);

	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Potion> potions = new ArrayList<Potion>();
	private List<Sword> epees = new ArrayList<Sword>();

	private int directionX;
	private int directionY;

	private int i = 0;

	public ElementManager(Map map) {
		this.map = map;
	}

	public void moveEnemies() {
		for (Enemy enemy : enemies) {
			moveEnemy(enemy);
		}
	}

	public void moveEnemy(Enemy enemy) {
		switch (enemy.getDirection()) {
		case "left":
			moveLeftEnemy(enemy);
			break;
		case "right":
			moveRightEnemy(enemy);
			break;
		case "up":
			moveUpEnemy(enemy);
			break;
		case "down":
			moveDownEnemy(enemy);
			break;
		}
	}

	public void moveLeftEnemy(Enemy enemy) {
		Block position = enemy.getPosition();
		int c, l;
		if (position.getColumn() > 0) {
			c = position.getColumn();
			l = position.getLine();
			if (map.getBlock(l, c - 1).isAvailable()) {
				enemy.setPosition(map.getBlock(l, c - 1));
				enemy.setMove(enemy.getMove() - 1);
				if (enemy.getMove() == 0) {
					enemy.setDirection("right");
				}
			} else {
				enemy.setDirection("right");
			}
		}
	}

	public void moveRightEnemy(Enemy enemy) {
		Block position = enemy.getPosition();
		int c, l;
		if (position.getColumn() < Config.nbColumns) {
			c = position.getColumn();
			l = position.getLine();
			if (map.getBlock(l, c + 1).isAvailable()) {
				enemy.setPosition(map.getBlock(l, c + 1));
				enemy.setMove(enemy.getMove() + 1);
				if (enemy.getMove() == enemy.getMaxMove()) {
					enemy.setDirection("left");
				}
			} else {
				enemy.setDirection("left");
			}
		}
	}

	public void moveUpEnemy(Enemy enemy) {
		Block position = enemy.getPosition();
		int c, l;
		if (position.getColumn() > 0) {
			c = position.getColumn();
			l = position.getLine();
			if (map.getBlock(l - 1, c).isAvailable()) {
				enemy.setPosition(map.getBlock(l - 1, c));
				enemy.setMove(enemy.getMove() - 1);
				if (enemy.getMove() == 0) {
					enemy.setDirection("down");
				}
			} else {
				enemy.setDirection("down");
			}
		}
	}

	public void moveDownEnemy(Enemy enemy) {
		Block position = enemy.getPosition();
		int c, l;
		if (position.getColumn() < Config.nbLines) {
			c = position.getColumn();
			l = position.getLine();
			if (map.getBlock(l + 1, c).isAvailable()) {
				enemy.setPosition(map.getBlock(l + 1, c));
				enemy.setMove(enemy.getMove() + 1);
				if (enemy.getMove() == enemy.getMaxMove()) {
					enemy.setDirection("up");
				}
			} else {
				enemy.setDirection("up");
			}
		}
	}

	public void moveLeftReborn() {
		Block position = reborn.getPosition();
		directionX = 0;
		directionY = -1;
		if (position.getColumn() > 0) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			if (hitbox(position) == 1) {
				reborn.setPosition(newPosition);
			}

		}
	}

	public void moveRightReborn() {
		Block position = reborn.getPosition();
		directionX = 0;
		directionY = +1;
		if (position.getColumn() < Config.nbColumns) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			if (hitbox(position) == 1) {
				reborn.setPosition(newPosition);
			}
		}
	}

	public void moveUpReborn() {
		Block position = reborn.getPosition();
		directionX = -1;
		directionY = 0;
		if (position.getLine() > 0) {
			Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
			if (hitbox(position) == 1) {
				reborn.setPosition(newPosition);
			}
		}
	}

	public void moveDownReborn() {
		Block position = reborn.getPosition();
		directionX = +1;
		directionY = 0;
		if (position.getLine() < Config.nbLines) {
			Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
			if (hitbox(position) == 1) {
				reborn.setPosition(newPosition);
			}
		}
	}

	public void usePotion() {
		reborn.delPotion();
		if (reborn.getNbCoeurs() < 3) {
			reborn.setNbCoeurs(reborn.getNbCoeurs() + 3);
		} else {
			reborn.setNbCoeurs(5);
		}
	}

	public void attackReborn() {
		Block position = reborn.getPosition();
		// List<Enemy> eliminatedEnemies = new ArrayList<Enemy>();

		// Block attackPosition = reborn.getPosition();
		ListIterator<Enemy> enemyIterator = enemies.listIterator();
		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();
			// Create an array with all the possible positions of a close enemy
			ArrayList<Block> inRangePositions = new ArrayList<Block>();
			if (position.getLine() > 0) {
				inRangePositions.add(map.getBlock(position.getLine() - 1, position.getColumn()));
			}
			if (position.getLine() < map.getLineCount()) {
				inRangePositions.add(map.getBlock(position.getLine() + 1, position.getColumn()));
			}
			if (position.getColumn() > 0) {
				inRangePositions.add(map.getBlock(position.getLine(), position.getColumn() - 1));
			}
			if (position.getColumn() < map.getColumnCount()) {
				inRangePositions.add(map.getBlock(position.getLine(), position.getColumn() + 1));
			}
			// Checking if the enemy is in range
			for (Block pos : inRangePositions) {
				if (enemy.getPosition().equals(pos)) {
					enemyIterator.remove();
					break;
				}
			}
		}
	}

	public int hitbox(Block position) {
		int verif = 0;

		// Cas ennemi
		for (int i = 0; i < enemies.size(); i++) {
			if (((enemies.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&& (enemies.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				// On vérifie si le personnage ne meurt pas suite aux dégats subis
				if (reborn.getNbCoeurs() > 0) {
					reborn.setNbCoeurs(reborn.getNbCoeurs() - 1);
				} // else{game over à coder}
				verif = 1;
			}
		}

		// Cas potions
		for (int i = 0; i < potions.size(); i++) {
			if (((potions.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&& (potions.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				reborn.pickUpPotion();
				potions.remove(i);
				verif = 1;
			}
		}

		// Cas sword
		for (int i = 0; i < epees.size(); i++) {
			if (((epees.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&& (epees.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				reborn.pickUpSword();
				epees.remove(i);
				verif = 1;
			}
		}

		if (verif == 0) {
			return 1; // Rien sur la route ou potion, il bouge
		} else {
			return 0; // Obstacle sur la route, il ne bouge pas
		}
	}

	public void add(Enemy ennemi) {
		enemies.add(ennemi);

	}

	public void add(Potion potion) {
		potions.add(potion);
	}

	public void add(Sword epee) {
		epees.add(epee);
	}

	public List<Enemy> getEnnemi() {
		return enemies;
	}

	public List<Potion> getPotion() {
		return potions;
	}

	public List<Sword> getEpee() {
		return epees;
	}

	public Reborn getReborn() {
		return reborn;
	}

	public void generateEnemies() {
		add(new Enemy(new Block(0, 2), "right", 10, 0));
		add(new Enemy(new Block(5, 5), "down", 5, 0));
		add(new Enemy(new Block(10, 10), "up", 6, 6));

	}

	public void generatePotion() {

		Potion potion = new Potion(new Block(3, 2));
		add(potion);

	}

	public void generateEpee() {

		Sword epee = new Sword(new Block(8, 7));
		add(epee);

	}

	public void createMap() {
		generateEnemies();
		generatePotion();
		generateEpee();

	}
}
