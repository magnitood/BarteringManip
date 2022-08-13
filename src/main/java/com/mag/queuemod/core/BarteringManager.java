package com.mag.queuemod.core;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
    public static int stackSize(){
        return barteringTradeQueue.size();
    }
    public static Trades getTrade(int index){
        return barteringTradeQueue.get(index);
    }
    public static List<ItemStack> getTradeAsList(int index){
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
    public static void clearTrades(){
        barteringTradeQueue.clear();
    }
    public static void resetCounter(){
        counter = 0;
    }
}
