package com.github.professorSam.trollmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.main.Main;

public class Trollmode implements CommandExecutor{
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("trollmode.use")) {
				//provide acces to the Trollcommand and subcommands
				if(Main.isTrollmodeActive()) {
					if(args.length == 0) {
						String[] help = new String[6];
						help[0] = "§e---Trollmode hilfe---";
						help[1] = "§9/trollmode vanish §7- Setzt dich in den vanish";
						help[2] = "§9/trollmode freeze [Spieler] §7- Lässt einen Spieler sich nicht mehr bewegen";
						help[3] = "§9/trollmode explode [Spieler] §7- Jagt einen Spieler in die Luft";
						help[4] = "§9/trollmode jain [Spieler] §7- Sperrt einen Spieler in ein Bedrock Käfig";
						help[5] = "§9/trollmode control [Spieler] §7- Kontroliere einen Spieler";
						help[6] = "§e---Trollmode hilfe---";
						for (int i = 0; i < help.length ; i++) {
							help[i] = Main.getPrefix() + help[i];
						}
						player.sendMessage(help);
					}
					
				} 
				//set the Trollmode to true and send Some messages, Titles and Sounds
				else {
					Main.setTrollmodeActive(true);
					for(Player players : Bukkit.getOnlinePlayers()) {
						players.sendTitle("§4Trollmode", "§cTrollmode wurde aktiviert");
						players.playSound(player.getLocation(), Sound.GHAST_SCREAM2, 2F, 1F);
						players.sendMessage(Main.getPrefix() + "§4Der Trollmode ist nun aktiviert!");
					}
				}
			}
		}
		return false;
	}
}
