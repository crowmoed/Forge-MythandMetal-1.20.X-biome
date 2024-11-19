package net.pinto.mythandmetal.worldgen.biome.surface;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.pinto.mythandmetal.block.ModBlocks;
import net.pinto.mythandmetal.worldgen.CustomTreeFeature;

import java.util.List;

public class ModVegatationPlacements {

    public static final ResourceKey<PlacedFeature> ASH_TREES = PlacementUtils.createKey("ash_placed");
    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier pPlacement) {
        return ImmutableList.<PlacementModifier>builder().add(pPlacement).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement) {
        return treePlacementBase(pPlacement).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement, Block pSaplingBlock) {
        return treePlacementBase(pPlacement).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(pSaplingBlock.defaultBlockState(), BlockPos.ZERO))).build();
    }

    public static void bootstrap(BootstapContext<PlacedFeature> pContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = pContext.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> holder = holdergetter.getOrThrow(CustomTreeFeature.ASH_KEY);
        PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
        PlacementUtils.register(pContext, ASH_TREES, holder, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), placementmodifier, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.ASH_SAPLING.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());
    }
}
