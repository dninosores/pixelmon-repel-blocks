package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;

public class BaseRepelBlockEntity extends RepelBlockEntity {
    public BaseRepelBlockEntity() {
        super(ModBlocks.BASE_REPEL_BLOCK_ENTITY.get());
    }

    @Override
    int getRadius() {
        return ModFile.getConfig().radius;
    }
}
