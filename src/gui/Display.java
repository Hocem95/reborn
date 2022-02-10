package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import map.Map;
import objet.Epee;
import objet.Potion;
import personnages.Ennemi;
import personnages.Reborn;
import process.ElementManager;

public class Display extends JPanel {

	private Map map;
	private ElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();

	public Display(Map map, ElementManager manager) {
		this.map = map;
		this.manager = manager;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(map, g);

		
		//Dessine le personnage principal
		for (Reborn reborn : manager.getReborn()) {
			paintStrategy.paint(reborn, g);
		
		}
		
		//Dessine les ennemis présent dans la liste
		for (Ennemi ennemi : manager.getEnnemi()) {
			paintStrategy.paint(ennemi, g);
		
		}
		
		//Dessine les potions présent dans la liste
		for (Potion potion : manager.getPotion()) {
			paintStrategy.paint(potion, g);
		
		}
		
		//Dessine les épées présent dans la liste
		for (Epee epee : manager.getEpee()) {
			paintStrategy.paint(epee, g);
		
		}
	}

}
