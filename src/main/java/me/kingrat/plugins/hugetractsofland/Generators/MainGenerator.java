package me.kingrat.plugins.hugetractsofland.Generators;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.Material;

public class MainGenerator extends ChunkGenerator {
	private final Material top;
	private final Material middle;
	private final Material bottom;

	public MainGenerator(Material top, Material middle, Material bottom) {
		this.top = top;
		this.middle = middle;
		this.bottom = bottom;
	}

	@Override
	public boolean canSpawn(World world, int x, int z) {
		return true;
	}

	private static int xyzToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}

	@Override
	public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
		byte[] result = new byte[32768];

		for (int y = 1; y >= 0; y--) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					// TODO: Make this use the materials from the constructor.
					result[xyzToByte(x, y, z)] = (byte) Material.BEDROCK.getId();
				}
			}
		}
		return result;
	}
}
