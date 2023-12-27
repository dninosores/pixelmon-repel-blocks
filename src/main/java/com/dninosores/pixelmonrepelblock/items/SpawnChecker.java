package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.Utils;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.command.impl.CheckSpawnsCommand;
import com.pixelmonmod.pixelmon.spawning.LegendarySpawner;
import com.pixelmonmod.pixelmon.spawning.PixelmonSpawning;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SpawnChecker extends Item {
    private final String SPAWNER_KEY = "SPAWNER_KEY";

    public SpawnChecker() {
        super(new Properties().tab(RepelItemGroup.REPEL_GROUP).stacksTo(1));
    }

    public void appendHoverText(ItemStack stack, @Nullable World p_77624_2_, List<ITextComponent> text, ITooltipFlag p_77624_4_) {
        text.add(new TranslationTextComponent("item.pixelmonrepelblock.spawn_checker_tooltip"));
    }

    public ITextComponent getName(ItemStack stack) {
        return new StringTextComponent(super.getName(stack).getString() + ": " + getCurrentSpawner(stack).toUpperCase());
    }

    private String[] getSpawnerNames() {
        return ModFile.getConfig().spawner_names;
    }

    private CompoundNBT defaultNBT() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        nbtTagCompound.putString(SPAWNER_KEY, getSpawnerNames()[0]);
        return nbtTagCompound;
    }

    private CompoundNBT getTags(ItemStack stack){
        CompoundNBT tags = null;
        if (!stack.hasTag() || !stack.getTag().contains(SPAWNER_KEY)) {
            tags = defaultNBT();
        } else {
            tags = stack.getTag();
        }
        return tags;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack newstack = super.getDefaultInstance();
        newstack.setTag(this.defaultNBT());
        return newstack;
    }

    private String getCurrentSpawner(ItemStack stack) {
        return getTags(stack).getString(SPAWNER_KEY);
    }

    private String cycleSpawner(ItemStack stack) {
        String current = getCurrentSpawner(stack);
        if (Arrays.asList(getSpawnerNames()).contains(current)) {

            current = getSpawnerNames()[(Arrays.asList(getSpawnerNames()).indexOf(current) + 1) %
                    getSpawnerNames().length];
        } else {
            current = getSpawnerNames()[0];
        }
        CompoundNBT tags = getTags(stack);
        tags.putString(SPAWNER_KEY, current);
        stack.setTag(tags);
        return current;
    }

    @Override
    public ActionResult<ItemStack> use(World w, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!w.isClientSide()) {
            if (player.isCrouching()) {
                String newKey = cycleSpawner(stack).toUpperCase();
                player.sendMessage(new StringTextComponent(String.format("Spawn Check Mode: " + newKey)), player.getUUID());
            } else {
                player.sendMessage(new StringTextComponent(String.format("Checking spawns for: " +
                        getCurrentSpawner(stack).toUpperCase())).withStyle(TextFormatting.DARK_GREEN).withStyle(TextFormatting.BOLD),
                        player.getUUID());
                if (true) {
                    String spawnerName = getCurrentSpawner(stack);
                    if (spawnerName.equals(ModFile.PLAYER_SPAWNER_CODE)) {
                        spawnerName = player.getName().getString();
                    }
                    AbstractSpawner spawner = PixelmonSpawning.coordinator.getSpawner(spawnerName);
                    spawner.checkSpawns.checkSpawns(spawner, player.createCommandSourceStack().withLevel((ServerWorld) player.level),
                            Arrays.asList(player.getName().getString()));
                }

                boolean blocked = false;

                if (!Arrays.asList(ModFile.getConfig().bypass_repels).contains(getCurrentSpawner(stack))) {
                    for (Map.Entry<Vector3d, Integer> entry : ModFile.getSpawnBlockLocations().entrySet()) {
                        if (Utils.inCube(entry.getValue(), entry.getKey(), player.position())) {
                            player.sendMessage(new StringTextComponent(String.format("Spawning blocked by repel block at: (%s, %s, %s)",
                                    entry.getKey().x, entry.getKey().y, entry.getKey().z)), player.getUUID());
                            blocked = true;
                        }
                    }
                }



            }
        }
        return ActionResult.success(stack);

    }
}
