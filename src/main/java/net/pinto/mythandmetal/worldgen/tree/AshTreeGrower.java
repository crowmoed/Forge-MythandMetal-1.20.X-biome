package net.pinto.mythandmetal.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.fml.common.Mod;
import net.pinto.mythandmetal.worldgen.ModConfigureFeatures;
import org.jetbrains.annotations.Nullable;

public class AshTreeGrower extends AbstractTreeGrower {


    @Override
    protected  ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        System.out.println("Trying to grow a tree!");
        return ModConfigureFeatures.ASH_KEY;

    }
}
