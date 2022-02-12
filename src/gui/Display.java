package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import characters.Enemy;
import characters.Reborn;
import map.Map;
import objet.Sword;
import objet.Potion;
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
		paintStrategy.paint(manager.getReborn(), g);
		
		
		//Dessine les ennemis pr�sent dans la liste
		for (Enemy ennemi : manager.getEnnemi()) {
			paintStrategy.paint(ennemi, g);
		
		}
		
		//Dessine les potions pr�sent dans la liste
		for (Potion potion : manager.getPotion()) {
			paintStrategy.paint(potion, g);
		}
		
		//Dessine les �p�es pr�sent dans la liste
		for (Sword epee : manager.getEpee()) {
			paintStrategy.paint(epee, g);
		
		}
	}

}
