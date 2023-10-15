package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ModFile.MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModFile.MOD_ID);

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ModFile.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(RepelItemGroup.REPEL_GROUP)));
    }

    public static final RegistryObject<Block> BASE_REPEL_BLOCK = registerBlock("pokemon_repel_block", () -> new BaseRepelBlock());
    public static final RegistryObject<Block> SUPER_REPEL_BLOCK = registerBlock("pokemon_super_repel_block", () -> new SuperRepelBlock());
    public static final RegistryObject<Block> MAX_REPEL_BLOCK = registerBlock("pokemon_max_repel_block", () -> new MaxRepelBlock());

    public static final RegistryObject<TileEntityType<BaseRepelBlockEntity>> BASE_REPEL_BLOCK_ENTITY = TILE_ENTITIES.register("pokemon_repel_block_te",
            () -> TileEntityType.Builder.of(BaseRepelBlockEntity::new, BASE_REPEL_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<SuperRepelBlockEntity>> SUPER_REPEL_BLOCK_ENTITY = TILE_ENTITIES.register("pokemon_super_repel_block_te",
            () -> TileEntityType.Builder.of(SuperRepelBlockEntity::new, SUPER_REPEL_BLOCK.get()).build(null));

    public static final RegistryObject<TileEntityType<MaxRepelBlockEntity>> MAX_REPEL_BLOCK_ENTITY = TILE_ENTITIES.register("pokemon_max_repel_block_te",
            () -> TileEntityType.Builder.of(MaxRepelBlockEntity::new, MAX_REPEL_BLOCK.get()).build(null));


}