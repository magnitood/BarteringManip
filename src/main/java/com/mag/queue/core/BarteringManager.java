package com.mag.queue.core;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class BarteringManager {
    @Getter
    private static final ArrayList<ArrayList<ItemStack>> barteringStack = new ArrayList<>();
    @Getter
    private static final Trades[] tradesList = Trades.values();
    @Setter @Getter
    private static boolean isBarteringEnabled = true;
    @Getter @Setter
    public static int counter = 0;
}
