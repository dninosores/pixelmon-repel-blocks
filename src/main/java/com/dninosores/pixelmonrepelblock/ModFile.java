package com.dninosores.pixelmonrepelblock;

import com.dninosores.pixelmonrepelblock.blocks.ModBlocks;
import com.dninosores.pixelmonrepelblock.config.ExampleConfig;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.config.api.yaml.YamlConfigFactory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dninosores.pixelmonrepelblock.listener.PokemonSpawnExampleListener;

import java.io.IOException;



@Mod(ModFile.MOD_ID)
@Mod.EventBusSubscriber(modid = ModFile.MOD_ID)
public class ModFile {

    public static final String MOD_ID = "pixelmonrepelblock";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static ModFile instance;

    private ExampleConfig config;

    public ModFile() {
        instance = this;

        reloadConfig();

        MinecraftForge.EVENT_BUS.register(this);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModFile::onModLoad);


        ModBlocks.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void onModLoad(FMLCommonSetupEvent event) {
        // Here is how you register a listener for Pixelmon events
        // Pixelmon has its own event bus for its events, as does TCG
        // So any event listener for those mods need to be registered to those specific event buses
        Pixelmon.EVENT_BUS.register(new PokemonSpawnExampleListener());
    }


    public void reloadConfig() {
        try {
            this.config = YamlConfigFactory.getInstance(ExampleConfig.class);
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

    public static ExampleConfig getConfig() {
        return instance.config;
    }


}
