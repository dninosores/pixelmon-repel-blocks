package com.dninosores.pixelmonrepelblock.items;

import com.dninosores.pixelmonrepelblock.ModFile;
import com.dninosores.pixelmonrepelblock.blocks.RepelItemGroup;
import com.pixelmonmod.pixelmon.init.registry.EffectRegistration;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class RepelCharm extends Item {
    private final String ACTIVE = "active";

    public RepelCharm() {
        super(new Item.Properties().tab(RepelItemGroup.REPEL_GROUP).stacksTo(1));
    }

    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> text, ITooltipFlag p_77624_4_) {
        text.add(new TranslationTextComponent("item.pixelmonrepelblock.repel_charm_tooltip"));
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
    public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        CompoundNBT tags = null;
        if (!stack.hasTag() || !stack.getTag().contains(ACTIVE)) {
            tags = defaultNBT();
        } else {
            tags = stack.getTag();
        }
        tags.putBoolean(ACTIVE, !tags.getBoolean(ACTIVE));
        stack.setTag(tags);
        
        return ActionResult.success(stack);

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
