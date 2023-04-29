package com.awesomeninja.elemental.fluid;

import com.awesomeninja.elemental.Elemental;
import com.awesomeninja.elemental.block.ModBlocks;
import com.awesomeninja.elemental.item.ModItems;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Elemental.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_GELID_CRYOTHEUM = FLUIDS.register("gelid_cryotheum_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.GELID_CRYOTHEUM_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_GELID_CRYOTHEUM = FLUIDS.register("flowing_gelid_cryotheum", () -> new ForgeFlowingFluid.Flowing(ModFluids.GELID_CRYOTHEUM_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_AERO_WATER = FLUIDS.register("aero_water_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.AERO_WATER_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_AERO_WATER = FLUIDS.register("flowing_aero_water", () -> new ForgeFlowingFluid.Flowing(ModFluids.AERO_WATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties GELID_CRYOTHEUM_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.GELID_CRYOTHEUM_FLUID_TYPE, SOURCE_GELID_CRYOTHEUM, FLOWING_GELID_CRYOTHEUM)
    .slopeFindDistance(6).block(ModBlocks.GELID_CRYOTHEUM_BLOCK).bucket(ModItems.GELID_CRYOTHEUM_BUCKET);

    public static final ForgeFlowingFluid.Properties AERO_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.AERO_WATER_FLUID_TYPE, SOURCE_AERO_WATER, FLOWING_AERO_WATER)
    .slopeFindDistance(4).block(ModBlocks.AERO_WATER_BLOCK).bucket(ModItems.AERO_WATER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
