package com.dninosores.pixelmonrepelblock.config;

import com.pixelmonmod.pixelmon.api.config.api.data.ConfigPath;
import com.pixelmonmod.pixelmon.api.config.api.yaml.AbstractYamlConfig;
import info.pixelmon.repack.org.spongepowered.objectmapping.ConfigSerializable;

@ConfigSerializable
@ConfigPath("config/pixelmon_repel_block/config.yml")
public class Config extends AbstractYamlConfig {

    // Pokemon will not spawn if they are within this many blocks of a spawner
    public int radius = 10;
    public int radius_super = 20;
    public int radius_max = 30;

    public Config() {
        super();
    }

}
