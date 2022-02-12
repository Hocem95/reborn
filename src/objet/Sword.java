package objet;

import map.Block;
import obstacles.Element;

public class Sword extends Element{
	
	private Integer dommage = 2; // Dommage que l'�p�e peut infliger 
	
	public Sword(Block position) {
		super(position);
	}
}
