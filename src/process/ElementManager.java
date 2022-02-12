package process;



import java.util.ArrayList;
import java.util.List;

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
	
	public void moveEnemy() 
	{
		i = i + 1;
		Enemy enemy = enemies.get(0);
		/*Block position = enemy.getPosition();
		if (position.getColumn() < Config.nbColumns) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
			if(hitbox(position) == 1) {
				enemy.setPosition(newPosition);
			}
		}*/
		
	}
	
	public void moveLeftReborn() {
		Block position = reborn.getPosition();
		directionX = 0;
		directionY = -1;
		if (position.getColumn() > 0) {
			Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
			if(hitbox(position) == 1) {
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
			if(hitbox(position) == 1) {
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
			if(hitbox(position) == 1) {
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
			if(hitbox(position) == 1) {
				reborn.setPosition(newPosition);
			}
		}
	}
	
	
	public int hitbox(Block position){		
		int verif = 0 ;
		
		// Cas ennemi
		for(int i = 0; i<enemies.size();i++) {
			if(((enemies.get(i).getPosition().getLine()) == position.getLine() + directionX)
				&&(enemies.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				// On vérifie si le personnage ne meurt pas suite aux dégats subis
				if(reborn.getNbCoeurs()>0) {
					reborn.decrNbCoeurs();
				}// else{game over à coder}
				verif = 1;
			}
		}
		
		// Cas potions
		for(int i = 0; i<potions.size();i++) {
			if(((potions.get(i).getPosition().getLine()) == position.getLine() + directionX)
				&&(potions.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
				// On vérifie si le personnage a déjà perdu des coeurs auquel cas il se soigne pas
				if(reborn.getNbCoeurs()<5) {
					reborn.incrNbCoeurs();
				}// else{déjà full life}
				verif = 1;
			}
		}
		
		if(verif == 0) {
			return 1;		//Rien sur la route ou potion, il bouge
		}else {
			return 0;		//Obstacle sur la route, il ne bouge pas
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
	
	public void generateEnnemi() {
			
		Enemy ennemi = new Enemy(new Block(0,2));
		add(ennemi);
			
	}
	
	public void generatePotion() {
		
		Potion potion = new Potion(new Block(3,2));
		add(potion);
		
	}
	
	public void generateEpee() {
	
		Sword epee = new Sword(new Block(8,7));
		add(epee);
		
	}
		public void createMap() {
		generateEnnemi();
		generatePotion();
		generateEpee();
		
	}
}
