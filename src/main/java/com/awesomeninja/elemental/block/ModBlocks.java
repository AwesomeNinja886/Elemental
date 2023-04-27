package com.awesomeninja.elemental.block;

import com.awesomeninja.elemental.Elemental;
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

    public static final RegistryObject<LiquidBlock> GELID_CRYOTHEUM_BLOCK = BLOCKS.register("gelid_cryotheum", () -> new LiquidBlock(ModFluids.SOURCE_GELID_CRYOTHEUM, BlockBehaviour.Properties.copy(Blocks.WATER)));
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
