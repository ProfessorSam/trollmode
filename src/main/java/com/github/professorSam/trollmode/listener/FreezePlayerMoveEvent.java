package com.github.professorSam.trollmode.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.professorSam.trollmode.commands.trollmode.Freeze;
import com.github.professorSam.trollmode.main.Main;

public class FreezePlayerMoveEvent implements Listener {
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		if (Main.isTrollmodeActive() == true) {
			if (Freeze.isPlayerFreeze(event.getPlayer()) == true) {
				Location from = event.getFrom();
				Location to = event.getTo();
				double x = Math.floor(from.getX());
				double z = Math.floor(from.getZ());
				if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
					x += .5;
					z += .5;
					event.getPlayer()
							.teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));

				}
			}
		}
	}
}
