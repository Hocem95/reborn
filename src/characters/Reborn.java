package characters;


import map.Block;
import objet.Element;

public class Reborn extends Element{
	
	// 5 coeurs de vie au depart
	public int nbCoeurs = 5;
	public boolean hasPotion = false;
	public boolean hasSword = false;
	public boolean hasDragonBall = false;
	
	public Reborn(Block position) {
		super(position);
	}
	
	public Integer getNbCoeurs(){
		return nbCoeurs;
	}
	
	public void setNbCoeurs(int newHP) {
		this.nbCoeurs = newHP;
	}
	
	public void pickUpPotion() {			// utilisee qd reborn ramasse une potion
		this.hasPotion = true;
	}
	
	public void delPotion() {				// supprime la potion de l'inventaire de reborn qd elle est utilisee
		this.hasPotion = false;
	}
	
	public boolean getHasPotion() {			// retourne un boolean indiquant si Reborn a une potion ou pas
		return hasPotion;
	}
	
	public void pickUpSword() {				// utilisee qd reborn ramasse une epee
		this.hasSword = true;
	}
	
	public boolean getHasSword() {			// retourne un boolean indiquant si Reborn a une epee ou pas
		return hasSword;
	}

	public void pickUpDragonBall() {
		this.hasDragonBall = true;
	}
	public void delDragonBall() {				// supprime la potion de l'inventaire de reborn qd elle est utilisee
		this.hasDragonBall = false;
	}
	public boolean getHasDragonBall() {			// retourne un boolean indiquant si Reborn a une epee ou pas
		return hasDragonBall;
	}
}