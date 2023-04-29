package com.awesomeninja.elemental.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

@Mixin ( LiquidBlockRenderer.class )
public interface ILiquidBlockRendererAccessor {
    @Accessor
    public TextureAtlasSprite getWaterOverlay();
}
