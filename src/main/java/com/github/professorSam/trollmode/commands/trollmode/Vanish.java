package com.github.professorSam.trollmode.commands.trollmode;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Vanish {
	public static void setVanish(Player player) {
		for (Player players : Bukkit.getServer().getOnlinePlayers()) {
			if (players == player) {
				continue;
			} else if (players.hasPermission("trollmode.use")) {
				players.sendMessage(player.getDisplayName() + " §cist im vanish");
				continue;
			}

			players.hidePlayer(player);
		}
	}

	public static void removeVanish(Player player) {
		 for (Player players : Bukkit.getServer().getOnlinePlayers()) {
			 players.showPlayer(player);
		 }    
	}
}
