package objet;

import map.Block;
import obstacles.Element;

public class Epee extends Element{
	
	private Integer dommage = 2; // Dommage que l'�p�e peut infliger 
	
	public Epee(Block position) {
		super(position);
	}
}
