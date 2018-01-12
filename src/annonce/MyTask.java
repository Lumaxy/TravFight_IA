package annonce;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import core.IA;

public class MyTask extends TimerTask {

	private ArrayList<String> dico = new ArrayList<String>();

	public MyTask(ArrayList<String> al) {
		this.dico = al;
	}

	@Override
	public void run() {
		String msg = dico.get(rand(0, dico.size() - 1));
		for (Player ply : Bukkit.getOnlinePlayers()) {
			ply.sendMessage(IA.getNameF() + msg);
		}

	}

	public int rand(int min, int max) {
		Random rand = new Random();

		return rand.nextInt(max - min + 1) + min;
	}

}
