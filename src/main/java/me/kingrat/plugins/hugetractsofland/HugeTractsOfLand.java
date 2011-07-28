package me.kingrat.plugins.hugetractsofland;

import java.util.List;
import me.kingrat.plugins.hugetractsofland.Generators.MainGenerator;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;
import org.bukkit.util.config.ConfigurationNode;

public class HugeTractsOfLand extends JavaPlugin {
	private static final String configHeader =
			"# HugeTractsOfLand Configuration!\r\n" +
			"#\r\n" +
			"# This file manages all worlds. You can define a new world easily.\r\n" +
			"# To make a world generate, make sure you set 'ready' to true.\r\n";

	@Override
	public void onDisable() {
		System.out.println(this + " is now disabled!");
	}

	@Override
	public void onEnable() {
		if (!getDataFolder().exists()) {
			makeConfig();
		}

		System.out.println(this + " is now enabled!");
		List<String> worlds = getConfiguration().getKeys("worlds");

		for (String world : worlds) {
			if (getServer().getWorld(world) == null) {
				getServer().createWorld(world, Environment.valueOf(getConfiguration()
						.getString("worlds." + world + ".worldtype", "NORMAL")),
											   getDefaultWorldGenerator(world, ""));
			}
		}
	}

	public void makeConfig() {
		try {
			Configuration config = getConfiguration();
			config.setHeader(configHeader);
			config.setProperty("worlds.flatworld.worldtype", "NORMAL");
			config.setProperty("worlds.flatworld.material1", "GRASS");
			config.setProperty("worlds.flatworld.material2", "DIRT");
			config.setProperty("worlds.flatworld.material3", "STONE");
//			config.setProperty("worlds.flatworld.ready", false);
			config.save();
		} catch (IllegalArgumentException ex) {
			System.out.println(this + " failed to initiate config. Check for errors!");
		}
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
		ConfigurationNode node = getConfiguration().getNode("worlds." + worldName);
		if (node == null) {
			return null;
		}

		Material top = Material.getMaterial(node.getString("material1"));
		Material middle = Material.getMaterial(node.getString("material2"));
		Material bottom = Material.getMaterial(node.getString("material3"));

		return new MainGenerator(top, middle, bottom);
	}
}
