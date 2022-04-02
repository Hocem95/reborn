package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import characters.Enemy;
import characters.NPC;
import characters.Reborn;
import config.Config;
import map.Block;
import map.Map;
import objet.Sword;
import objet.DragonBall;
import objet.Potion;

public class PaintStrategy {

	int compteur = 0;

	private ImageIcon icoReborn;
	private Image reborn;

	private ImageIcon icoNPC_goku;
	private Image npc_goku;

	private ImageIcon icoNPC_vegeta;
	private Image npc_vegeta;

	private ImageIcon icoHerbe;
	private Image herbe;

	private ImageIcon icoFence;
	private Image fence;
	private ImageIcon icoFenceRight;
	private Image fenceRight;
	private ImageIcon icoFenceLeft;
	private Image fenceLeft;
	private ImageIcon icoFenceDoor;
	private Image fenceDoor;
	private ImageIcon icoFenceVertical;
	private Image fenceVertical;

	private ImageIcon icoArbre;
	private Image arbre;

	private ImageIcon icoEau;
	private Image eau;
	private ImageIcon icoWheat;
	private Image wheat;
	private ImageIcon icoFields;
	private Image fields;
	private ImageIcon icoCarrots;
	private Image carrots;
	private ImageIcon icoMur;
	private Image mur;

	private ImageIcon icoSand;
	private Image sand;
	private ImageIcon icoCow;
	private Image cow;
	private ImageIcon icoChicken;
	private Image chicken;
	private ImageIcon icoBridge;
	private Image bridge;

	private ImageIcon icoDungeonWall;
	private Image dungeonWall;

	private ImageIcon icoDungeonFloor;
	private Image dungeonFloor;
	private ImageIcon icoWoodFloor;
	private Image woodfloor;
	private ImageIcon icoEnnemi;
	private Image ennemi;

	private ImageIcon icoPotion;
	private Image potion;

	private ImageIcon icoEpee;
	private Image epee;

	private ImageIcon icoDragonBall;
	private Image dragonball;

	private ImageIcon icoDialog;
	private Image dialog;

