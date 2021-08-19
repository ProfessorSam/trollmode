package com.github.professorSam.trollmode.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.professorSam.trollmode.commands.Trollmode;

public class Main extends JavaPlugin{
	
	private Main plugin;
	private String prefix;
	
	@Override
	public void onEnable() {
		getLogger().info("Trollmode wird geladen!");
		plugin = this;
		plugin.saveDefaultConfig();
		plugin.saveConfig();
		prefix = getConfig().getString("Settings.Prefix");
		
		this.getCommand("trollmode").setExecutor(new Trollmode());
		getLogger().info("Trollmode erfolgreich geladen!");
	}

	public Main getPlugin() {
		return plugin;
	}

	public String getPrefix() {
		return prefix;
	}
}
