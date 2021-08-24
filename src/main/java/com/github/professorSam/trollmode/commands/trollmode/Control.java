package com.github.professorSam.trollmode.commands.trollmode;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.main.Main;

public class Control {
	public static HashMap<Player, Boolean> controledPlayer = new HashMap<Player, Boolean>();
	private static int taskID;
	//set a player to controle Mode
	private static void setPlayerControled(Player player, Boolean freezing) {
	controledPlayer.replace(player, freezing);
	}
	//checks if a Player is in Controle Mode
	private static Boolean isPlayerControled(Player player) {
		return controledPlayer.get(player);
	}
	//register a Player on for example Join event with default to false
	public static void registerControlPlayer(Player player) {
		controledPlayer.put(player, false);
	}
	public static void togglePlayerControl(Player toControl, Player controler) {
		controler.teleport(toControl.getLocation());
		if(toControl == controler) {
			controler.sendMessage(Main.getPrefix() + "§cDu kannst dich nicht selber steuern");
		} else {
			if(isPlayerControled(toControl) == true) {
				setPlayerControled(toControl, false);
				Bukkit.getScheduler().cancelTask(taskID);
				controler.sendMessage(Main.getPrefix() + "§cDu kontrolierst nun diesen Spieler nicht mehr!");
				Vanish.removeVanish(controler);
				controler.showPlayer(toControl);
			}
			else {
				Vanish.setVanish(controler);
				controler.hidePlayer(toControl);
				setPlayerControled(toControl, true);
				controler.sendMessage(Main.getPrefix() + "§cDu kontrolierst nun diesen Spieler!");
				taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new  Runnable() {
					
					@Override
					public void run() {
						if(isPlayerControled(toControl) == true) {
							toControl.teleport(controler.getLocation());
						}
					}
				}, 0, 1);
			}	
		}

	}
	
	
}
