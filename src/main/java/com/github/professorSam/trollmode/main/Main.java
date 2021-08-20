package com.github.professorSam.trollmode.main;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.professorSam.trollmode.commands.Trollmode;

public class Main extends JavaPlugin{
	
	private Main plugin;
	private static String prefix;
	private static boolean trollmodeActive;
	
	@Override
	public void onEnable() {
		getLogger().info("Trollmode wird geladen!");
		plugin = this;
		plugin.saveDefaultConfig();
		plugin.saveConfig();
		prefix = getConfig().getString("Settings.Prefix");
		setTrollmodeActive(false);
		
		this.getCommand("trollmode").setExecutor(new Trollmode());
		getLogger().info("Trollmode erfolgreich geladen!");
	}

	public Main getPlugin() {
		return plugin;
	}

	public static String getPrefix() {
		return prefix;
	}

	public static boolean isTrollmodeActive() {
		return trollmodeActive;
	}

	public static void setTrollmodeActive(boolean trollmodeActive) {
		Main.trollmodeActive = trollmodeActive;
	}

}
