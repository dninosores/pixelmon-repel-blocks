package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

class RepelBlockEntity extends TileEntity {
    public RepelBlockEntity() {
        super(ModBlocks.REPEL_BLOCK_ENTITY.get());
    }

    @Override
    public void onLoad() {
        ModFile.addSpawnBlockLocation(Utils.getPosVec(this.getBlockPos()));
    }
}

public class RepelBlock extends Block {

    public RepelBlock() {
        super(AbstractBlock.Properties.of(Material.BUILDABLE_GLASS));
        ModFile.getLogger().atError().log("CONSTRUCTOR CONSTRUCTOR CONSTRUCTOR COASNTRUONCTOR");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RepelBlockEntity();
    }
}
