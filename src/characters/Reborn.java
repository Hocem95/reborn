package characters;


import map.Block;
import obstacles.Element;

public class Reborn extends Element{
	
	// 5 coeurs de vie au d�part
	public int nbCoeurs = 5;
	
	public Reborn(Block position) {
		super(position);
	}
	
	public Integer getNbCoeurs(){
		return nbCoeurs;
	}
	
	public void incrNbCoeurs() {
		this.nbCoeurs ++;
		System.out.println("Coeurs incrémenté : " + nbCoeurs);
	}
	
	public void decrNbCoeurs() {
		this.nbCoeurs --;
		System.out.println("Coeurs décrémenté : " + nbCoeurs);
	}
	
}