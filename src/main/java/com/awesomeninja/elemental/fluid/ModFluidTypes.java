package com.awesomeninja.elemental.fluid;

import com.awesomeninja.elemental.Elemental;
import com.mojang.math.Vector3f;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Elemental.MODID);

    public static final ResourceLocation CRYOTHEUM_STILL = new ResourceLocation(Elemental.MODID, "block/gelid_cryotheum");
    public static final ResourceLocation CRYOTHEUM_FLOWING = new ResourceLocation(Elemental.MODID, "block/gelid_cryotheum_flowing");
    public static final ResourceLocation CRYOTHEUM_OVERLAY = new ResourceLocation(Elemental.MODID, "misc/gelid_cryotheum_overlay");

    public static final RegistryObject<FluidType> GELID_CRYOTHEUM_FLUID_TYPE = register(
        "gelid_cryotheum_fluid",
        CRYOTHEUM_STILL,
        CRYOTHEUM_FLOWING,
        CRYOTHEUM_OVERLAY,
        0xFFFFFFFF,
        new Vector3f(95f / 255f, 206f / 255f, 230f / 255f),
        FluidType.Properties.create()
            .canConvertToSource(false)
            .canExtinguish(true)
            .density(3000)
            .temperature(-4223)
            .viscosity(7000)
            .lightLevel(15)
            .motionScale(0.0023)
    );

    public static final RegistryObject<FluidType> register(String name, ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(stillTexture, flowingTexture, overlayTexture, tintColor, fogColor, properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
