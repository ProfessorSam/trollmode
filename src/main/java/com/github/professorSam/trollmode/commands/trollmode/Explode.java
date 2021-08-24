package com.github.professorSam.trollmode.commands.trollmode;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.main.Main;

public class Explode {
	int taskID;
	private void explodePlayer(Player player) {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			int i = 0;
			@Override
			public void run() {
				i++;
				if(i == 1) {
					player.sendMessage(Main.getPrefix() + "§cDu explodierst in §e3 §cSekunden!");
					player.playSound(player.getLocation(), Sound.NOTE_PLING, 2, 2.3F);
				}
				else if(i == 2) {
					player.sendMessage(Main.getPrefix() + "§cDu explodierst in §e2 §cSekunden!");
					player.playSound(player.getLocation(), Sound.NOTE_PLING, 2, 1.3F);
				}
				else if(i == 3) {
					player.sendMessage(Main.getPrefix() + "§cDu explodierst in §e1§cSekunde!");
					player.playSound(player.getLocation(), Sound.NOTE_PLING, 3, 0.8F);
				}
				else {
					player.sendMessage(Main.getPrefix() + "§cDu explodierst §4JETZT§c!");
					player.playSound(player.getLocation(), Sound.EXPLODE, 6, 1);
					player.getWorld().createExplosion(player.getLocation(), 6, true);
					Bukkit.getScheduler().cancelTask(taskID);
				}
				
			}
		}, 0, 20);
	}
	public Explode(Player player) {
		explodePlayer(player);
	}
}
