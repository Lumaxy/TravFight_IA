package core;

import java.util.ArrayList;
import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import annonce.MyTask;

public class IA {

	private ArrayList<Question> questionList = new ArrayList<Question>();
	private static ArrayList<String> iaName = new ArrayList<String>();
	private static ArrayList<String> badWords = new ArrayList<String>();
	private static ArrayList<String> annonce = new ArrayList<String>();

	public Timer annonceTimer;

	private final String SITE = "www.google.com";

	public IA() {
		iaName.add("Cloë");
		iaName.add("Cloe");
		iaName.add("Cloé");
		iaName.add("Chloé");
		iaName.add("Chloe");

		initQuestion();
		initBadWords();
		initAnnonce();

		/* Annonces */
		annonceTimer = new Timer();
		MyTask annonceTask = new MyTask(annonce);
		annonceTimer.schedule(annonceTask, 1, (long) 10 * 60 * 1000);
	}

	public String analyze(ArrayList<String> words, Player p) {
		String res = "";
		// compte les mots
		for (String w : words) {
			for (Question q : questionList) {
				for (String dico : q.getDico()) {
					if (dico.equalsIgnoreCase(w)) {
						q.setScore(q.getScore() + 1);
						System.out.println(q.getReponse() + " :: " + q.getScore());
					}
				}
			}
		}
		// On recupère la meilleure reponse
		Question best = questionList.get(0);
		for (Question q : questionList) {
			if (q.getScore() > best.getScore()) {
				best = q;
			} else if (q.getScore() == best.getScore()) {
				// Egalite
			}
		}
		// Reinitialise les score des questions
		for (Question q : questionList) {
			q.setScore(0);
		}
		// Test si la reponse est une phrase ou une action
		if (best.getAction() == null) {
			p.sendMessage(getNameF() + best.getReponse());
		} else {
			best.getAction().start(p);
		}

		return res;
	}

	public static ArrayList<String> getName() {
		return iaName;
	}

	public static String getNameF() {
		return ChatColor.LIGHT_PURPLE + iaName.get(0) + " " + ChatColor.WHITE + ": ";
	}

	public void initQuestion() {

		String tmp[] = new String[iaName.size()];
		int i = 0;
		for (String name : iaName) {
			tmp[i] = name;
			i++;
		}

		questionList.add(new Question(new String[] { "" }, "Je n'ai pas compris"));

		questionList.add(new Question(new String[] { "casque" }, new Action() {
			@Override
			public void start(Player p) {
				p.getInventory().setHelmet(new ItemStack(Material.ANVIL, 1));

			}
		}));

		questionList.add(new Question(new String[] { "sperm", "queue", "bite", "cul", "nichon", "seins" },
				"Si tu espères une interaction sexuelle avec moi, c'est mal parti."));

		questionList.add(new Question(new String[] { "rejoindre", "comment", "faction" },
				"Pour rejoindre une faction, il faut faire /join <NomFaction>."));

		questionList.add(new Question(new String[] { "pourquoi", "faction", "ne", "pas", "creer", "créer", "créé" },
				"Pour créer une faction il avoir suffisament de crédits. Pour rapporter une erreur au staff : "
						+ SITE));

		questionList.add(new Question(new String[] { "avoir", "argent" },
				"Pour gagner de l'argent, il faut vendre des ressources. Vous pouvez aussi gagner des events ou des mini-jeux!"));

		// TODO fin
		questionList.add(new Question(
				new String[] { "creer", "créer", "créé", "factions", "faction", "fonder", "nouvelle", "comment" },
				"Pour creer une faction il faut. Tiens voici le lien : " + SITE));

		questionList.add(new Question(new String[] { "etre", "être", "VIP", "devenir", "acheter" },
				"Tu peux devenir VIP ! Viens voir sur notre site : " + SITE));

		questionList.add(new Question(
				new String[] { "changer", "change", "changé", "grade", "améliorer", "ameliorer", "amélioré" },
				"Je n'ai pas compris"));

		// Relatif STAFF-----------------------------------------------
		// Layce
		questionList.add(new Question(new String[] { "Qui", "est", "fondateur", "serveur" },
				"Le fondateur du serveur est Layce17"));
		questionList.add(new Question(new String[] { "Qui", "est", "Layce17" },
				"Layce17 est le fondateur du serveur. (En plus il est sympa)"));

		// Lumix
		questionList.add(
				new Question(new String[] { "Qui", "est", "Lumix" }, "Lumix est mon créateur. (En plus il est cool)"));

		// Shynoni
		questionList.add(new Question(new String[] { "Qui", "est", "Shynoni" },
				"Shynoni est un admin-builder, homme à tout faire. (En plus il est drôle)"));

		// Relatif à l'IA------------------------------------
		questionList.add(new Question(new String[] { "Bonjour", "Salut", "Coucou", "yo", "Slt", "cc" },
				"Salut toi ! Pose moi une question en écrivant mon nom puis ta question !"));

		questionList.add(new Question(new String[] { "Qui", "est", "es", "quoi", "tu" }, "Je suis une IA"));

		questionList.add(new Question(new String[] { "quel", "est", "nom", "comment", "apelle" },
				"Je m'apelle " + iaName.get(0)));

		questionList.add(new Question(new String[] { "Qui", "créateur", "createur", "creer", "créer", "père", "pere" },
				"Lumix m'a créer"));

		// Doit etre la derniere
		questionList.add(new Question(tmp,
				"Oui c'est moi ! Je suis une intelligence artificielle conçue pour vous aider. (Exemple : Bonjour "
						+ iaName.get(0) + ")"));
	}

	public static ArrayList<String> getBadWords() {
		return badWords;
	}

	public void initBadWords() {
		badWords.add("pute");
		badWords.add("putain");
		badWords.add("merde");
		badWords.add("chier");
		badWords.add("connard");
		badWords.add("con");
		badWords.add("conasse");
		badWords.add("enculer");
		badWords.add("enculé");
		badWords.add("mortecouille");
		badWords.add("batard");
		badWords.add("gueule");
		badWords.add("ftg");
		badWords.add("tg");
		badWords.add("bite");
		badWords.add("sperm");
		badWords.add("nichon");
		badWords.add("couille");
		badWords.add("pets de cachalot");

	}

	public void initAnnonce() {
		annonce.add("Le serveur est encore en développement");
		annonce.add("Viens faire un tour sur la boutique ! " + SITE + "/boutique.php");
		annonce.add("Voici une annonce");

	}
}
