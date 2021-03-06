package com.github.professorSam.trollmode.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.github.professorSam.trollmode.commands.trollmode.Arrow;
import com.github.professorSam.trollmode.commands.trollmode.Control;
import com.github.professorSam.trollmode.commands.trollmode.Explode;
import com.github.professorSam.trollmode.commands.trollmode.Freeze;
import com.github.professorSam.trollmode.commands.trollmode.Jail;
import com.github.professorSam.trollmode.commands.trollmode.Tpall;
import com.github.professorSam.trollmode.commands.trollmode.Vanish;
import com.github.professorSam.trollmode.main.Main;

public class Trollmode implements CommandExecutor{
	String notActive = "§cDiese Funktion ist auf diesem Server nicht verfügbar!";
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
						player.sendMessage(Main.getPrefix() + "§9/trollmode jail [Spieler] §7- Sperrt einen Spieler in ein Bedrock Käfig");
						player.sendMessage(Main.getPrefix() + "§9/trollmode unjail [Spieler] §7- Befreit einen Spieler aus einen Käfig");
						player.sendMessage(Main.getPrefix() + "§9/trollmode control [Spieler] §7- Kontroliere einen Spieler");
						player.sendMessage(Main.getPrefix() + "§9/trollmode arrow [Spieler] §7- Spawnt jede Sekunde einen Pfeil über den Spieler");
						player.sendMessage(Main.getPrefix() + "§9/trollmode tpall §7- Teleportiert alle Spieler zu dir");
						player.sendMessage(Main.getPrefix() + "§e---Trollmode by ProfessorSam---");
						return true;
					}
					else if(args.length == 1 && args[0].equalsIgnoreCase("vanish")) {
						if(isActive("Vanish")) {
							Vanish.setVanish(player);
							player.sendMessage(Main.getPrefix() + "§cDu bist nun im Vanish! Schalte es aus mit /trollmode unvanish");
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 1 && args[0].equalsIgnoreCase("unvanish")) {
						if(isActive("Vanish")) {
							Vanish.removeVanish(player);
							player.sendMessage(Main.getPrefix() + "§cDu befindest dich nun nicht mehr im Vansish!");
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 2 && args[0].equalsIgnoreCase("freeze")) {
						if(isActive("Freeze")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toFreeze = Bukkit.getPlayer(args[1]);
								if(Freeze.isPlayerFreeze(toFreeze) == false) {
									Freeze.setPlayerFreeze(toFreeze, true);
									player.sendMessage(Main.getPrefix() + toFreeze.getDisplayName() + " §cwurde eingefroren!");
									toFreeze.sendMessage(Main.getPrefix() + "§cDu wurdest eingefroren und kannst dich nicht mehr Bewegen!");
								}
								else {
									Freeze.setPlayerFreeze(toFreeze, false);
									player.sendMessage(Main.getPrefix() + toFreeze.getDisplayName() + "§c wurde aufgetaut!");
									toFreeze.sendMessage(Main.getPrefix() + "§cDu kannst dich wieder bewegen!");
								}
									
							}
							else {
								player.sendMessage(Main.getPrefix() + "§cDieser Spieler ist nicht online!");
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
						
						
					}
					else if(args.length == 2 && args[0].equalsIgnoreCase("explode")) {
						if(isActive("Explode")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toExplode = Bukkit.getPlayer(args[1]);
								
								new Explode(toExplode);
								player.sendMessage(Main.getPrefix() + "§cDer Spieler wird hochgejagt");
									
							}
							else {
								player.sendMessage(Main.getPrefix() + "§cDieser Spieler ist nicht online!");
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
						}
					else if(args.length == 2 && args[0].equalsIgnoreCase("jail")) {
						if(isActive("Jail")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toJail = Bukkit.getPlayer(args[1]);
								Jail.toggleJail(toJail, false);
								player.sendMessage(Main.getPrefix() + "§cDer Spieler wird in einen Käfig gesteckt");
								toJail.sendMessage(Main.getPrefix() + "§cDu wurdest in einen Käfig gesteckt");
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 2 && args[0].equalsIgnoreCase("unjail")) {
						if(isActive("Jail")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toUnJail = Bukkit.getPlayer(args[1]);
								
								Jail.toggleJail(toUnJail, true);
								player.sendMessage(Main.getPrefix() + "§cDer Spieler ist befreit!");
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 2 && args[0].equalsIgnoreCase("control")) {
						if(isActive("Control")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toControl = Bukkit.getPlayer(args[1]);
								Control.togglePlayerControl(toControl, player);
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 1 && args[0].equalsIgnoreCase("tpall")) {
						if(isActive("Tpall")) {
							player.sendMessage(Main.getPrefix() + "§cEs wurden alle Spieler zu dir teleportiert");
							Tpall.tpall(player);
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else if(args.length == 2 && args[0].equalsIgnoreCase("arrow")) {
						if(isActive("Arrow")) {
							if(Bukkit.getPlayerExact(args[1]) != null) {
								Player toArrow = Bukkit.getPlayer(args[1]);
								Arrow.toggleArrow(toArrow);
								player.sendMessage(Main.getPrefix() + "§cDer Spieler wird nun mit Pfeilen abgeschossen!");
							}
						}
						else
							player.sendMessage(Main.getPrefix() + notActive);
					}
					else {
						player.sendMessage(Main.getPrefix() + "§cDieser Sub-Command existiert nicht oder der Spieler ist nicht online!");
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
						Control.registerControlPlayer(players);
						Arrow.registerArrowPlayer(players);
						
					}
					Arrow.initArrow();
					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage(Main.getPrefix() + "§cDu kannst mit §7/trollmode §calle Subcommands dir ansehen!");
				}
			}
		}
		return false;
	}
	private Boolean isActive(String subcommand) {
		FileConfiguration config = Main.getPlugin().getConfig();
		try {
			return config.getBoolean("Settings." + subcommand);
		}
		catch (Exception e) {
			return false;
		}
	}
}
