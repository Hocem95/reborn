package personnages;

import map.Block;
import obstacles.Element;

public class Reborn extends Element{
	
	// 5 coeurs de vie au départ
	private Integer nombreCoeur = 5;
	
	public Reborn(Block position) {
		super(position);
	}
	
	public Integer getNombreCoeur(){
		return nombreCoeur;
	}
	
	public void setNombreCoeur(Integer nbCoeur) {
		nombreCoeur+= nbCoeur;
	}
	
}