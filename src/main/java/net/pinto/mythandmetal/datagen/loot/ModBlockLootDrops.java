package net.pinto.mythandmetal.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.block.ModBlocks;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;

import java.util.Set;

public class ModBlockLootDrops extends BlockLootSubProvider {
    public ModBlockLootDrops() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate(){
    this.dropSelf((ModBlocks.ASH_SAPLING.get()));
    this.dropSelf((ModBlocks.ENCHANTED_DIRT.get()));


    }
    @Override

    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
