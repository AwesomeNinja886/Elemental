package com.awesomeninja.elemental.block.custom;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class GelidCryotheumLiquidBlock extends LiquidBlock {
    public GelidCryotheumLiquidBlock(Supplier<? extends FlowingFluid> pFluid, Properties pProperties) {
        super(pFluid, pProperties);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.setIsInPowderSnow(true);
        if (pEntity.getType() == EntityType.BLAZE) {
            pEntity.hurt(DamageSource.FREEZE, 10f);
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }
}
