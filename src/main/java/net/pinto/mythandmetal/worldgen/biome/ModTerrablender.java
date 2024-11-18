package net.pinto.mythandmetal.worldgen.biome;

import net.minecraft.resources.ResourceLocation;
import net.pinto.mythandmetal.MythandMetal;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(new ResourceLocation(MythandMetal.MOD_ID,"overworld_1"),5));
    }
}
