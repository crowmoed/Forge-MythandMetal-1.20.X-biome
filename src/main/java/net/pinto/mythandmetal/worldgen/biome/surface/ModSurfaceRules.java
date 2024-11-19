package net.pinto.mythandmetal.worldgen.biome.surface;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.pinto.mythandmetal.block.ModBlocks;
import net.pinto.mythandmetal.worldgen.biome.ModBiomes;

public class ModSurfaceRules {


    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ModBlocks.ASH_GRASS.get());


    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);


        return SurfaceRules.sequence(

                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.ENCHANTED_FOREST),
                    SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
                    )
        ));
    }






    private static  SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }










}
