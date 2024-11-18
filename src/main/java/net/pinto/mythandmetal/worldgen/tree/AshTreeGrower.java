package net.pinto.mythandmetal.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.pinto.mythandmetal.worldgen.CustomTreeFeature;
import net.pinto.mythandmetal.worldgen.CustomTreeFeature;
import org.jetbrains.annotations.Nullable;

public class AshTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        System.out.println("Killing myself one sec");
        return CustomTreeFeature.ASH_KEY;
    }
}
