package com.mag.queue.core;

import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Collections;

public abstract class ItemPropertyHandler {

    public static ItemStack SOUL_SPEED_BOOK ;
    public static ItemStack SOUL_SPEED_BOOTS = new ItemStack(Items.IRON_BOOTS);
    public static ItemStack POTION_OF_FIRE_RESISTANCE = new ItemStack(Items.POTION);
    public static ItemStack SPLASH_POTION_OF_FIRE_RESISTANCE = new ItemStack(Items.SPLASH_POTION);
    public static ItemStack WATER_BOTTLE = new ItemStack(Items.POTION);

    static {
        SOUL_SPEED_BOOK = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.SOUL_SPEED, 3));
        EnchantmentHelper.setEnchantments(Collections.singletonMap(Enchantments.SOUL_SPEED, 3), SOUL_SPEED_BOOTS);
        WATER_BOTTLE = PotionUtils.setPotion(WATER_BOTTLE, Potions.WATER);
        POTION_OF_FIRE_RESISTANCE = PotionUtils.setPotion(POTION_OF_FIRE_RESISTANCE, Potions.FIRE_RESISTANCE);
        SPLASH_POTION_OF_FIRE_RESISTANCE = PotionUtils.setPotion(SPLASH_POTION_OF_FIRE_RESISTANCE, Potions.FIRE_RESISTANCE);
    }
}
