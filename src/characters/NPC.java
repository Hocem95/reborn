package characters;

import map.Block;
import objet.Element;

public class NPC extends Element{
	
	String nom;
	
	//Quête finie ou non
	private boolean questFinish=false;
	
	//Quête prit par l'utilisateur
	private boolean questTake=false;
	
	//Contenu de la quête
	private  String quest= "";
	
	public NPC(Block position) {
		super(position);
	}
	
	public boolean isQuestFinish() {
		return questFinish;
	}
	
	public void setQuestFinish(boolean questFinish) {
		this.questFinish = questFinish;
	}
	
	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}

	public boolean isQuestTake() {
		return questTake;
	}

	public void setQuestTake(boolean questTake) {
		this.questTake = questTake;
	}
}
