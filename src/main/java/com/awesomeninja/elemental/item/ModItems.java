package com.awesomeninja.elemental.item;

import com.awesomeninja.elemental.Elemental;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Elemental.MODID);

    public static final RegistryObject<Item> CRYOTHEUM_DUST = ITEMS.register("cryotheum_dust", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
