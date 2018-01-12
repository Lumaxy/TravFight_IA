package core;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PluginListener implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		boolean send = true;
		Main.getIA();
		Player p = e.getPlayer();
		String msg = e.getMessage();
		ArrayList<String> words = new ArrayList<String>();
		for (String w : msg.split(" ")) {
			words.add(w);
		}
		// Test des mots interdit

		for (String w : words) {
			for (String str : IA.getBadWords()) {
				if (w.equalsIgnoreCase(str)) {
					p.sendMessage(IA.getNameF() + "C'est quoi ce vocabulaire !");
					e.setCancelled(true);
					send = false;
				}
			}
		}
		if (send) {
			for (String name : IA.getName()) {
				for (String w : words) {
					if (w.equalsIgnoreCase(name)) {
						// Le joueur a prononcer son nom
						Main.getIA().analyze(words, p);
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
