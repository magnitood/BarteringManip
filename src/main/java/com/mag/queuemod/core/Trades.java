package com.mag.queuemod.core;

import com.mag.queuemod.Queue;
import com.mojang.bridge.game.GameVersion;
import net.minecraft.DetectedVersion;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.Collections;

/**
 * Enum to store the ItemStack and string name (for commands) of each possible trade
 */
public enum Trades {
    ENDER_PEARL(new ItemStack(Items.ENDER_PEARL, 4), "ender_pearl", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    OBSIDIAN(new ItemStack(Items.OBSIDIAN,1), "obsidian", "", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    STRING(new ItemStack(Items.STRING, 9), "string", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    IRON_NUGGET(new ItemStack(Items.IRON_NUGGET, 36),"iron_nugget", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    SOUL_SPEED_BOOK(ComplexItemStack.SOUL_SPEED_BOOK, "soul_speed_book", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    SOUL_SPEED_BOOTS(ComplexItemStack.SOUL_SPEED_BOOTS, "soul_speed_boots", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    POTION_OF_FIRE_RESISTANCE(ComplexItemStack.POTION_OF_FIRE_RESISTANCE, "potion_of_fire_resistance", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    SPLASH_POTION_OF_FIRE_RESISTANCE(ComplexItemStack.SPLASH_POTION_OF_FIRE_RESISTANCE, "splash_potion_of_fire_resistance", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    NETHER_QUARTZ(new ItemStack(Items.QUARTZ, 12), "nether_quartz", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    FIRE_CHARGE(new ItemStack(Items.FIRE_CHARGE, 1), "fire_charge", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    GRAVEL(new ItemStack(Items.GRAVEL, 16), "gravel", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    LEATHER(new ItemStack(Items.LEATHER, 4),"leather", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    SOUL_SAND(new ItemStack(Items.SOUL_SAND, 8),"soul_sand", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    NETHER_BRICK(new ItemStack(Items.NETHER_BRICK, 8),"nether_brick", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    CRYING_OBSIDIAN(new ItemStack(Items.CRYING_OBSIDIAN,3),"crying_obsidian", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    SPECTRAL_ARROW(new ItemStack(Items.SPECTRAL_ARROW,12),"spectral_arrow", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    BLACKSTONE(new ItemStack(Items.BLACKSTONE,16),"blackstone", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),
    WATER_BOTTLE(ComplexItemStack.WATER_BOTTLE, "water_bottle", "1.16.2", "1.16.3", "1.16.4","1.16.5", "1.17", "1.17.1", "1.18", "1.18.1", "1.18.2", "1.19", "1.19.1", "1.19.2"),


    ENDER_PEARL_PRE_NERF(new ItemStack(Items.ENDER_PEARL, 8), "ender_pearl", "1.16", "1.16.1"),
    STRING_PRE_NERF(new ItemStack(Items.STRING, 24),  "string", "1.16", "1.16.1"),
    NETHER_QUARTZ_PRE_NERF(new ItemStack(Items.QUARTZ, 16),  "nether_quartz", "1.16", "1.16.1"),
    GLOWSTONE_DUST(new ItemStack(Items.GLOWSTONE_DUST, 12), "glowstone_dust", "1.16", "1.16.1"),
    MAGMA_CREAM(new ItemStack(Items.MAGMA_CREAM, 6),  "magma_cream", "1.16", "1.16.1"),
    FIRE_CHARGE_PRE_NERF(new ItemStack(Items.FIRE_CHARGE, 5),  "fire_charge", "1.16", "1.16.1"),
    LEATHER_PRE_NERF(new ItemStack(Items.LEATHER, 10),  "leather", "1.16", "1.16.1"),
    SOUL_SAND_PRE_NERF(new ItemStack(Items.SOUL_SAND, 16),  "soul_sand", "1.16", "1.16.1"),
    NETHER_BRICK_PRE_NERF(new ItemStack(Items.NETHER_BRICK, 16),  "nether_brick", "1.16", "1.16.1");

    public final ItemStack barterItem;
    public final String name;

    public final String[] compatibleVersions;
    Trades(ItemStack barterItem, String name, String... compatibleVersions){
        this.barterItem = barterItem;
        this.name = name;
        this.compatibleVersions = compatibleVersions;
    }

    /**
     * Class to initialise some complex enchanted and potion ItemStacks
     */
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
    
    @Override
    public String toString() {
    	return name;
    }
    
    public static Trades fromString(String name) {
    	for (Trades trade : values()) {
			if(trade.name.equals(name) && isTradeCompatible(trade)) {
				return trade;
			}
		}
    	return null;
    }

    /**
     * checks whether a trade can be bartered in the loaded version
     * @param trade bartering trade
     * @return true is bartering trade is compatible
     */
    public static boolean isTradeCompatible(Trades trade){
        GameVersion runningVersion = DetectedVersion.tryDetectVersion();
        for (String compatibleVersion : trade.compatibleVersions) {
            if (runningVersion.getName().equals(compatibleVersion)){
                return true;
            }
        }
        return false;
    }
}
