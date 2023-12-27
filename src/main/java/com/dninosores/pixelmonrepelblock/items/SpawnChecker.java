package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.command.impl.CheckSpawnsCommand;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
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
import java.util.Arrays;
import java.util.Map;

public class SpawnChecker extends Item {
    public SpawnChecker() {
        super(new Properties().tab(RepelItemGroup.REPEL_GROUP).stacksTo(1));
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
                //AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner("legendary");
                // The wild spawn spawner is the player's username I think
                for (AbstractSpawner abstractSpawner : PixelmonSpawning.coordinator.spawners) {
                    ModFile.LOGGER.info(abstractSpawner.name);
                    if (abstractSpawner.name.equals("curry")) {
                        continue;
                    }
//                    if ( abstractSpawner instanceof LegendarySpawner) {
//                        LegendarySpawner lspawner = (LegendarySpawner) abstractSpawner;
//                        lspawner.checkSpawns.checkSpawns();
//                    }
                    abstractSpawner.checkSpawns.checkSpawns(abstractSpawner, player.createCommandSourceStack(),
                            Arrays.asList(player.getName().getString()));
                }

//                spawner.checkSpawns.checkSpawns(spawner, player.createCommandSourceStack(), new ArrayList<>());
//                spawner.checkSpawns.checkSpawns(PixelmonSpawning.coordinator.getSpawner("fishing"),
//                        player.createCommandSourceStack(), new ArrayList<>());
            }
        }
        return ActionResult.success(stack);

    }
}
