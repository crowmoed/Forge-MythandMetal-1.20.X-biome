package net.pinto.mythandmetal.worldgen.biome.surface;

import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomFeatures {


    public static void addPlainVegetation(BiomeGenerationSettings.Builder pBuilder) {
        pBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModVegatationPlacements.ASH_TREES);
    }
}
