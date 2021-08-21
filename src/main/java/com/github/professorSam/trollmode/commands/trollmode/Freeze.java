package com.github.professorSam.trollmode.commands.trollmode;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Freeze {
	private static HashMap<Player, Boolean> freeze = new HashMap<Player, Boolean>();
	
	//set a player to freeze Mode
	public static void setPlayerFreeze(Player player, Boolean freezing) {
		freeze.replace(player, freezing);
	}
	//checks if a Player is in freeze Mode
	public static Boolean isPlayerFreeze(Player player) {
		return freeze.get(player);
	}
	//register a Player on for example Join event with default to false
	public static void registerFreezePlayer(Player player) {
		freeze.put(player, false);
	}
}
