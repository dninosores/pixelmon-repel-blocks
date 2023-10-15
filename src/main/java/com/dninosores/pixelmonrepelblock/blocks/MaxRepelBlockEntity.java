package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;

public class MaxRepelBlockEntity extends RepelBlockEntity {
    public MaxRepelBlockEntity() {
        super(ModBlocks.MAX_REPEL_BLOCK_ENTITY.get());
    }

    @Override
    int getRadius() {
        return ModFile.getConfig().radius_max;
    }
}
