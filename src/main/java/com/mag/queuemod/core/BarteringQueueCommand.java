package com.mag.queuemod.core;

import com.mag.queuemod.Queue;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class BarteringQueueCommand {

    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher){
        for(int i=0; i<Trades.values().length; i++){
            registerTradeOptions(commandDispatcher, Trades.values()[i]);
        }
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("enable")
                                .executes(
                                        (context) -> BarteringQueueCommand.enableQueue()
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("disable")
                                .executes(
                                        (context) -> BarteringQueueCommand.disableQueue()
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("clear")
                                .executes(
                                        (context) -> BarteringQueueCommand.clearQueue()
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("show")
                                .executes(
                                        (context) -> BarteringQueueCommand.show()
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("debugShow")
                                .executes(
                                        (context) -> BarteringQueueCommand.debugShow()
                                )
                )
        );
    }

    private static void registerTradeOptions(CommandDispatcher<CommandSourceStack> commandDispatcher, Trades trade) {
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("add")
                                .then(
                                        Commands.literal(trade.name)
                                                .then(Commands.argument("times", IntegerArgumentType.integer())
                                                        .executes(
                                                                 context -> BarteringQueueCommand.addBarteringItem(trade, IntegerArgumentType.getInteger(context, "times"))
                                                        )
                                                )
                                                .executes(
                                                        context -> BarteringQueueCommand.addBarteringItem(trade, 1)
                                                )
                                )
                )
        );
    }

    private static int enableQueue(){
        BarteringManager.setBarteringEnabled(true);
        Queue.LOGGER.info("Bartering Queue has been enabled");
        return 1;
    }
    private static int disableQueue(){
        BarteringManager.setBarteringEnabled(false);
        Queue.LOGGER.info("Bartering Queue has been disabled");
        return 1;
    }
    private static int addBarteringItem(Trades trade, int i){
        for (int j=0; j<i; j++) {
            Queue.LOGGER.info("trying to add item");
            BarteringManager.addTrade(trade);
            Queue.LOGGER.info("item_added");
        }
        return 1;
    }
    private static int clearQueue(){
        BarteringManager.clearTrades();
        BarteringManager.resetCounter();
        Queue.LOGGER.info("Bartering Queue has been cleared");
        return 1;
    }
    private static int show(){
        for(int i = 0; i< BarteringManager.stackSize(); i++){
            Queue.LOGGER.info(BarteringManager.getTrade(i).get(0));
        }
        return 1;
    }
    private static int debugShow(){
        Trades[] trades = Trades.values();
        for (int i = 0; i < trades.length; i++) {
            Queue.LOGGER.info("Trade Constant "+i+" "+trades[i].barterItem);
        }
        return 1;
    }
}
