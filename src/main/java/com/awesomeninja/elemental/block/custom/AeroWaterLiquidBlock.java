package com.awesomeninja.elemental.block.custom;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

public class AeroWaterLiquidBlock extends LiquidBlock {
    public static final ImmutableList<Direction> POSSIBLE_FLOW_DIRECTIONS = ImmutableList.of(Direction.UP, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST);
    public AeroWaterLiquidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
    }
    
    
}
