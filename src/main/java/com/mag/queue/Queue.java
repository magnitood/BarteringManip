package com.mag.queue;

import com.mag.queue.core.Trades;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;

public class Queue implements ModInitializer {
    public static final String MOD_ID = "queue";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static ArrayList<ArrayList<ItemStack>> barteringStack = new ArrayList<>();
    public static Iterator<ArrayList<ItemStack>> stackCounter;
    public static int counter = 0;
    @Override
    public void onInitialize() {
        LOGGER.info("Hello World");
        System.out.println(Minecraft.getInstance().getVersionType());
        barteringStack.add(Trades.CRYING_OBSIDIAN.barterItem);
        barteringStack.add(Trades.IRON_NUGGET.barterItem);
        barteringStack.add(Trades.ENDER_PEARL.barterItem);
        stackCounter = barteringStack.iterator();
    }
}
