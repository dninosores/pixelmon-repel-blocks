package com.dninosores.pixelmonrepelblock.config;

import com.pixelmonmod.pixelmon.api.config.api.data.ConfigPath;
import com.pixelmonmod.pixelmon.api.config.api.yaml.AbstractYamlConfig;
import info.pixelmon.repack.org.spongepowered.objectmapping.ConfigSerializable;

@ConfigSerializable
@ConfigPath("config/ModId/config.yml")
public class Config extends AbstractYamlConfig {

    public int radius = 20;

    public Config() {
        super();
    }

}
