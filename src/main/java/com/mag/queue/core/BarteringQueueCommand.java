package com.mag.queue.core;

import com.mag.queue.Queue;
import com.mojang.brigadier.CommandDispatcher;
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
    }

    private static void registerTradeOptions(CommandDispatcher<CommandSourceStack> commandDispatcher, Trades trade) {
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("add")
                                .then(
                                        Commands.literal(trade.name)
                                                .executes(
                                                        (context) -> BarteringQueueCommand.addBarteringItem(trade)
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
    private static int addBarteringItem(Trades trade){
        Queue.LOGGER.info("trying to add item");
        BarteringManager.getBarteringStack().add(trade.barterItem);
        Queue.LOGGER.info("item_added");
        return 1;
    }
    private static int clearQueue(){
        BarteringManager.getBarteringStack().clear();
        BarteringManager.setCounter(0);
        Queue.LOGGER.info("Bartering Queue has been cleared");
        return 1;
    }
}
