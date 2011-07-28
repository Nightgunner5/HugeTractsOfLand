package me.kingrat.plugins.hugetractsofland.Generators;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import me.kingrat.plugins.hugetractsofland.HugeTractsOfLand;
import org.bukkit.Material;

public class MainGenerator extends ChunkGenerator {

    private byte[] result;
    
    public int getRandom() {
        return ((int) (Math.random() * 3) - 1) * 15;
    }
    
    @Override
    public boolean canSpawn(World world, int x, int z) {
        double xX = Math.random()*x;
        double zZ = Math.random()*z;
        if(xX>zZ)
        return true;
        else
        return false;
    }
    
    public int xyzToByte(int x, int y, int z) {
        return (x * 16 + z) * 128 + y;
    }
    
    @Override
    public byte[] generate(World world, Random rand, int chunkx, int chunkz) {
        result = new byte[32768];

        for (int y = 1; y >= 0; y--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    result[xyzToByte(x, y, z)] = (byte) Material.BEDROCK.getId();
                }
            }
        }
    return result;
    }
}
