package com.mag.queuemod.core;

import com.mag.queuemod.Queue;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Collections;

public enum Trades {
    ENDER_PEARL(new ItemStack(Items.ENDER_PEARL, 8), "ender_pearl"),
    OBSIDIAN(new ItemStack(Items.OBSIDIAN),  "obsidian"),
    STRING(new ItemStack(Items.STRING, 24),  "string"),
    IRON_NUGGET(new ItemStack(Items.IRON_NUGGET, 36), "iron_nugget"),
    SOUL_SPEED_BOOK(ComplexItemStack.SOUL_SPEED_BOOK,  "soul_speed_book"),
    SOUL_SPEED_BOOTS(ComplexItemStack.SOUL_SPEED_BOOTS, "soul_speed_boots"),
    POTION_OF_FIRE_RESISTANCE(ComplexItemStack.POTION_OF_FIRE_RESISTANCE, "potion_of_fire_resistance"),
    SPLASH_POTION_OF_FIRE_RESISTANCE(ComplexItemStack.SPLASH_POTION_OF_FIRE_RESISTANCE,  "splash_potion_of_fire_resistance"),
    NETHER_QUARTZ(new ItemStack(Items.QUARTZ, 16),  "nether_quartz"),
    GLOWSTONE_DUST(new ItemStack(Items.GLOWSTONE_DUST, 12), "glowstone_dust"),
    MAGMA_CREAM(new ItemStack(Items.MAGMA_CREAM, 6),  "magma_cream"),
    FIRE_CHARGE(new ItemStack(Items.FIRE_CHARGE, 5),  "fire_charge"),
    GRAVEL(new ItemStack(Items.GRAVEL, 16), "gravel"),
    LEATHER(new ItemStack(Items.LEATHER, 10),  "leather"),
    SOUL_SAND(new ItemStack(Items.SOUL_SAND, 16),  "soul_sand"),
    NETHER_BRICK(new ItemStack(Items.NETHER_BRICK, 16),  "nether_brick"),
    CRYING_OBSIDIAN(new ItemStack(Items.CRYING_OBSIDIAN, 3),  "crying_obsidian");

    public final ItemStack barterItem;
    public final String name;
    Trades(ItemStack barterItem, String name){
        this.barterItem = barterItem;
        this.name = name;
    }

    private static class ComplexItemStack {

        public static ItemStack SOUL_SPEED_BOOK;
        public static ItemStack SOUL_SPEED_BOOTS;
        public static ItemStack POTION_OF_FIRE_RESISTANCE;
        public static ItemStack SPLASH_POTION_OF_FIRE_RESISTANCE;
        public static ItemStack WATER_BOTTLE;

        static {
            SOUL_SPEED_BOOK = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.SOUL_SPEED, 3));
            SOUL_SPEED_BOOTS = new ItemStack(Items.IRON_BOOTS);
            POTION_OF_FIRE_RESISTANCE = new ItemStack(Items.POTION);
            POTION_OF_FIRE_RESISTANCE = PotionUtils.setPotion(POTION_OF_FIRE_RESISTANCE, Potions.FIRE_RESISTANCE);
            SPLASH_POTION_OF_FIRE_RESISTANCE = new ItemStack(Items.SPLASH_POTION);
            SPLASH_POTION_OF_FIRE_RESISTANCE = PotionUtils.setPotion(SPLASH_POTION_OF_FIRE_RESISTANCE, Potions.FIRE_RESISTANCE);
            WATER_BOTTLE = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);
            EnchantmentHelper.setEnchantments(Collections.singletonMap(Enchantments.SOUL_SPEED, 3), SOUL_SPEED_BOOTS);
            Queue.LOGGER.info("Complex Items Loaded");
        }
    }
}
