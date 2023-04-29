package com.awesomeninja.elemental.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

@Mixin ( LiquidBlockRenderer.class )
public interface ILiquidBlockRendererInvoker {
    @Invoker("isNeighborSameFluid")
    public static boolean callIsNeighborSameFluid(FluidState pFirstState, FluidState pSecondState) {
        System.out.println("Something isn't working as intended ()");
        return false;
    }
    @Invoker("getHeight")
    public float callGetHeight(BlockAndTintGetter p_203161_, Fluid p_203162_, BlockPos p_203163_, BlockState p_203164_, FluidState pState);
    @Invoker("isFaceOccludedByNeighbor")
    public static boolean callIsFaceOccludedByNeighbor(BlockGetter pLevel, BlockPos pPos, Direction pSide, float pHeight, BlockState pBlockState) {
        System.out.println("Something isn't working as intended (isFaceOccludedByNeighbor)");
        return false;
    }
    @Invoker("calculateAverageHeight")
    public float callCalculateAverageHeight(BlockAndTintGetter p_203150_, Fluid p_203151_, float p_203152_, float p_203153_, float p_203154_, BlockPos p_203155_);
    @Invoker("getLightColor")
    public int callGetLightColor(BlockAndTintGetter pLevel, BlockPos pPos);
    @Invoker("vertex")
    public void callVertex(VertexConsumer pConsumer, double pX, double pY, double pZ, float pRed, float pGreen, float pBlue, float alpha, float pU, float pV, int pPackedLight);
}
