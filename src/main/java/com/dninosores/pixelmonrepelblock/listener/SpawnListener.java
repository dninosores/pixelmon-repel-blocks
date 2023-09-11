package com.dninosores.pixelmonrepelblock.listener;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import com.pixelmonmod.pixelmon.api.events.spawning.CreateSpawnerEvent;
import com.pixelmonmod.pixelmon.api.spawning.*;
import com.pixelmonmod.pixelmon.api.spawning.calculators.CheckSpawns;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class SpawnListener {

    @SubscribeEvent
    public void onCreateSpawner(CreateSpawnerEvent event) {

        event.spawner.conditions.add(new SpawnerCondition() {
            @Override
            public boolean fits(AbstractSpawner abstractSpawner, SpawnInfo spawnInfo, SpawnLocation spawnLocation) {
                for (Vector3d repelPosition : ModFile.getSpawnBlockLocations()) {
                    if (Utils.inCube(ModFile.getConfig().radius, repelPosition, Utils.getPosVec(spawnLocation.location.pos.immutable()))) {
                        ModFile.LOGGER.atInfo().log("Repel block prevented pokemon spawn at " + Utils.getPosVec(spawnLocation.location.pos.immutable()));
                        return false;
                    }
                }
                return true;
            }
        });
    }

}
