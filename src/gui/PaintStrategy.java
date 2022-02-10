package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import config.Config;
import map.Block;
import map.Map;
import objet.Epee;
import objet.Potion;
import obstacles.Arbre;
import obstacles.Eau;
import obstacles.Mur;
import personnages.Ennemi;
import personnages.Reborn;

public class PaintStrategy {

	int compteur = 0;

	private ImageIcon icoReborn;
	private Image reborn;

	
	private ImageIcon icoHerbe;
	private Image herbe;
	
	private ImageIcon icoEnnemi;
	private Image ennemi;
	
	private ImageIcon icoPotion;
	private Image potion;
	
	private ImageIcon icoEpee;
	private Image epee;
	
	public void paint(Map map, Graphics g) {
		int blockSize = Config.tailleBlock;
		Block[][] blocks = map.getBlocks();

		icoHerbe = new ImageIcon("herbe.png"); // image de l'herbe
		this.herbe = this.icoHerbe.getImage();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];
				g.drawImage(this.herbe, lineIndex * blockSize, columnIndex * blockSize, blockSize, blockSize, null);

			}
		}
	}

	public void paint(Reborn reborn, Graphics g) {
		Block position = reborn.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoReborn = new ImageIcon("reborn.png"); // image du personnage principal
		this.reborn = this.icoReborn.getImage();

		g.drawImage(this.reborn, x * blockSize, y * blockSize, blockSize, blockSize, null);

	}
	
	public void paint(Ennemi ennemi, Graphics g) {
		Block position = ennemi.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoEnnemi = new ImageIcon("intrus.png"); // image d'un ennemi 
		this.ennemi = this.icoEnnemi.getImage();
		
		g.drawImage(this.ennemi, x * blockSize, y * blockSize, blockSize, blockSize, null);
		
	}
	
	public void paint(Potion potion, Graphics g) {
		Block position = potion.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoPotion = new ImageIcon("potion.png"); // image d'une potion 
		this.potion = this.icoPotion.getImage();
		
		g.drawImage(this.potion, x * blockSize, y * blockSize, blockSize, blockSize, null);
		
	}
	public void paint(Epee epee, Graphics g) {
		Block position = epee.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();
		
		
		icoEpee = new ImageIcon("epee.png"); // image de l'épée
		this.epee = this.icoEpee.getImage();
		
		g.drawImage(this.epee, x * blockSize, y * blockSize, blockSize, blockSize, null);
		
	}

}