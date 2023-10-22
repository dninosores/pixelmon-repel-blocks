package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;

public class SpawnChecker extends Item {
    public SpawnChecker() {
        super(new Properties().tab(RepelItemGroup.REPEL_GROUP));
    }

    @Override
    public ActionResult<ItemStack> use(World w, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (w.isClientSide()) {
            boolean blocked = false;
            for (Map.Entry<Vector3d, Integer> entry : ModFile.getSpawnBlockLocations().entrySet()) {
                if (Utils.inCube(entry.getValue(), entry.getKey(), player.position())) {
                    player.sendMessage(new StringTextComponent(String.format("Spawning blocked by repel block at: (%s, %s, %s)",
                            entry.getKey().x, entry.getKey().y, entry.getKey().z)), player.getUUID());
                    blocked = true;
                }
            }

            if (!blocked) {
                AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner(player.getName().getString());
                spawner.checkSpawns.checkSpawns(spawner, player.createCommandSourceStack(), new ArrayList<>());
            }
        }
        return ActionResult.success(stack);

    }
}
