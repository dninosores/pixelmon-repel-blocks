package com.dninosores.pixelmonrepelblock.config;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.pixelmonmod.pixelmon.api.config.api.data.ConfigPath;
import com.pixelmonmod.pixelmon.api.config.api.yaml.AbstractYamlConfig;
import info.pixelmon.repack.org.spongepowered.objectmapping.ConfigSerializable;

@ConfigSerializable
@ConfigPath("config/pixelmon_repel_block/config.yml")
public class Config extends AbstractYamlConfig {

    // Pokemon will not spawn if they are within this many blocks of a spawner
    public int radius = 8;
    public int radius_super = 16;
    public int radius_max = 32;

    public String[] spawner_names = new String[]{ModFile.PLAYER_SPAWNER_CODE, "legendary", "megaboss", "fishing",
            "caverock", "grass", "headbutt", "rocksmash", "seaweed", "tallgrass", "sweetscent", "forage", "curry"};

    public String[] bypass_repels = new String[]{"megaboss", "fishing",
          "headbutt", "rocksmash", "seaweed", "sweetscent", "forage", "curry"};

    public Config() {
        super();
    }

}
