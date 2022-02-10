package process;



import java.util.ArrayList;
import java.util.List;

import config.Config;
import map.Block;
import map.Map;
import objet.Epee;
import objet.Potion;
import personnages.Ennemi;
import personnages.Reborn;

public class ElementManager {
	private Map map;
	Block position = new Block(0, 0);
	
	private List<Reborn> rebornList = new ArrayList<Reborn>();
	private List<Ennemi> ennemiList = new ArrayList<Ennemi>();
	private List<Potion> potionList = new ArrayList<Potion>();
	private List<Epee> epeeList = new ArrayList<Epee>();
	
	private int directionX;
	private int directionY;
	
	public ElementManager(Map map) {
		this.map = map;
		
	}
	
	public void moveLeftReborn() {
		Block position = rebornList.get(0).getPosition();
		Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
		directionX = 0;
		directionY = -1;
		if (position.getColumn() > 0) {
			if(hitbox(position) == 1) {
				rebornList.get(0).setPosition(newPosition);
			}

		}
	}

	public void moveRightReborn() {
		Block position = rebornList.get(0).getPosition();
		Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
		directionX = 0;
		directionY = +1;
		if (position.getColumn() < Config.nbColumns) {
			if(hitbox(position) == 1) {
				rebornList.get(0).setPosition(newPosition);
			}
		}
	}

	public void moveUpReborn() {
		Block position = rebornList.get(0).getPosition();
		Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
		directionX = -1;
		directionY = 0;
		if (position.getLine() > 0) {
			if(hitbox(position) == 1) {
				rebornList.get(0).setPosition(newPosition);
			}
		}
	}

	public void moveDownReborn() {
		Block position = rebornList.get(0).getPosition();
		Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
		directionX = +1;
		directionY = 0;
		if (position.getLine() < Config.nbLines) {
			if(hitbox(position) == 1) {
				rebornList.get(0).setPosition(newPosition);
			}
		}
	}
	
	
	public int hitbox(Block position){		
		int verif = 0 ;
		
		// Cas potion
		for(int i = 0; i<potionList.size();i++) {
				if(((potionList.get(i).getPosition().getLine()) == position.getLine() + directionX)
					&&(potionList.get(i).getPosition().getColumn()) == position.getColumn() + directionY) {
					
					// Le personnage principal gagne un coeur si coeur infèrieure à 5
					if(rebornList.get(0).getNombreCoeur()<5) {
						rebornList.get(0).setNombreCoeur(1);
					}

					verif = 1;
				}
		}
		if(verif == 0) {
			return 1;		//Rien sur la route ou potion, il bouge
		}else {
			return 0;		//Obstacle sur la route, il ne bouge pas
		} 
	}
	
	
	public void add(Reborn reborn) {
		rebornList.add(reborn);
		
	}
	
	public void add(Ennemi ennemi) {
		ennemiList.add(ennemi);
		
	}
	
	public void add(Potion potion) {
		potionList.add(potion);
	}
	
	public void add(Epee epee) {
		epeeList.add(epee);
	}
	
	public List<Reborn> getReborn() {
		return rebornList;
	}
	
	public List<Ennemi> getEnnemi() {
		return ennemiList;
	}
	
	public List<Potion> getPotion() {
		return potionList;
	}
	
	public List<Epee> getEpee() {
		return epeeList;
	}
	
	public void generateReborn() {
		
		Reborn reborn = new Reborn(position);
		add(reborn);
		
	}
	
	public void generateEnnemi() {
			
		Ennemi ennemi = new Ennemi(new Block(0,2));
		add(ennemi);
			
	}
	
	public void generatePotion() {
		
		Potion potion = new Potion(new Block(3,2));
		add(potion);
		
	}
	
	public void generateEpee() {
	
		Epee epee = new Epee(new Block(8,7));
		add(epee);
		
	}
		public void createMap() {
		
		generateReborn();
		generateEnnemi();
		generatePotion();
		generateEpee();
		
	}
}
