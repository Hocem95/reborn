package process;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import characters.Enemy;
import characters.NPC;
import characters.Reborn;
import config.Config;
import map.Block;
import map.Map;
import objet.Sword;
import objet.DragonBall;
import objet.Potion;

public class ElementManager {
	private Map map;
	Block position = new Block(17, 34);

	private Reborn reborn = new Reborn(position);

	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Potion> potions = new ArrayList<Potion>();
	private List<Sword> epees = new ArrayList<Sword>();
	private List<NPC> npcList = new ArrayList<NPC>();
	private List<DragonBall> dragonBalls = new ArrayList<DragonBall>();

	private boolean firstTimeSeeingNPC;
	private int directionX;
	private int directionY;

	private int numberEnemyAlive;

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
			if (hitbox(position) == 1 && isAvailable(newPosition) == true) {
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
			if (hitbox(position) == 1 && isAvailable(newPosition) == true) {
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
			if (hitbox(position) == 1 && isAvailable(newPosition) == true) {
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
			if (hitbox(position) == 1 && isAvailable(newPosition) == true) {
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

	public void questDone() {
		// V�rifie si la qu�te 2 donn� par V�g�ta est fini ( 3 ennemies en vie de base )
		for (NPC npc : npcList) {
			if (npc.getNom() == "vegeta" && npc.isQuestTake()) {
				if (enemies.size() != numberEnemyAlive && !npc.isQuestFinish()) {
					npc.setQuestFinish(true);
					npc.setQuest("Erf... je suppose que tu es \ndevenu suffisamment fort...");
				} else if (!npc.isQuestFinish() && !firstTimeSeeingNPC) {
					npc.setQuest("Tu n'as toujours pas r�ussi ?\nLa honte.");
				}
			}
			if (npc.getNom() == "goku" && npc.isQuestTake()) {
				if (reborn.getHasDragonBall() && !npc.isQuestFinish()) {
					npc.setQuestFinish(true);
					reborn.delDragonBall();
					npc.setQuest("Merci de m'avoir ramen� \nma boule de cristal !");
				} else if (!npc.isQuestFinish() && !firstTimeSeeingNPC) {
					npc.setQuest("Tu ne la trouves pas? \nElle se trouve dans le donjon !");
				}
			}
		}

	}

	public void cheatKillAllReborn() {
		ListIterator<Enemy> enemyIterator = enemies.listIterator();
		while (enemyIterator.hasNext()) {
			enemyIterator.next();
			enemyIterator.remove();
		}
	}

	public void cheatInvincibleReborn() {
		if (reborn.getNbCoeurs() < 5) {
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

	// Int�ractio PNJ-Joueur Principal pour la gestion des qu�tes
	public int interactionNPC() {
		Block position = reborn.getPosition();
		// List<Enemy> eliminatedEnemies = new ArrayList<Enemy>();

		// Block attackPosition = reborn.getPosition();
		ListIterator<NPC> npcIterator = npcList.listIterator();
		while (npcIterator.hasNext()) {
			NPC npc = npcIterator.next();
			// Create an array with all the possible positions of a close npc
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
			// Checking if the npc is in range
			for (Block pos : inRangePositions) {
				if (npc.getPosition().equals(pos)) {
					switch (npc.getNom()) {
					case "goku":
						// V�rifie si la qu�te a �t� prise ou non
						if (!npc.isQuestTake()) {
							// Qu�te 1 prit
							npc.setQuestTake(true);
							firstTimeSeeingNPC = true;
						} else {
							questDone();
						}
						return 1;
					case "vegeta":
						if (!npc.isQuestTake()) {// V�rifie si la qu�te a �t� prise ou non
							// Qu�te 2 prit
							npc.setQuestTake(true);
							firstTimeSeeingNPC = true;
							numberEnemyAlive = enemies.size();
						} else {
							questDone();
						}
						return 2;
					}
				}
			}
		}
		firstTimeSeeingNPC = false;
		return 0;
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

		// Cas PNJ
		for (int i = 0; i < npcList.size(); i++) {
			if (((npcList.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&& (npcList.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				verif = 1;
			}
		}
		for (int i = 0; i < dragonBalls.size(); i++) {
			if (((dragonBalls.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&& (dragonBalls.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				reborn.pickUpDragonBall();
				dragonBalls.remove(i);
				verif = 1;
			}
		}

		if (verif == 0) {
			return 1; // Rien sur la route ou potion, il bouge
		} else {
			return 0; // Obstacle sur la route, il ne bouge pas
		}
	}

	public boolean isAvailable(Block block) {
		int index = block.getColumn() + Config.nbColumns * block.getLine();
		int val[] = Config.configMap;

		switch (val[index]) {
		case 0: // Herbe
			return true;
		case 4: // Sable
			return true;
		case 8: // Sol dj
			return true;
		case 15: // terre labour�e
			return true;
		case 6: // pont
			return true;
		case 16: // sol maison
			return true;
		default:
			return false;
		}
	}

	public void add(Enemy ennemi) {
		enemies.add(ennemi);

	}

	public void add(Potion potion) {
		potions.add(potion);
	}

	public void add(NPC npc) {
		npcList.add(npc);
	}

	public void add(DragonBall dragonball) {
		dragonBalls.add(dragonball);
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

	public List<DragonBall> getDragonBall() {
		return dragonBalls;
	}

	public void generateEnemies() {
		add(new Enemy(new Block(9, 13), "right", 7, 0));
		add(new Enemy(new Block(10, 2), "down", 5, 0));
		add(new Enemy(new Block(6, 6), "down", 6, 0));

	}

	public void generatePotion() {

		Potion potion = new Potion(new Block(10, 4));
		add(potion);

	}

	public void generateEpee() {

		Sword epee = new Sword(new Block(2, 16));
		add(epee);

	}

	public void generateNPC() {
		NPC npc_goku = new NPC(new Block(8, 30));
		NPC npc_vegeta = new NPC(new Block(15, 24));

		npc_goku.setNom("goku");
		npc_goku.setQuest("Peux-tu me ramener ma boule \nde cristal s'il te plait?");

		npc_vegeta.setNom("vegeta");
		npc_vegeta.setQuest("Essaies donc d'aller tuer un \nennemi, faiblard que tu es");

		add(npc_goku);
		add(npc_vegeta);
	}

	public void generateDragonBall() {
		DragonBall db4 = new DragonBall(new Block(3, 2));
		add(db4);
	}

	public void createMap() {
		generateEnemies();
		generatePotion();
		generateEpee();
		generateNPC();
		generateDragonBall();
	}

	public List<NPC> getNPC() {
		return npcList;
	}
}
