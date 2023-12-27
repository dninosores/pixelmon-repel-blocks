package com.dninosores.pixelmonrepelblock;

import com.dninosores.pixelmonrepelblock.blocks.ModBlocks;
import com.dninosores.pixelmonrepelblock.blocks.RepelBlock;
import com.dninosores.pixelmonrepelblock.config.Config;
import com.dninosores.pixelmonrepelblock.items.ModItems;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dninosores.pixelmonrepelblock.listener.SpawnListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "pixelmonrepelblock";

    public static final String PLAYER_SPAWNER_CODE = "PLAYER";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    private Config config;

    // Map of spawn block locations to their associated radii
    private static HashMap<Vector3d, Integer> spawnBlockLocations;

    public ModFile() {
        instance = this;

        spawnBlockLocations = new HashMap<Vector3d, Integer>();

        reloadConfig();

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModFile::onModLoad);


        ModBlocks.register(bus);
        ModItems.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() instanceof RepelBlock) {
            removeSpawnBlockLocation(Utils.getPosVec(event.getPos()));
        }
    }

    public static HashMap<Vector3d, Integer> addSpawnBlockLocation(Vector3d vector, int radius) {
        spawnBlockLocations.put(vector, radius);
        LOGGER.atDebug().log("Spawn Block registered at " + vector + " Length: " + spawnBlockLocations.size());
        return spawnBlockLocations;
    }

    public static HashMap<Vector3d, Integer> removeSpawnBlockLocation(Vector3d vector) {
        spawnBlockLocations.remove(vector);
        LOGGER.atDebug().log("Spawn Block removed at " + vector + " Length: " + spawnBlockLocations.size());
        return spawnBlockLocations;
    }

    public static HashMap<Vector3d, Integer> getSpawnBlockLocations() {
        return spawnBlockLocations;
    }

    public static void onModLoad(FMLCommonSetupEvent event) {
        // Here is how you register a listener for Pixelmon events
        // Pixelmon has its own event bus for its events, as does TCG
        // So any event listener for those mods need to be registered to those specific event buses
        Pixelmon.EVENT_BUS.register(new SpawnListener());
    }


    public void reloadConfig() {
        try {
            this.config = YamlConfigFactory.getInstance(Config.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static ModFile getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static Config getConfig() {
        return instance.config;
    }


}
