package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.items.LureItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import java.lang.reflect.Field;

public class RepelCharm extends LureItem {
    public RepelCharm() {
        super(LureType.HA, LureStrength.STRONG);
        try {
            Field categoryField = Item.class.getDeclaredField("category");
            categoryField.setAccessible(true);
            categoryField.set(this, RepelItemGroup.REPEL_GROUP);

            Field maxDamageField = Item.class.getDeclaredField("maxDamage");
            maxDamageField.setAccessible(true);
            maxDamageField.set(this, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public float getMultiplier(AbstractSpawner spawner, SpawnInfo spawnInfo, float sum, float rarity) {
        return 0;
    }
}
