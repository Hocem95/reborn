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
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;

import config.Config;
import map.Map;
import process.ElementManager;
import process.GameBuilder;
import process.MusicManager;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class Window extends JFrame implements Runnable, ActionListener {

	private Display display;
	private Map map;
	private ElementManager manager;
	private MusicManager music;
	
	private boolean turn = true; // variable qui permet de faire tourner le programme ou non (thread)
	
	private JPanel infobar = new JPanel();

	// Fenêtre de fin du jeu en cas de défaite
	private JDialog gameOver = new JDialog(this, "Défaite");

	ImageIcon coeur = new ImageIcon(
			new ImageIcon("img/coeur.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon potion = new ImageIcon(
			new ImageIcon("img/potion.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon epee = new ImageIcon(
			new ImageIcon("img/epee.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	ImageIcon dragonBall = new ImageIcon(
			new ImageIcon("img/dragonball4.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	
	//Image du bouton de défaite dans la dernière frame
	ImageIcon gameOverPicture = new ImageIcon("img/gameOverPicture.jpg");

	// Cinq coeurs de vie du personnage principal
	JLabel coeurLabelUn = new JLabel(coeur);
	JLabel coeurLabelDeux = new JLabel(coeur);
	JLabel coeurLabelTrois = new JLabel(coeur);
	JLabel coeurLabelQuatre = new JLabel(coeur);
	JLabel coeurLabelCinq = new JLabel(coeur);

	// Potion dans l'inventaire
	JLabel potionInventaire = new JLabel(potion);
	JLabel epeeInventaire = new JLabel(epee);
	JLabel dragonBallInventaire = new JLabel(dragonBall);

	private JButton boutonFermeture; // Boutton fermeture du jeu dans le JDialog GameOver

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
		music = new MusicManager();
		manager = GameBuilder.buildInitMobile(map, music);
		display = new Display(map, manager);
		
		// Barre d'info

		infobar.setPreferredSize(new Dimension(Config.tailleBlock * Config.nbColumns, 5 * Config.tailleBlock));
		infobar.setBackground(Color.black);
		infobar.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// commentaires ci-dessous trï¿½s important pour la suite du dï¿½v
		// coeurLabel.setHorizontalAlignment(SwingConstants.LEFT); // Places les coeurs
		// ï¿½ gauche de la barre d'information
		// coeurLabelDeux.setHorizontalAlignment(SwingConstants.LEFT);

		// Coeurs codï¿½ en dur, ï¿½ mettre en liste par la suite

		infobar.add(coeurLabelUn);
		infobar.add(coeurLabelDeux);
		infobar.add(coeurLabelTrois);
		infobar.add(coeurLabelQuatre);
		infobar.add(coeurLabelCinq);
		infobar.add(potionInventaire);
		infobar.add(epeeInventaire);
		infobar.add(dragonBallInventaire);

		potionInventaire.setVisible(false);
		epeeInventaire.setVisible(false);
		dragonBallInventaire.setVisible(false);

		contentPane.add(infobar, BorderLayout.NORTH);

		contentPane.add(display, BorderLayout.CENTER);

		this.setSize(1620, 1200);
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(true);

		// Icone de la fenï¿½tre
		Image icon = Toolkit.getDefaultToolkit().getImage("img/reborn.png");
		this.setIconImage(icon);

		textField.requestFocusInWindow();
		textField.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void run() {

		manager.createMap();

		/*try {
			// Lances l'écoute de la musique de fond
			music.playMusicBackground("ambiance");
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		while (turn == true) {

			// GameOver Frame (numbers of heart == 0)
			if (manager.getReborn().getNbCoeurs() == 0) {
				turn = false;
				
				//Lancement de la frame de défaite
				initFrameGameOver();
				
				//On coupe la musique de fond
				music.stopMusicBackGround();
			}
			try {
				Thread.sleep(Config.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			manager.moveEnemies();
			display.repaint();
			updateValues();
			try {
				Thread.sleep(Config.GAME_SPEED - Config.GAME_SPEED);
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
			case '7':
				manager.cheatKillAllReborn();
				music.upDateMusic("bazooka");

				break;
			case '8':
				manager.cheatInvincibleReborn();

				break;
			case 'k':
				if (manager.getReborn().getHasSword()) {
					manager.attackReborn();
				}
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
		if (e.getSource() == (boutonFermeture)) {
			System.exit(0);// Fermeture du JEU
		}
	}

	public void updateHP() {

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

	public void initFrameGameOver() {

		this.setVisible(false);
		gameOver.setVisible(true);
		gameOver.setSize(1280, 720);
		gameOver.addWindowListener(new Closing());

		//////////////////////////////// FENETRE DE FIN ////////////////////////////////
		boutonFermeture = new JButton(gameOverPicture);
		boutonFermeture.addActionListener(this);

		music.stopMusicBackGround();
		gameOver.add(boutonFermeture);
		music.stopMusicBackGround();

	}
		
	public void updateInventory() {
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

		if (manager.getReborn().getHasDragonBall()) {
			dragonBallInventaire.setVisible(true);
		} else {
			dragonBallInventaire.setVisible(false);
		}
	}

	public void updateValues() {
		updateHP();
		updateInventory();
	}
	
	private class Closing extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}

}
