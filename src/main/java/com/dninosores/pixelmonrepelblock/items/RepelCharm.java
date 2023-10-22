package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.api.spawning.AbstractSpawner;
import com.pixelmonmod.pixelmon.api.spawning.SpawnInfo;
import com.pixelmonmod.pixelmon.entities.effects.RepelEffect;
import com.pixelmonmod.pixelmon.init.registry.EffectRegistration;
import com.pixelmonmod.pixelmon.items.LureItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.NumberNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.World;

import java.lang.reflect.Field;

public class RepelCharm extends Item {
    private final String ACTIVE = "active";

    public RepelCharm() {
        super(new Item.Properties().tab(RepelItemGroup.REPEL_GROUP));
    }

    private CompoundNBT defaultNBT() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        nbtTagCompound.putBoolean(ACTIVE, false);
        return nbtTagCompound;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack newstack = super.getDefaultInstance();
        newstack.setTag(this.defaultNBT());
        return newstack;
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        ModFile.LOGGER.info("CLICKED");
        CompoundNBT tags = null;
        if (!stack.hasTag() || !stack.getTag().contains(ACTIVE)) {
            tags = defaultNBT();
        } else {
            tags = stack.getTag();
        }
        tags.putBoolean(ACTIVE, !tags.getBoolean(ACTIVE));
        stack.setTag(tags);

        ModFile.LOGGER.info(tags.getBoolean(ACTIVE));

        return ActionResultType.SUCCESS;

    }

    public boolean isFoil(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(ACTIVE)) {
            return stack.getTag().getBoolean(ACTIVE);
        }
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World p_77663_2_, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (stack.hasTag() && stack.getTag().contains(ACTIVE) && stack.getTag().getBoolean(ACTIVE)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.addEffect(new EffectInstance(EffectRegistration.REPEL.get(), 160));

            }
        }
    }
}
