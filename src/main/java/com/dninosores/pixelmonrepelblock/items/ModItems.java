package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.client.gui.inventory.SlotInventoryPixelmon;
import com.pixelmonmod.pixelmon.container.PokeBagContainer;
import com.pixelmonmod.pixelmon.items.LureCasingItem;
import com.pixelmonmod.pixelmon.items.LureItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModFile.MOD_ID);
    public static final RegistryObject<Item> REPEL_CHARM = ITEMS.register("repel_charm",
            () -> new RepelCharm());

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
