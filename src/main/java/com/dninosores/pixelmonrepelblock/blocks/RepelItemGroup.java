package com.dninosores.pixelmonrepelblock.blocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class RepelItemGroup {
    public static final ItemGroup REPEL_GROUP = new ItemGroup("repelBlockTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.BASE_REPEL_BLOCK.get());
        }
    };
}