	public void paint(Map map, Graphics g) {
		int blockSize = Config.tailleBlock;
		int i = 0;

		icoDialog = new ImageIcon("img/dialog.png");
		this.dialog = this.icoDialog.getImage();
		
		icoHerbe = new ImageIcon("img/herbe.png");
		this.herbe = this.icoHerbe.getImage();
		
		icoEau = new ImageIcon("img/water.png");
		this.eau = this.icoEau.getImage();
		
		icoArbre = new ImageIcon("img/arbre.png");
		this.arbre = this.icoArbre.getImage();
		
		icoMur = new ImageIcon("img/mur.png");
		this.mur = this.icoMur.getImage();
		
		icoSand = new ImageIcon("img/sand.png");
		this.sand = this.icoSand.getImage();

		icoFence = new ImageIcon("img/fence.png");
		this.fence = this.icoFence.getImage();

		icoWheat = new ImageIcon("img/wheat.png");
		this.wheat = this.icoWheat.getImage();

		icoFields = new ImageIcon("img/field.PNG");
		this.fields = this.icoFields.getImage();

		icoCow = new ImageIcon("img/cow.png");
		this.cow = this.icoCow.getImage();

		icoChicken = new ImageIcon("img/chicken.png");
		this.chicken = this.icoChicken.getImage();

		icoCarrots = new ImageIcon("img/carrots.PNG");
		this.carrots = this.icoCarrots.getImage();

		icoFenceRight = new ImageIcon("img/fence_right.png");
		this.fenceRight = this.icoFenceRight.getImage();

		icoFenceLeft = new ImageIcon("img/fence_left.png");
		this.fenceLeft = this.icoFenceLeft.getImage();

		icoFenceDoor = new ImageIcon("img/fence_door.png");
		this.fenceDoor = this.icoFenceDoor.getImage();

		icoFenceVertical = new ImageIcon("img/fence_vertical.png");
		this.fenceVertical = this.icoFenceVertical.getImage();

		icoBridge = new ImageIcon("img/bridge.png");
		this.bridge = this.icoBridge.getImage();

		icoWoodFloor = new ImageIcon("img/woodfloor.png");
		this.woodfloor = this.icoWoodFloor.getImage();
		
		icoDungeonWall = new ImageIcon("img/dungeonwall.png");
		this.dungeonWall = this.icoDungeonWall.getImage();
		
		icoDungeonFloor = new ImageIcon("img/dungeonfloor.png");
		this.dungeonFloor = this.icoDungeonFloor.getImage();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				switch (Config.configMap[i]) {
				case 0:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 1:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.arbre, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 2:
					g.drawImage(this.eau, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 3:
					g.drawImage(this.mur, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 4:
					g.drawImage(this.sand, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 5:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.fence, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 6:
					g.drawImage(this.bridge, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 7:
					g.drawImage(this.dungeonWall, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 8:
					g.drawImage(this.dungeonFloor, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 9:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.fenceVertical, columnIndex * blockSize + 7, lineIndex * blockSize, blockSize - 10,
							blockSize, null);
					break;
				case 10:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.fenceRight, columnIndex * blockSize, lineIndex * blockSize, blockSize + 5,
							blockSize + 2, null);
					break;
				case 11:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.fenceDoor, columnIndex * blockSize - 8, lineIndex * blockSize, blockSize + 15,
							blockSize, null);
					break;
				case 12:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.fenceLeft, columnIndex * blockSize, lineIndex * blockSize, blockSize + 5,
							blockSize + 2, null);
					break;
				case 13:
					g.drawImage(this.carrots, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 14:
					g.drawImage(this.wheat, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					break;
				case 15:
					g.drawImage(this.fields, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 16:
					g.drawImage(this.woodfloor, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				case 17:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.cow, columnIndex * blockSize - 15, lineIndex * blockSize, blockSize + 15,
							blockSize, null);
					break;
				case 18:
					g.drawImage(this.herbe, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize, null);
					g.drawImage(this.chicken, columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize,
							null);
					break;
				}
				i++;

			}
		}

	}

	public void paint(Reborn reborn, Graphics g) {
		Block position = reborn.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoReborn = new ImageIcon("img/reborn.png"); // image du personnage principal
		this.reborn = this.icoReborn.getImage();

		g.drawImage(this.reborn, x * blockSize, y * blockSize - 15, blockSize, blockSize + 15, null);

	}

	public void paint(Enemy ennemi, Graphics g) {
		Block position = ennemi.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoEnnemi = new ImageIcon("img/ennemi.png"); // image d'un ennemi
		this.ennemi = this.icoEnnemi.getImage();

		g.drawImage(this.ennemi, x * blockSize, y * blockSize, blockSize, blockSize, null);

	}

	public void paint(Potion potion, Graphics g) {
		Block position = potion.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoPotion = new ImageIcon("img/potion.png"); // image d'une potion
		this.potion = this.icoPotion.getImage();

		g.drawImage(this.potion, x * blockSize, y * blockSize, blockSize, blockSize, null);

	}

	public void paint(Sword epee, Graphics g) {
		Block position = epee.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoEpee = new ImageIcon("img/epee.png"); // image de l'epee
		this.epee = this.icoEpee.getImage();

		g.drawImage(this.epee, x * blockSize, y * blockSize, blockSize, blockSize, null);

	}

	public void paint(NPC npc, Graphics g) {
		Block position = npc.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		switch (npc.getNom()) {
		case "goku":
			icoNPC_goku = new ImageIcon("img/npc_goku.png"); // image du npc goku
			this.npc_goku = this.icoNPC_goku.getImage();

			g.drawImage(this.npc_goku, x * blockSize, y * blockSize - 15, blockSize, blockSize + 15, null);
			break;
		case "vegeta":
			icoNPC_vegeta = new ImageIcon("img/npc_vegeta.png"); // image du npc goku
			this.npc_vegeta = this.icoNPC_vegeta.getImage();

			g.drawImage(this.npc_vegeta, x * blockSize + 4, y * blockSize - 18, blockSize - 8, blockSize + 18, null);
			break;
		}
	}

	public void paintQuestGoku(NPC npc, Graphics g) {
		String chaine[] = npc.getQuest().split("\n");

		g.drawImage(this.dialog, npc.getPosition().getColumn() * 28, npc.getPosition().getLine() * 22 - 100, 400, 400,
				null);
		g.setFont(g.getFont().deriveFont(18f));
		g.drawString(chaine[0], npc.getPosition().getColumn() * 28 + 70, npc.getPosition().getLine() * 42 - 100);
		g.drawString(chaine[1], npc.getPosition().getColumn() * 28 + 70, npc.getPosition().getLine() * 42 - 60);
	}

	public void paintQuestVegeta(NPC npc, Graphics g) {
		String chaine[] = npc.getQuest().split("\n");

		g.drawImage(this.dialog, npc.getPosition().getColumn() * 25, npc.getPosition().getLine() * 23, 400, 400, null);
		g.setFont(g.getFont().deriveFont(18f));
		g.drawString(chaine[0], npc.getPosition().getColumn() * 25 + 70, npc.getPosition().getLine() * 41 - 110);
		g.drawString(chaine[1], npc.getPosition().getColumn() * 25 + 70, npc.getPosition().getLine() * 41 - 70);
	}

	public void paint(DragonBall dragonball, Graphics g) {
		Block position = dragonball.getPosition();
		int blockSize = Config.tailleBlock;
		int y = position.getLine();
		int x = position.getColumn();

		icoDragonBall = new ImageIcon("img/dragonball4.png"); // image d'un ennemi
		this.dragonball = this.icoDragonBall.getImage();

		g.drawImage(this.dragonball, x * blockSize, y * blockSize, blockSize, blockSize, null);

	}

}
