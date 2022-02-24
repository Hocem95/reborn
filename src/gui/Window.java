package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import config.Config;
import map.Map;
import objet.Sword;
import process.ElementManager;
import process.GameBuilder;

public class Window extends JFrame implements Runnable, ActionListener {

	private Display display;
	private Map map;
	private ElementManager manager;

	private boolean turn = true; // variable qui permet de faire tourner le programme ou non (thread)

	private JPanel infobar = new JPanel();

	ImageIcon coeur = new ImageIcon(
			new ImageIcon("coeur.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon potion = new ImageIcon(
			new ImageIcon("potion.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon epee = new ImageIcon(new ImageIcon("epee.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	// Cinq coeurs de vie du personnage principal
	JLabel coeurLabelUn = new JLabel(coeur);
	JLabel coeurLabelDeux = new JLabel(coeur);
	JLabel coeurLabelTrois = new JLabel(coeur);
	JLabel coeurLabelQuatre = new JLabel(coeur);
	JLabel coeurLabelCinq = new JLabel(coeur);

	// Potion dans l'inventaire
	JLabel potionInventaire = new JLabel(potion);
	JLabel epeeInventaire = new JLabel(epee);

	public Window(String title) {
		super(title);
		init();
	}

	public void init() {
		// FENETRE DE LA SIMULATION
		Container contentPane = getContentPane();
		KeyControls keyControls = new KeyControls();
		JTextField textField = new JTextField();
		textField.addKeyListener(keyControls);
		contentPane.add(textField, BorderLayout.SOUTH);
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		display = new Display(map, manager);

		// Barre d'info

		infobar.setPreferredSize(new Dimension(Config.tailleBlock * Config.nbColumns, 5 * Config.tailleBlock));
		infobar.setBackground(Color.black);
		infobar.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// commentaires ci-dessous tr�s important pour la suite du d�v
		// coeurLabel.setHorizontalAlignment(SwingConstants.LEFT); // Places les coeurs
		// � gauche de la barre d'information
		// coeurLabelDeux.setHorizontalAlignment(SwingConstants.LEFT);

		// Coeurs cod� en dur, � mettre en liste par la suite

		infobar.add(coeurLabelUn);
		infobar.add(coeurLabelDeux);
		infobar.add(coeurLabelTrois);
		infobar.add(coeurLabelQuatre);
		infobar.add(coeurLabelCinq);
		infobar.add(potionInventaire);
		infobar.add(epeeInventaire);

		potionInventaire.setVisible(false);
		epeeInventaire.setVisible(false);

		contentPane.add(infobar, BorderLayout.NORTH);

		contentPane.add(display, BorderLayout.CENTER);

		this.setSize(1600, 1200);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(true);

		// Icone de la fen�tre
		Image icon = Toolkit.getDefaultToolkit().getImage("reborn.png");
		this.setIconImage(icon);

		textField.requestFocusInWindow();
		textField.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void run() {

		manager.createMap();

		while (turn == true) {

			try {
				Thread.sleep(Config.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			manager.moveEnemies();
			display.repaint();
			updateValues();
			try {
				Thread.sleep(1000 - Config.GAME_SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private class KeyControls implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			char keyChar = event.getKeyChar();

			switch (keyChar) {

			case 'q':
				manager.moveLeftReborn();

				break;
			case 'd':
				manager.moveRightReborn();

				break;
			case 'z':
				manager.moveUpReborn();

				break;
			case 's':
				manager.moveDownReborn();

				break;
			case 'k':
				manager.attackReborn();

				break;
			case 'a':
				if (manager.getReborn().getHasPotion()) {
					manager.usePotion();
				}
			default:
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void updateHP() {
		int nbCoeursActuels = manager.getReborn().getNbCoeurs();
		switch (nbCoeursActuels) {
		case 5:
			coeurLabelCinq.setVisible(true);
			coeurLabelQuatre.setVisible(true);
			coeurLabelTrois.setVisible(true);
			coeurLabelDeux.setVisible(true);
			coeurLabelUn.setVisible(true);
			break;
		case 4:
			coeurLabelCinq.setVisible(false);
			coeurLabelQuatre.setVisible(true);
			coeurLabelTrois.setVisible(true);
			coeurLabelDeux.setVisible(true);
			coeurLabelUn.setVisible(true);
			break;
		case 3:
			coeurLabelCinq.setVisible(false);
			coeurLabelQuatre.setVisible(false);
			coeurLabelTrois.setVisible(true);
			coeurLabelDeux.setVisible(true);
			coeurLabelUn.setVisible(true);
			break;
		case 2:
			coeurLabelCinq.setVisible(false);
			coeurLabelQuatre.setVisible(false);
			coeurLabelTrois.setVisible(false);
			coeurLabelDeux.setVisible(true);
			coeurLabelUn.setVisible(true);
			break;
		case 1:
			coeurLabelCinq.setVisible(false);
			coeurLabelQuatre.setVisible(false);
			coeurLabelTrois.setVisible(false);
			coeurLabelDeux.setVisible(false);
			coeurLabelUn.setVisible(true);
			break;
		case 0:
			coeurLabelCinq.setVisible(false);
			coeurLabelQuatre.setVisible(false);
			coeurLabelTrois.setVisible(false);
			coeurLabelDeux.setVisible(false);
			coeurLabelUn.setVisible(false);
			break;
		}
	}

	private void updateInventory() {
		// Affichage potion dans l'inventaire
		if (manager.getReborn().getHasPotion()) {
			potionInventaire.setVisible(true);
		} else {
			potionInventaire.setVisible(false);
		}

		// Affichage de l'epee dans l'inventaire
		if (manager.getReborn().getHasSword()) {
			epeeInventaire.setVisible(true);
		} else {
			epeeInventaire.setVisible(false);
		}
	}

	private void updateValues() {
		updateHP();
		updateInventory();
	}

}
