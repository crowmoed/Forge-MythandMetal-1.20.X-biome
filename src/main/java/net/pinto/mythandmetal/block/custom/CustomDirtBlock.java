package net.pinto.mythandmetal.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.pinto.mythandmetal.block.ModBlocks;

public class CustomDirtBlock extends Block {
    public CustomDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        BlockState aboveState = world.getBlockState(pos.above());

        // Check if the block above allows light through and if there's enough light
        boolean isTransparent = aboveState.propagatesSkylightDown(world, pos.above());
        int lightLevel = world.getMaxLocalRawBrightness(pos.above());
        boolean isTouchingGrass = isAdjacentToGrass(world, pos);

        // Conditions to turn dirt back into grass:
        if ((isTransparent || lightLevel >= 9) && isTouchingGrass){
            // Replace this block with your custom grass block
            world.setBlock(pos, ModBlocks.ASH_GRASS.get().defaultBlockState(), 2);
        }
    }
    private boolean isAdjacentToGrass(ServerLevel world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockState neighborState = world.getBlockState(pos.relative(direction));
            if (neighborState.is(ModBlocks.ASH_GRASS.get())) {
                return true;
            }
        }
        return false;
    }
}
