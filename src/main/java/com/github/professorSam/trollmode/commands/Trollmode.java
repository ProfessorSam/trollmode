package com.github.professorSam.trollmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.commands.trollmode.Freeze;
import com.github.professorSam.trollmode.commands.trollmode.Vanish;
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
						player.sendMessage(Main.getPrefix() + "§e---Trollmode hilfe---");
						player.sendMessage(Main.getPrefix() + "§9/trollmode vanish §7- Setzt dich in den Vanish");
						player.sendMessage(Main.getPrefix() + "§9/trollmode unvanish §7- Hohlt dich aus dem Vanish!");
						player.sendMessage(Main.getPrefix() + "§9/trollmode freeze [Spieler] §7- Lässt einen Spieler sich nicht mehr bewegen");
						player.sendMessage(Main.getPrefix() + "§9/trollmode explode [Spieler] §7- Jagt einen Spieler in die Luft");
						player.sendMessage(Main.getPrefix() + "§9/trollmode jain [Spieler] §7- Sperrt einen Spieler in ein Bedrock Käfig");
						player.sendMessage(Main.getPrefix() + "§9/trollmode control [Spieler] §7- Kontroliere einen Spieler");
						player.sendMessage(Main.getPrefix() + "§e---Trollmode hilfe---");
					}
					else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("vanish")) {
							Vanish.setVanish(player);
							player.sendMessage(Main.getPrefix() + "§cDu bist nun im Vanish! Schalte es aus mit /trollmode unvanish");
						}
					}
					else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("unvanish")) {
							Vanish.removeVanish(player);
							player.sendMessage(Main.getPrefix() + "§cDu befindest dich nun nicht mehr im Vansish!");
						}
					}
					else if(args.length == 2) {
						if(args[0].equalsIgnoreCase("freeze")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toFreeze = Bukkit.getPlayer(args[1]);
								if(Freeze.isPlayerFreeze(toFreeze) == false) {
									Freeze.setPlayerFreeze(toFreeze, true);
									player.sendMessage(Main.getPrefix() + toFreeze.getDisplayName() + " §cwurde eingefroren!");
									toFreeze.sendMessage(Main.getPrefix() + "§cDu wurdest eingefroren und kannst dich nicht mehr Bewegen!");
								}
								else {
									Freeze.setPlayerFreeze(toFreeze, false);
									player.sendMessage(Main.getPrefix() + toFreeze.getDisplayName() + "§4 wurde aufgetaut!");
									toFreeze.sendMessage(Main.getPrefix() + "§4Du kannst dich wieder bewegen!");
								}
									
							}
							else {
								player.sendMessage(Main.getPrefix() + "§cDieser Spieler ist nicht online!");
							}
						}
					}
					
				} 
				//set the Trollmode to true and send Some messages, Titles and Sounds
				else {
					Main.setTrollmodeActive(true);
					for(Player players : Bukkit.getOnlinePlayers()) {
						players.sendTitle("§4Trollmode", "§cTrollmode wurde aktiviert");
						players.playSound(player.getLocation(), Sound.GHAST_SCREAM2, 2F, 1F);
						players.sendMessage(Main.getPrefix() + "§4Der Trollmode ist nun aktiviert!");
						Freeze.registerFreezePlayer(players);
						player.setGameMode(GameMode.CREATIVE);
					}
				}
			}
		}
		return false;
	}
}
