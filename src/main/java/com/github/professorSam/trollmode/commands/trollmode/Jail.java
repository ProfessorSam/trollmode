package com.github.professorSam.trollmode.commands.trollmode;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Jail {
	public static void toggleJail(Player player, Boolean free) {
		teleportToMiddle(player);
		Location playerLoc = player.getLocation();
		World world = playerLoc.getWorld();
		double x = playerLoc.getX();
		double y = playerLoc.getY();
		double z = playerLoc.getZ();
		Location floor = new Location(world, x, y-1, z);
		Location top = new Location(world, x, y+2, z);
		Location x1 = new Location(world, x+1, y, z);
		Location x2 = new Location(world, x-1, y, z);
		Location z1 = new Location(world, x, y, z-1);
		Location z2 = new Location(world, x, y, z+1);
		placeBlocks(floor, free);
		placeBlocks(top, free);
		placeBlocks(x1, free);
		placeBlocks(x2, free);
		placeBlocks(z1, free);
		placeBlocks(z2, free);
	}
	private static void placeBlocks(Location location, Boolean free){
		if(free == true) {
			location.getWorld().getBlockAt(location).setType(Material.AIR);
		}
		else
			location.getWorld().getBlockAt(location).setType(Material.BEDROCK);
	}
	private static void teleportToMiddle(Player player) {
		Location loc = player.getLocation();
		int ix = (int) loc.getX();
		int iy = (int) loc.getY();
		int iz = (int) loc.getZ();
		double x = ix + 0.5;
		double y = iy + 0.5;
		double z = iz + 0.5;
		Location toTeleport = new Location(player.getWorld(), x, y, z);
		player.teleport(toTeleport);
	}
}
