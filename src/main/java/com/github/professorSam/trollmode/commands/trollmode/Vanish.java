package com.github.professorSam.trollmode.commands.trollmode;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Vanish {
	public static void setVanish(Player player) {
		PotionEffect invisible = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 250, false, false);
		player.addPotionEffect(invisible);
	}

	public static void removeVanish(Player player) {
		 player.removePotionEffect(PotionEffectType.INVISIBILITY);
	}
}
