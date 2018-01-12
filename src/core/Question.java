package core;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Question {

	private ArrayList<String> dico = new ArrayList<String>();
	private String reponse;
	private int score = 0;
	private Player ply;
	private Action action = null;

	public Question(String words[], Action a) {
		for (String w : words) {
			dico.add(w);
		}
		this.action = a;
	}

	public Question(String words[], String rep) {
		// Contient les mots pour trouver la question
		for (String w : words) {
			dico.add(w);
		}
		this.setReponse(rep);
	}

	public void setDico(ArrayList<String> dico) {
		this.dico = dico;
	}

	public ArrayList<String> getDico() {
		return dico;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public Player getPly() {
		return ply;
	}

	public void setPly(Player ply) {
		this.ply = ply;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
