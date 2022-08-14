package com.mag.queuemod.core;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the bartering queue. Has functions to add, clear, and get trades from the queue
 */
public class BarteringManager {
    private static final List<Trades> barteringTradeQueue = new ArrayList<>();
    @Getter
    private static final Trades[] tradesList = Trades.values();
    @Setter @Getter
    private static boolean isBarteringEnabled = true;
    @Getter
    private static int counter = 0;
    public static void addTrade(Trades trade){
        barteringTradeQueue.add(trade);
    }
    public static int getQueueSize(){
        return barteringTradeQueue.size();
    }
    public static Trades getTrade(int index){
        return barteringTradeQueue.get(index);
    }

    /**
     * This part is implemented like this instead of directly passing the ItemStack because from the enum because when 3 or more piglins trade together, the enum ItemStack is getting overwritten by Air
     * @param index the index for the trade
     * @return Trade ItemStack wrapped in a List
     */
    public static List<ItemStack> getTradeAsList(int index){
        //Thanks to Scribble for this idea.
        Trades trade = barteringTradeQueue.get(index);
        switch(trade){
            case POTION_OF_FIRE_RESISTANCE, SPLASH_POTION_OF_FIRE_RESISTANCE, SOUL_SPEED_BOOK, SOUL_SPEED_BOOTS -> {
                return List.of(trade.barterItem);
            }
            default -> {
                Item tradeItem = trade.barterItem.getItem();
                int count = trade.barterItem.getCount();
                return List.of(new ItemStack(tradeItem, count));
            }
        }
    }
    public static void incrementCounter(){
        counter++;
    }
    public static void resetQueue(){
        barteringTradeQueue.clear();
        counter = 0;
    }
}
