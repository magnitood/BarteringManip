package com.mag.queuemod.core;

import com.mag.queuemod.Queue;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;

/**
 * Commands to call BarteringManager functions from the chat
 */
public class BarteringQueueCommand {
    public static void register(CommandDispatcher<CommandSourceStack> commandDispatcher){
        for(int i=0; i<Trades.values().length; i++){
            registerTradeOptions(commandDispatcher, Trades.values()[i]);
        }
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("enable")
                                .executes(
                                        BarteringQueueCommand::enableQueue
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("disable")
                                .executes(
                                        BarteringQueueCommand::disableQueue
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("reset")
                                .executes(
                                        BarteringQueueCommand::resetQueue
                                )
                )
        );
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("show")
                                .executes(
                                        BarteringQueueCommand::show
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
                                                                context -> BarteringQueueCommand.addBarteringItem(context, trade, IntegerArgumentType.getInteger(context, "times"))
                                                        )
                                                )
                                                .executes(
                                                        context -> BarteringQueueCommand.addBarteringItem(context, trade, 1)
                                                )
                                )
                )
        );
    }

    /**
     * Enables the queue
     */
    private static int enableQueue(CommandContext<CommandSourceStack> context){
        BarteringManager.setBarteringEnabled(true);
        Queue.LOGGER.info("Bartering Queue has been enabled");
        sendChatMessage(context, ChatFormatting.DARK_AQUA+"Bartering Queue is "+ChatFormatting.GREEN+"Enabled");
        return 1;
    }

    /**
     * Disables the queue
     */
    private static int disableQueue(CommandContext<CommandSourceStack> context){
        BarteringManager.setBarteringEnabled(false);
        Queue.LOGGER.info("Bartering Queue has been disabled");
        sendChatMessage(context, ChatFormatting.DARK_AQUA+"Bartering Queue is "+ChatFormatting.RED+"Disabled");
        return 1;
    }

    /**
     * Adds trade to the queue
     */
    private static int addBarteringItem(CommandContext<CommandSourceStack> context, Trades trade, int i){
        for (int j=0; j<i; j++) {
            Queue.LOGGER.info("trying to add item");
            BarteringManager.addTrade(trade);
            Queue.LOGGER.info("added "+trade.barterItem.toString());
        }
        sendChatMessage(context, ChatFormatting.DARK_AQUA+"Added "+ChatFormatting.DARK_PURPLE+ i +ChatFormatting.DARK_PURPLE+" "+trade.barterItem.getItem().toString()+" trade(s)"+ChatFormatting.DARK_AQUA+ " to the queue");
        return 1;
    }

    /**
     * Resets the Queue
     */
    private static int resetQueue(CommandContext<CommandSourceStack> context){
        BarteringManager.resetQueue();
        Queue.LOGGER.info("Bartering Queue has been cleared");
        sendChatMessage(context, ChatFormatting.DARK_AQUA+"The Queue has been "+ChatFormatting.GREEN+"cleared");
        return 1;
    }

    /**
     * Prints the contents and status of the queue in the chat in a neat coloured format
     */
    private static int show(CommandContext<CommandSourceStack> context){
        if(BarteringManager.getQueueSize() == 0){
            sendChatMessage(context, ChatFormatting.DARK_AQUA+"The queue is empty");
            return 1;
        }
        sendChatMessage(context, ChatFormatting.DARK_PURPLE+"Trading Queue:");
        for(int i = 0; i< BarteringManager.getCounter(); i++){
            Trades finishedTrades = BarteringManager.getTrade(i);
            sendChatMessage(context, i + 1 +". "+ChatFormatting.RED+finishedTrades.barterItem.getItem().toString());
        }
        Trades nextTrade = BarteringManager.getTrade(BarteringManager.getCounter());
        sendChatMessage(context, BarteringManager.getCounter() + 1 +". "+ChatFormatting.DARK_AQUA+nextTrade.barterItem.getItem().toString()+ChatFormatting.YELLOW+" \u21E0 Next Trade");
        for(int i = BarteringManager.getCounter()+1; i< BarteringManager.getQueueSize(); i++){
            Trades upcommingTrades = BarteringManager.getTrade(i);
            sendChatMessage(context, i + 1 +". "+ChatFormatting.GREEN+upcommingTrades.barterItem.getItem().toString());
        }
        return 1;
    }

    /**
     * Debug method I used to check the contents of the Enum
     */
    private static int debugShow(){
        Trades[] trades = Trades.values();
        for (int i = 0; i < trades.length; i++) {
            Queue.LOGGER.info("Trade Constant "+i+" "+trades[i].barterItem);
        }
        return 1;
    }
    private static void sendChatMessage(CommandContext<CommandSourceStack> context, String string){
        context.getSource().sendSuccess(new TextComponent(string), false);
    }
}
