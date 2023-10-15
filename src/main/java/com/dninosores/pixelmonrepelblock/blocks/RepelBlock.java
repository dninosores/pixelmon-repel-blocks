package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

abstract class RepelBlockEntity extends TileEntity {

    public RepelBlockEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    abstract int getRadius();

    @Override
    public void onLoad() {
        ModFile.addSpawnBlockLocation(Utils.getPosVec(this.getBlockPos()), this.getRadius());
    }
}

public abstract class RepelBlock extends Block {

    public RepelBlock() {
        super(AbstractBlock.Properties.of(Material.GLASS).noOcclusion().lightLevel(s -> 8));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);
}


