package net.pinto.mythandmetal.worldgen.biome;


import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.pinto.mythandmetal.MythandMetal;
import net.pinto.mythandmetal.block.ModBlocks;
import net.pinto.mythandmetal.worldgen.CustomTreeFeature;

import java.util.List;

public class ModPlacedFeatures {


    public static final ResourceKey<PlacedFeature> ASH_PLACED_KEY = registerKey("ash_placed");
    public static final ResourceKey<PlacedFeature> ASH_TREES = PlacementUtils.createKey("ash_tree");
    private static final PlacementModifier TREE_THRESHOLD = SurfaceWaterDepthFilter.forMaxDepth(0);

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);


        register(context, ASH_PLACED_KEY, configuredFeatures.getOrThrow(CustomTreeFeature.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.ASH_SAPLING.get()));


        HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        Holder<ConfiguredFeature<?, ?>> holder = holdergetter.getOrThrow(CustomTreeFeature.ASH_KEY);
        PlacementModifier placementmodifier = SurfaceWaterDepthFilter.forMaxDepth(0);
        PlacementUtils.register(context, ASH_TREES, holder, PlacementUtils.countExtra(0, 0.05F, 1), InSquarePlacement.spread(), placementmodifier,
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(ModBlocks.ASH_SAPLING.get().defaultBlockState(), BlockPos.ZERO)), BiomeFilter.biome());

    }
    private static ImmutableList.Builder<PlacementModifier> treePlacementBase(PlacementModifier pPlacement) {
        return ImmutableList.<PlacementModifier>builder().add(pPlacement).add(InSquarePlacement.spread()).add(TREE_THRESHOLD).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR).add(BiomeFilter.biome());
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement) {
        return treePlacementBase(pPlacement).build();
    }

    public static List<PlacementModifier> treePlacement(PlacementModifier pPlacement, Block pSaplingBlock) {
        return treePlacementBase(pPlacement).add(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(pSaplingBlock.defaultBlockState(), BlockPos.ZERO))).build();
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MythandMetal.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}