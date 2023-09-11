package com.dninosores.pixelmonrepelblock.listener;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.pixelmonmod.pixelmon.api.events.spawning.CreateSpawnerEvent;
import com.pixelmonmod.pixelmon.api.spawning.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnListener {

    @SubscribeEvent
    public void onCreateSpawner(CreateSpawnerEvent event){

        event.spawner.conditions.add(new SpawnerCondition() {
                                         @Override
                                         public boolean fits(AbstractSpawner abstractSpawner, SpawnInfo spawnInfo, SpawnLocation spawnLocation) {
                                             return false;
                                         }
                                     }
        );
    }

}
