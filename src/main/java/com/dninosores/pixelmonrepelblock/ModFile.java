package com.dninosores.pixelmonrepelblock;

import com.dninosores.pixelmonrepelblock.blocks.ModBlocks;
import com.dninosores.pixelmonrepelblock.config.Config;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dninosores.pixelmonrepelblock.listener.SpawnListener;

import java.io.IOException;
import java.util.HashSet;


@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "pixelmonrepelblock";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    private Config config;

    private static HashSet<Vector3d> spawnBlockLocations;

    public ModFile() {
        instance = this;

        spawnBlockLocations = new HashSet<Vector3d>();

        reloadConfig();

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModFile::onModLoad);


        ModBlocks.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static HashSet<Vector3d> addSpawnBlockLocation(Vector3d vector) {
        LOGGER.atInfo().log("Spawn Block registered at " + vector);
        spawnBlockLocations.add(vector);
        return spawnBlockLocations;
    }

    public static HashSet<Vector3d> removeSpawnBlockLocation(Vector3d vector) {
        LOGGER.atInfo().log("Spawn Block removed at " + vector);
        spawnBlockLocations.remove(vector);
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
