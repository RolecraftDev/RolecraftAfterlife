/*
 * This file is part of RolecraftAfterlife.
 *
 * Copyright (c) ${inceptionYear} RolecraftDev <http://rolecraftdev.github.com>
 * RolecraftCore is licensed under the Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-nc-nd/3.0
 *
 * As long as you follow the following terms, you are free to copy and redistribute
 * the material in any medium or format.
 *
 * You must give appropriate credit, provide a link to the license, and indicate
 * whether any changes were made to the material. You may do so in any reasonable
 * manner, but not in any way which suggests the licensor endorses you or your use.
 *
 * You may not use the material for commercial purposes.
 *
 * If you remix, transform, or build upon the material, you may not distribute the
 * modified material.
 *
 * You may not apply legal terms or technological measures that legally restrict
 * others from doing anything the license permits.
 *
 * DISCLAIMER: This is a human-readable summary of (and not a substitute for) the
 * license.
 */
package com.github.rolecraftdev.afterlife.hell;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

public class HellChunkGenerator extends ChunkGenerator {
private NoiseGenerator generator;
    
    public NoiseGenerator getGenerator(World world) {
        if (generator == null) {
            generator = new SimplexNoiseGenerator (world);
        }
        return generator;
    }
    
    private void setBlock(byte[][] rslt, int x, int y, int z, int blkid) {
        if(rslt[y >> 4] == null) {
            rslt[y >> 4] = new byte[4096];
        }
        rslt[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte)blkid;
    }
    
    @Override
    public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes) {
        byte[][] result = new byte[16][];
        
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int height = getHeight(world, cx + x * 0.0625, cz + z * 0.0625, 2) + 60;
                for (int y = 0; y < height; y++) {
                    setBlock(result, x, y, z, Material.SPONGE.getId());
                }
                biomes.setBiome(x, z, Biome.DESERT);
            }
        }

        return result;
    }
    
    private int getHeight(World world, double x, double y, int variance) {
        NoiseGenerator gen = getGenerator(world);

        double result = gen.noise(x, y);
        result *= variance;
        return NoiseGenerator.floor(result);
    }
    
    @Override
    public List<BlockPopulator> getDefaultPopulators (World world) {
        return Arrays.asList((BlockPopulator)new LavaLakePopulator());
    }
}
