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
import objet.Epee;
import process.ElementManager;
import process.GameBuilder;

public class Window extends JFrame implements Runnable, ActionListener {

	private Display display;
	private int lockKeys = -1;
	private Map map;
	private ElementManager manager;

	private boolean turn = true; // variable qui permet de faire tourner le programme ou non (thread)

	
	private JPanel infobar = new JPanel();
	private JPanel coeurPanel =new JPanel();
	
	
	
	ImageIcon coeur = new ImageIcon(new ImageIcon("coeur.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	
	
	// Cinq coeurs de vie du personnage principal
	JLabel coeurLabel = new JLabel(coeur);
	JLabel coeurLabelDeux = new JLabel(coeur);
	JLabel coeurLabelTrois = new JLabel(coeur);
	JLabel coeurLabelQuatre = new JLabel(coeur);
	JLabel coeurLabelCinq = new JLabel(coeur);
	
	private List<Integer> coeurList = new ArrayList<Integer>(); 
	
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
		
		infobar.setPreferredSize(new Dimension(Config.tailleBlock*Config.nbColumns,5*Config.tailleBlock));
		infobar.setBackground(Color.black);
		
		// commentaires ci-dessous très important pour la suite du dév
		//coeurLabel.setHorizontalAlignment(SwingConstants.LEFT); // Places les coeurs à gauche de la barre d'information
		//coeurLabelDeux.setHorizontalAlignment(SwingConstants.LEFT);
		infobar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Coeurs codé en dur, à mettre en liste par la suite
		infobar.add(coeurLabel);
		infobar.add(coeurLabelDeux);
		infobar.add(coeurLabelTrois);
		infobar.add(coeurLabelQuatre);
		infobar.add(coeurLabelCinq);
		
			
		contentPane.add(infobar, BorderLayout.NORTH);
		
		contentPane.add(display, BorderLayout.CENTER);

		this.setSize(1600, 1200);	
		this.setVisible(true);
		this.setLayout(null);
		this.setResizable(true);
		
		// Icone de la fenêtre
		Image icon = Toolkit.getDefaultToolkit().getImage("reborn.png");
		this.setIconImage(icon);
		
		textField.requestFocusInWindow();
		textField.setFocusable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void run() {
		Config.generation = 1;
		int génération = 0;
		
		// Génération de la map qu'une seule fois
		while(génération==0){
			
			manager.createMap();
			génération++;
		}

		while (turn == true) {
			
			try {
				Thread.sleep(Config.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			display.repaint();

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

}
