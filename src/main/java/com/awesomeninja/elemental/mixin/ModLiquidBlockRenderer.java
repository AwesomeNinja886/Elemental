package com.awesomeninja.elemental.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

@Mixin ( LiquidBlockRenderer.class )
public abstract class ModLiquidBlockRenderer {
    
    @Inject(method = "tesslate", at = @At("HEAD"))
    public void injectTesslate(BlockAndTintGetter pLevel, BlockPos pPos, VertexConsumer pVertexConsumer, BlockState pBlockState, FluidState pFluidState) {
        if (pFluidState.getFluidType().isLighterThanAir()) {
            LiquidBlockRenderer original_renderer = (LiquidBlockRenderer) (Object) this;
            boolean flag = pFluidState.is(FluidTags.LAVA);
            TextureAtlasSprite[] atextureatlassprite = net.minecraftforge.client.ForgeHooksClient.getFluidSprites(pLevel, pPos, pFluidState);
            int i = net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions.of(pFluidState).getTintColor(pFluidState, pLevel, pPos);
            float alpha = (float)(i >> 24 & 255) / 255.0F;
            float f = (float)(i >> 16 & 255) / 255.0F;
            float f1 = (float)(i >> 8 & 255) / 255.0F;
            float f2 = (float)(i & 255) / 255.0F;
            BlockState blockStateDown = pLevel.getBlockState(pPos.relative(Direction.DOWN));
            FluidState fluidStateDown = blockStateDown.getFluidState();
            BlockState blockStateUp = pLevel.getBlockState(pPos.relative(Direction.UP));
            FluidState fluidStateUp = blockStateUp.getFluidState();
            BlockState blockStateNorth = pLevel.getBlockState(pPos.relative(Direction.NORTH));
            FluidState fluidStateNorth = blockStateNorth.getFluidState();
            BlockState blockStateSouth = pLevel.getBlockState(pPos.relative(Direction.SOUTH));
            FluidState fluidStateSouth = blockStateSouth.getFluidState();
            BlockState blockStateWest = pLevel.getBlockState(pPos.relative(Direction.WEST));
            FluidState fluidStateWest = blockStateWest.getFluidState();
            BlockState blockStateEast = pLevel.getBlockState(pPos.relative(Direction.EAST));
            FluidState fluidStateEast = blockStateEast.getFluidState();
            boolean fluidUpIsSame = LiquidBlockRenderer.shouldRenderFace(pLevel, pPos, pFluidState, pBlockState, Direction.UP, fluidStateUp) && !ILiquidBlockRendererInvoker.callIsFaceOccludedByNeighbor(pLevel, pPos, Direction.UP, 0.8888889F, blockStateUp);
            boolean fluidDownIsSame = !ILiquidBlockRendererInvoker.callIsNeighborSameFluid(pFluidState, fluidStateDown);
            boolean fluidNorthIsSame = LiquidBlockRenderer.shouldRenderFace(pLevel, pPos, pFluidState, pBlockState, Direction.NORTH, fluidStateNorth);
            boolean fluidSouthIsSame = LiquidBlockRenderer.shouldRenderFace(pLevel, pPos, pFluidState, pBlockState, Direction.SOUTH, fluidStateSouth);
            boolean fluidWestIsSame = LiquidBlockRenderer.shouldRenderFace(pLevel, pPos, pFluidState, pBlockState, Direction.WEST, fluidStateWest);
            boolean fluidEastIsSame = LiquidBlockRenderer.shouldRenderFace(pLevel, pPos, pFluidState, pBlockState, Direction.EAST, fluidStateEast);
            if (fluidUpIsSame || fluidDownIsSame || fluidEastIsSame || fluidWestIsSame || fluidNorthIsSame || fluidSouthIsSame) {
               float f3 = pLevel.getShade(Direction.DOWN, true);
               float f4 = pLevel.getShade(Direction.UP, true);
               float f5 = pLevel.getShade(Direction.NORTH, true);
               float f6 = pLevel.getShade(Direction.WEST, true);
               Fluid fluid = pFluidState.getType();
               float f11 = ((ILiquidBlockRendererInvoker) original_renderer).callGetHeight(pLevel, fluid, pPos, pBlockState, pFluidState);
               float f7;
               float f8;
               float f9;
               float f10;
               if (f11 >= 1.0F) {
                  f7 = 1.0F;
                  f8 = 1.0F;
                  f9 = 1.0F;
                  f10 = 1.0F;
               } else {
                  float f12 = ((ILiquidBlockRendererInvoker) original_renderer).callGetHeight(pLevel, fluid, pPos.north(), blockStateNorth, fluidStateNorth);
                  float f13 = ((ILiquidBlockRendererInvoker) original_renderer).callGetHeight(pLevel, fluid, pPos.south(), blockStateSouth, fluidStateSouth);
                  float f14 = ((ILiquidBlockRendererInvoker) original_renderer).callGetHeight(pLevel, fluid, pPos.east(), blockStateEast, fluidStateEast);
                  float f15 = ((ILiquidBlockRendererInvoker) original_renderer).callGetHeight(pLevel, fluid, pPos.west(), blockStateWest, fluidStateWest);
                  f7 = ((ILiquidBlockRendererInvoker) original_renderer).callCalculateAverageHeight(pLevel, fluid, f11, f12, f14, pPos.relative(Direction.NORTH).relative(Direction.EAST));
                  f8 = ((ILiquidBlockRendererInvoker) original_renderer).callCalculateAverageHeight(pLevel, fluid, f11, f12, f15, pPos.relative(Direction.NORTH).relative(Direction.WEST));
                  f9 = ((ILiquidBlockRendererInvoker) original_renderer).callCalculateAverageHeight(pLevel, fluid, f11, f13, f14, pPos.relative(Direction.SOUTH).relative(Direction.EAST));
                  f10 = ((ILiquidBlockRendererInvoker) original_renderer).callCalculateAverageHeight(pLevel, fluid, f11, f13, f15, pPos.relative(Direction.SOUTH).relative(Direction.WEST));
               }
      
               double dX = (double)(pPos.getX() & 15);
               double dY = (double)(pPos.getY() & 15);
               double dZ = (double)(pPos.getZ() & 15);
               float f16 = 0.001F;
               float f17 = fluidDownIsSame ? 0.001F : 0.0F;
               if (fluidDownIsSame && !ILiquidBlockRendererInvoker.callIsFaceOccludedByNeighbor(pLevel, pPos, Direction.DOWN, Math.min(Math.min(f8, f10), Math.min(f9, f7)), blockStateUp)) {
                  f8 -= 0.001F;
                  f10 -= 0.001F;
                  f9 -= 0.001F;
                  f7 -= 0.001F;
                  Vec3 vec3 = pFluidState.getFlow(pLevel, pPos);
                  float f18;
                  float f19;
                  float f20;
                  float f21;
                  float f22;
                  float f23;
                  float f24;
                  float f25;
                  if (vec3.x == 0.0D && vec3.z == 0.0D) {
                     TextureAtlasSprite textureatlassprite1 = atextureatlassprite[0];
                     f18 = textureatlassprite1.getU(0.0D);
                     f22 = textureatlassprite1.getV(0.0D);
                     f19 = f18;
                     f23 = textureatlassprite1.getV(16.0D);
                     f20 = textureatlassprite1.getU(16.0D);
                     f24 = f23;
                     f21 = f20;
                     f25 = f22;
                  } else {
                     TextureAtlasSprite textureatlassprite = atextureatlassprite[1];
                     float f26 = (float)Mth.atan2(vec3.z, vec3.x) - ((float)Math.PI / 2F);
                     float f27 = Mth.sin(f26) * 0.25F;
                     float f28 = Mth.cos(f26) * 0.25F;
                     float f29 = 8.0F;
                     f18 = textureatlassprite.getU((double)(8.0F + (-f28 - f27) * 16.0F));
                     f22 = textureatlassprite.getV((double)(8.0F + (-f28 + f27) * 16.0F));
                     f19 = textureatlassprite.getU((double)(8.0F + (-f28 + f27) * 16.0F));
                     f23 = textureatlassprite.getV((double)(8.0F + (f28 + f27) * 16.0F));
                     f20 = textureatlassprite.getU((double)(8.0F + (f28 + f27) * 16.0F));
                     f24 = textureatlassprite.getV((double)(8.0F + (f28 - f27) * 16.0F));
                     f21 = textureatlassprite.getU((double)(8.0F + (f28 - f27) * 16.0F));
                     f25 = textureatlassprite.getV((double)(8.0F + (-f28 - f27) * 16.0F));
                  }
      
                  float f49 = (f18 + f19 + f20 + f21) / 4.0F;
                  float f50 = (f22 + f23 + f24 + f25) / 4.0F;
                  float f51 = (float)atextureatlassprite[0].getWidth() / (atextureatlassprite[0].getU1() - atextureatlassprite[0].getU0());
                  float f52 = (float)atextureatlassprite[0].getHeight() / (atextureatlassprite[0].getV1() - atextureatlassprite[0].getV0());
                  float f53 = 4.0F / Math.max(f52, f51);
                  f18 = Mth.lerp(f53, f18, f49);
                  f19 = Mth.lerp(f53, f19, f49);
                  f20 = Mth.lerp(f53, f20, f49);
                  f21 = Mth.lerp(f53, f21, f49);
                  f22 = Mth.lerp(f53, f22, f50);
                  f23 = Mth.lerp(f53, f23, f50);
                  f24 = Mth.lerp(f53, f24, f50);
                  f25 = Mth.lerp(f53, f25, f50);
                  int j = ((ILiquidBlockRendererInvoker) original_renderer).callGetLightColor(pLevel, pPos);
                  float f30 = f4 * f;
                  float f31 = f4 * f1;
                  float f32 = f4 * f2;
      
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 0.0D, dY + (double)f8, dZ + 0.0D, f30, f31, f32, alpha, f18, f22, j);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 0.0D, dY + (double)f10, dZ + 1.0D, f30, f31, f32, alpha, f19, f23, j);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f9, dZ + 1.0D, f30, f31, f32, alpha, f20, f24, j);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f7, dZ + 0.0D, f30, f31, f32, alpha, f21, f25, j);
                  if (pFluidState.shouldRenderBackwardUpFace(pLevel, pPos.above())) {
                    ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 0.0D, dY + (double)f8, dZ + 0.0D, f30, f31, f32, alpha, f18, f22, j);
                    ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f7, dZ + 0.0D, f30, f31, f32, alpha, f21, f25, j);
                    ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f9, dZ + 1.0D, f30, f31, f32, alpha, f20, f24, j);
                    ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 0.0D, dY + (double)f10, dZ + 1.0D, f30, f31, f32, alpha, f19, f23, j);
                  }
               }
      
               if (fluidUpIsSame) {
                  float f40 = atextureatlassprite[0].getU0();
                  float f41 = atextureatlassprite[0].getU1();
                  float f42 = atextureatlassprite[0].getV0();
                  float f43 = atextureatlassprite[0].getV1();
                  int l = ((ILiquidBlockRendererInvoker) original_renderer).callGetLightColor(pLevel, pPos.below());
                  float f46 = f3 * f;
                  float f47 = f3 * f1;
                  float f48 = f3 * f2;
      
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX, dY + (double)f17, dZ + 1.0D, f46, f47, f48, alpha, f40, f43, l);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX, dY + (double)f17, dZ, f46, f47, f48, alpha, f40, f42, l);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f17, dZ, f46, f47, f48, alpha, f41, f42, l);
                  ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, dX + 1.0D, dY + (double)f17, dZ + 1.0D, f46, f47, f48, alpha, f41, f43, l);
               }
      
               int k = ((ILiquidBlockRendererInvoker) original_renderer).callGetLightColor(pLevel, pPos);
      
               for(Direction direction : Direction.Plane.HORIZONTAL) {
                  float f44;
                  float f45;
                  double d3;
                  double d4;
                  double d5;
                  double d6;
                  boolean neighboringFluidIsSame;
                  switch (direction) {
                     case NORTH:
                        f44 = f8;
                        f45 = f7;
                        d3 = dX;
                        d5 = dX + 1.0D;
                        d4 = dZ + (double)0.001F;
                        d6 = dZ + (double)0.001F;
                        neighboringFluidIsSame = fluidNorthIsSame;
                        break;
                     case SOUTH:
                        f44 = f9;
                        f45 = f10;
                        d3 = dX + 1.0D;
                        d5 = dX;
                        d4 = dZ + 1.0D - (double)0.001F;
                        d6 = dZ + 1.0D - (double)0.001F;
                        neighboringFluidIsSame = fluidSouthIsSame;
                        break;
                     case WEST:
                        f44 = f10;
                        f45 = f8;
                        d3 = dX + (double)0.001F;
                        d5 = dX + (double)0.001F;
                        d4 = dZ + 1.0D;
                        d6 = dZ;
                        neighboringFluidIsSame = fluidWestIsSame;
                        break;
                     default:
                        f44 = f7;
                        f45 = f9;
                        d3 = dX + 1.0D - (double)0.001F;
                        d5 = dX + 1.0D - (double)0.001F;
                        d4 = dZ;
                        d6 = dZ + 1.0D;
                        neighboringFluidIsSame = fluidEastIsSame;
                  }
      
                  if (neighboringFluidIsSame && !ILiquidBlockRendererInvoker.callIsFaceOccludedByNeighbor(pLevel, pPos, direction, Math.max(f44, f45), pLevel.getBlockState(pPos.relative(direction)))) {
                     BlockPos blockpos = pPos.relative(direction);
                     TextureAtlasSprite textureatlassprite2 = atextureatlassprite[1];
                     if (atextureatlassprite[2] != null) {
                        if (pLevel.getBlockState(blockpos).shouldDisplayFluidOverlay(pLevel, blockpos, pFluidState)) {
                           textureatlassprite2 = atextureatlassprite[2];
                        }
                     }
      
                     float f54 = textureatlassprite2.getU(0.0D);
                     float f55 = textureatlassprite2.getU(8.0D);
                     float f33 = textureatlassprite2.getV((double)((1.0F - f44) * 16.0F * 0.5F));
                     float f34 = textureatlassprite2.getV((double)((1.0F - f45) * 16.0F * 0.5F));
                     float f35 = textureatlassprite2.getV(8.0D);
                     float f36 = direction.getAxis() == Direction.Axis.Z ? f5 : f6;
                     float f37 = f4 * f36 * f;
                     float f38 = f4 * f36 * f1;
                     float f39 = f4 * f36 * f2;
      
                     ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d3, dY + (double)f44, d4, f37, f38, f39, alpha, f54, f33, k);
                     ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d5, dY + (double)f45, d6, f37, f38, f39, alpha, f55, f34, k);
                     ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d5, dY + (double)f17, d6, f37, f38, f39, alpha, f55, f35, k);
                     ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d3, dY + (double)f17, d4, f37, f38, f39, alpha, f54, f35, k);
                     if (textureatlassprite2 != ((ILiquidBlockRendererAccessor) original_renderer).getWaterOverlay()) {
                        ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d3, dY + (double)f17, d4, f37, f38, f39, alpha, f54, f35, k);
                        ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d5, dY + (double)f17, d6, f37, f38, f39, alpha, f55, f35, k);
                        ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d5, dY + (double)f45, d6, f37, f38, f39, alpha, f55, f34, k);
                        ((ILiquidBlockRendererInvoker) original_renderer).callVertex(pVertexConsumer, d3, dY + (double)f44, d4, f37, f38, f39, alpha, f54, f33, k);
                     }
                  }
               }
      
            }
            return;
        }
    }
}
