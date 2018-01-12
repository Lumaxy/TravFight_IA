package core;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	ConsoleCommandSender console;
	private static IA bigBro;

	public void onEnable() {
		console = Bukkit.getServer().getConsoleSender();
		console.sendMessage("[BigBrother] Plugin Start !");

		bigBro = new IA();

		// Chargement des commandes
		// getCommand("seeInv").setExecutor(new CMD_SeeInv());

		// Listener
		Listener l = new PluginListener();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(l, this);
	}

	public void onDisable() {
		bigBro.annonceTimer.cancel();
		bigBro = null;
		console.sendMessage("[BigBrother] Plugin stop.");
	}

	public static IA getIA() {
		return bigBro;
	}

	public static void setIA(IA bigBro) {
		Main.bigBro = bigBro;
	}

}
