package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.calculators.CheckSpawns;
import com.pixelmonmod.pixelmon.command.impl.CheckSpawnsCommand;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.ArrayList;

public class SpawnChecker extends Item {
    public SpawnChecker() {
        super(new Properties().tab(RepelItemGroup.REPEL_GROUP));
    }

    @Override
    public ActionResult<ItemStack> use(World w, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (w.isClientSide()) {
            AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner(player.getName().getString());
            spawner.checkSpawns.checkSpawns(spawner, player.createCommandSourceStack(), new ArrayList<>());
        }
        return ActionResult.success(stack);

    }
}
