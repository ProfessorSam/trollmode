package com.github.professorSam.trollmode.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.professorSam.trollmode.commands.trollmode.Arrow;
import com.github.professorSam.trollmode.commands.trollmode.Control;
import com.github.professorSam.trollmode.commands.trollmode.Freeze;
import com.github.professorSam.trollmode.main.Main;

public class RegisterPlayerOnJoin implements Listener {
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		if(Main.isTrollmodeActive() == true) {
			Freeze.registerFreezePlayer(event.getPlayer());
			Control.registerControlPlayer(event.getPlayer());
			Arrow.registerArrowPlayer(event.getPlayer());
		}
	}
}
