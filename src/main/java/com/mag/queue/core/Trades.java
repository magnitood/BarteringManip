package com.mag.queue.core;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Collections;

public enum Trades {
    ENDER_PEARL(new ArrayList<>(Collections.singleton(new ItemStack(Items.ENDER_PEARL, 8))), "ender_pearl"),
    ENDER_PEARL_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.ENDER_PEARL, 4))), "ender_pearl"),
    OBSIDIAN(new ArrayList<>(Collections.singleton(new ItemStack(Items.OBSIDIAN,1))), "obsidian"),
    STRING(new ArrayList<>(Collections.singleton(new ItemStack(Items.STRING, 24))), "string"),
    STRING_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.STRING, 9))), "string"),
    IRON_NUGGET(new ArrayList<>(Collections.singleton(new ItemStack(Items.IRON_NUGGET, 36))),"iron_nugget"),
    SOUL_SPEED_BOOK(new ArrayList<>(Collections.singleton(ItemPropertyHandler.SOUL_SPEED_BOOK)), "soul_speed_book"),
    SOUL_SPEED_BOOTS(new ArrayList<>(Collections.singleton(ItemPropertyHandler.SOUL_SPEED_BOOTS)), "soul_speed_boots"),
    POTION_OF_FIRE_RESISTANCE(new ArrayList<>(Collections.singleton(ItemPropertyHandler.POTION_OF_FIRE_RESISTANCE)), "potion_of_fire_resistance"),
    SPLASH_POTION_OF_FIRE_RESISTANCE(new ArrayList<>(Collections.singleton(ItemPropertyHandler.SPLASH_POTION_OF_FIRE_RESISTANCE)), "splash_potion_of_fire_resistance"),
    NETHER_QUARTZ(new ArrayList<>(Collections.singleton(new ItemStack(Items.QUARTZ, 16))), "nether_quartz"),
    NETHER_QUARTZ_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.QUARTZ, 12))), "nether_quartz"),
    GLOWSTONE_DUST(new ArrayList<>(Collections.singleton(new ItemStack(Items.GLOWSTONE_DUST, 12))), "glowstone_dust"),
    MAGMA_CREAM(new ArrayList<>(Collections.singleton(new ItemStack(Items.MAGMA_CREAM, 6))), "magma_cream"),
    FIRE_CHARGE(new ArrayList<>(Collections.singleton(new ItemStack(Items.FIRE_CHARGE, 5))), "fire_charge"),
    FIRE_CHARGE_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.FIRE_CHARGE, 1))), "fire_charge"),
    GRAVEL(new ArrayList<>(Collections.singleton(new ItemStack(Items.GRAVEL, 16))), "gravel"),
    LEATHER(new ArrayList<>(Collections.singleton(new ItemStack(Items.LEATHER, 10))),"leather"),
    LEATHER_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.LEATHER, 4))),"leather"),
    SOUL_SAND(new ArrayList<>(Collections.singleton(new ItemStack(Items.SOUL_SAND, 8))),"soul_sand"),
    NETHER_BRICK(new ArrayList<>(Collections.singleton(new ItemStack(Items.NETHER_BRICK, 16))),"nether_brick"),
    NETHER_BRICK_NERFED(new ArrayList<>(Collections.singleton(new ItemStack(Items.NETHER_BRICK, 8))),"nether_brick"),
    CRYING_OBSIDIAN(new ArrayList<>(Collections.singleton(new ItemStack(Items.CRYING_OBSIDIAN,3))),"crying_obsidian"),
    SPECTRAL_ARROW(new ArrayList<>(Collections.singleton(new ItemStack(Items.SPECTRAL_ARROW,12))),"spectral_arrow"),
    BLACKSTONE(new ArrayList<>(Collections.singleton(new ItemStack(Items.BLACKSTONE,16))),"blackstone"),
    WATER_BOTTLE(new ArrayList<>(Collections.singleton(ItemPropertyHandler.WATER_BOTTLE)), "water_bottle");

    public final ArrayList<ItemStack> barterItem;
    public final String name;
    Trades(ArrayList<ItemStack> barterItem, String name){
        this.barterItem = barterItem;
        this.name = name;
    }
}
