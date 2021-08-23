package com.github.professorSam.trollmode.commands.trollmode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.main.Main;

public class Arrow {
	private static HashMap<Player, Boolean> arrowPlayers = new HashMap<Player, Boolean>();
	private static List<Player> arrowed = new ArrayList<Player>();

	// set a player to freeze Mode
	public static void setPlayerArrowed(Player player, Boolean arrowMode) {
		arrowPlayers.replace(player, arrowMode);
	}

	// checks if a Player is in freeze Mode
	public static Boolean isPlayerArrowed(Player player) {
		return arrowPlayers.get(player);
	}

	// register a Player on for example Join event with default to false
	public static void registerArrowPlayer(Player player) {
		arrowPlayers.put(player, false);
	}
	public static void toggleArrow(Player player) {
		if(isPlayerArrowed(player) == true) {
			setPlayerArrowed(player, false);
			arrowed.remove(player);
		}
		else {
			setPlayerArrowed(player, true);
			arrowed.add(player);
			
		}
			
	}
	public static void initArrow() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
			
			@Override
			public void run() {
				for(Player players : arrowed) {
					Location playersLoc = players.getLocation();
					playersLoc.add(0, 6, 0);
				    players.getWorld().spawnEntity(playersLoc, EntityType.ARROW);
				}
				
			}
		}, 0, 20);
	}
}
