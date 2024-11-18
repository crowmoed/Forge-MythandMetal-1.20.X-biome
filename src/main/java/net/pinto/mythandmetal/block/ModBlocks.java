package net.pinto.mythandmetal.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pinto.mythandmetal.MythandMetal;
import net.pinto.mythandmetal.item.ModItems;
import net.pinto.mythandmetal.worldgen.tree.AshTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MythandMetal.MOD_ID);

    public static final RegistryObject<Block> ENCHANTED_DIRT = registerBlock("enchanted_dirt",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));

    public static final RegistryObject<Block> ASH_SAPLING = registerBlock("ash_sapling",
            ()-> new SaplingBlock(new AshTreeGrower(),BlockBehaviour.Properties.copy(Blocks.SPRUCE_SAPLING)));



    private  static  <T extends  Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return  ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),new Item.Properties()));



    }
    public static void register (IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
