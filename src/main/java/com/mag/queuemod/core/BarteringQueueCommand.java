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
        Trades[] trades = Trades.values();
        for (Trades trade : trades) {
            if (Trades.isTradeCompatible(trade)) {
                registerTradeOptions(commandDispatcher, trade);
            }
        }
        commandDispatcher.register(Commands.literal("bartering_queue")
                .then(
                        Commands.literal("toggle")
                                .executes(
                                        BarteringQueueCommand::toggle
                                )
                )
        );
//        commandDispatcher.register(Commands.literal("bartering_queue")
//                .then(
//                        Commands.literal("disable")
//                                .executes(
//                                        BarteringQueueCommand::disableQueue
//                                )
//                )
//        );
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
     * Toggles the queue
     */
    private static int toggle(CommandContext<CommandSourceStack> context){
    	boolean newState = !BarteringManager.isBarteringEnabled();
        BarteringManager.setBarteringEnabled(newState);
        Queue.LOGGER.info("Bartering Queue has been {}", newState? "enabled":"disabled");
        sendChatMessage(context, String.format(ChatFormatting.DARK_AQUA+"Bartering Queue is %s", newState ? ChatFormatting.GREEN+"Enabled" : ChatFormatting.RED+"Disabled"));
        return 1;
    }

//    /**
//     * Disables the queue
//     */
//    private static int disableQueue(CommandContext<CommandSourceStack> context){
//        BarteringManager.setBarteringEnabled(false);
//        Queue.LOGGER.info("Bartering Queue has been disabled");
//        sendChatMessage(context, ChatFormatting.DARK_AQUA+"Bartering Queue is "+ChatFormatting.RED+"Disabled");
//        return 1;
//    }

    /**
     * Adds trade to the queue
     */
    private static int addBarteringItem(CommandContext<CommandSourceStack> context, Trades trade, int i){
    	if(i<=0) {
    		context.getSource().sendFailure(new TextComponent("The number of trades should be at least 1"));
    		return 0;
    	}
        for (int j=0; j<i; j++) {
            Queue.LOGGER.info("trying to add item");
            BarteringManager.addTrade(trade);
            Queue.LOGGER.info("added "+trade.barterItem.toString());
        }
        sendChatMessage(context, ChatFormatting.DARK_AQUA+"Added "+ChatFormatting.DARK_PURPLE+ i +ChatFormatting.DARK_PURPLE+" "+trade.barterItem.getItem().toString()+" trade"+ (i>1?"s":"") +ChatFormatting.DARK_AQUA+ " to the queue");
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
        sendChatMessage(context, BarteringManager.finishedQueueToString()+BarteringManager.barterQueueToString());
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
