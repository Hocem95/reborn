package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import characters.Enemy;
import characters.NPC;
import map.Map;
import objet.Sword;
import objet.DragonBall;
import objet.Potion;
import process.ElementManager;

@SuppressWarnings("serial")
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

		// Dessine le personnage principal
		paintStrategy.paint(manager.getReborn(), g);

		// Dessine les ennemis prï¿½sent dans la liste
		for (Enemy ennemi : manager.getEnnemi()) {
			paintStrategy.paint(ennemi, g);
		}

		// Dessine les potions prï¿½sent dans la liste
		for (Potion potion : manager.getPotion()) {
			paintStrategy.paint(potion, g);
		}

		// Dessine les epees presentes dans la liste
		for (Sword epee : manager.getEpee()) {
			paintStrategy.paint(epee, g);
		}

		for (NPC npc : manager.getNPC()) {
			paintStrategy.paint(npc, g);
		}
		for (DragonBall db : manager.getDragonBall()) {
			paintStrategy.paint(db, g);
		}

		// Affichage quête 1 ou 2 dans une bulle
		if (manager.interactionNPC() == 1) {
			paintStrategy.paintQuestGoku(manager.getNPC().get(0), g);
		} else if (manager.interactionNPC() == 2) {
			paintStrategy.paintQuestVegeta(manager.getNPC().get(1), g);
		}

	}

}
