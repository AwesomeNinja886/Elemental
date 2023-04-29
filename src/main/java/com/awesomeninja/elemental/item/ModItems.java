package com.awesomeninja.elemental.item;

import com.awesomeninja.elemental.Elemental;
import com.awesomeninja.elemental.fluid.ModFluids;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Elemental.MODID);

    public static final RegistryObject<Item> CRYOTHEUM_DUST = ITEMS.register("cryotheum_dust", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> GELID_CRYOTHEUM_BUCKET = ITEMS.register("gelid_cryotheum_bucket", () -> new BucketItem(ModFluids.SOURCE_GELID_CRYOTHEUM, new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS).stacksTo(1).craftRemainder(Items.BUCKET).rarity(Rarity.EPIC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
