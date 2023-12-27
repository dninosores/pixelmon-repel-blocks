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

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpawnListener {

    @SubscribeEvent
    public void onCreateSpawner(CreateSpawnerEvent event) {

        event.spawner.conditions.add(new SpawnerCondition() {
            @Override
            public boolean fits(AbstractSpawner abstractSpawner, SpawnInfo spawnInfo, SpawnLocation spawnLocation) {
                if (Arrays.asList(ModFile.getConfig().bypass_repels).contains(abstractSpawner.name)) {
                    return true;
                }
                for (Map.Entry<Vector3d, Integer> entry : ModFile.getSpawnBlockLocations().entrySet()) {
                    if (Utils.inCube(entry.getValue(), entry.getKey(), Utils.getPosVec(spawnLocation.location.pos.immutable()))) {
                        //ModFile.LOGGER.atInfo().log("Repel block prevented pokemon spawn at " + Utils.getPosVec(spawnLocation.location.pos.immutable()));
                        return false;
                    }
                }
                return true;
            }
        });
    }

}
