package com.awesomeninja.elemental.block;

import com.awesomeninja.elemental.Elemental;
import com.awesomeninja.elemental.block.custom.GelidCryotheumLiquidBlock;
import com.awesomeninja.elemental.fluid.ModFluids;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elemental.MODID);

    public static final RegistryObject<GelidCryotheumLiquidBlock> GELID_CRYOTHEUM_BLOCK = BLOCKS.register("gelid_cryotheum", () -> new GelidCryotheumLiquidBlock(ModFluids.SOURCE_GELID_CRYOTHEUM, BlockBehaviour.Properties.copy(Blocks.WATER).lightLevel((state) -> 15)));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
