package com.github.professorSam.trollmode.commands.trollmode;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.main.Main;

public class Tpall {
	public static void tpall(Player player) {
		for(Player players : Bukkit.getServer().getOnlinePlayers()) {
			if(player == players) {
				continue;
			}
			players.teleport(player);
			players.sendMessage(Main.getPrefix() + "§cDu wurdest zum Troller teleportiert!");
			players.playSound(players.getLocation(), Sound.ENDERMAN_TELEPORT, 1.5F, 0.4F);
		}
	}
}
