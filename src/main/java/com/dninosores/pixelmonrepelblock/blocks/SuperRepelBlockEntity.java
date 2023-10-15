package com.dninosores.pixelmonrepelblock.blocks;

import com.dninosores.pixelmonrepelblock.ModFile;

public class SuperRepelBlockEntity extends RepelBlockEntity {
    public SuperRepelBlockEntity() {
        super(ModBlocks.SUPER_REPEL_BLOCK_ENTITY.get());
    }

    @Override
    int getRadius() {
        return ModFile.getConfig().radius_super;
    }
}
