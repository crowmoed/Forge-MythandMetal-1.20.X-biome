package net.pinto.mythandmetal.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.pinto.mythandmetal.MythandMetal;
import net.pinto.mythandmetal.block.ModBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModConfigureFeatures
{
    public static final ResourceKey<ConfiguredFeature<?,?>> ASH_KEY = registerkey("ash");

    private static final Logger LOGGER = LoggerFactory.getLogger(ModConfigureFeatures.class);


    public static void bootstrap (BootstapContext<ConfiguredFeature<?,?>>context){


        register (context, ASH_KEY,Feature.TREE,new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(5,4,3),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(3),ConstantInt.of(2),3),
                new TwoLayersFeatureSize(1,0,2)).build());




    }
    public static ResourceKey<ConfiguredFeature<?,?>> registerkey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(MythandMetal.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register (BootstapContext<ConfiguredFeature<?,?>> context,
                                                                                           ResourceKey<ConfiguredFeature<?,?>> key, F feature, FC configuration){

        context.register(key, new ConfiguredFeature<>(feature , configuration));
    }
}
