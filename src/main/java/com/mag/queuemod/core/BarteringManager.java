package com.mag.queuemod.core;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FileUtils;

import com.mag.queuemod.Queue;
import com.mag.queuemod.Mixins.AccessorLevelStorage;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.ChatFormatting;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/**
 * Class to manage the bartering queue. Has functions to add, clear, and get trades from the queue
 */
public class BarteringManager {
	/**
	 * The main bartering queue that gets polled when a piglin desires a trade.
	 */
    private static final ConcurrentLinkedQueue<Trades> barteringTradeQueue = new ConcurrentLinkedQueue<>();
    /**
     * The finished trades list, filled once the trades from the bartering queue have succeeded. Can be emptied per element
     */
    private static final ConcurrentLinkedQueue<Trades> finishedTradeQueue = new ConcurrentLinkedQueue<>();
    
    public static MinecraftServer server;
    
    @Setter @Getter
    private static boolean isBarteringEnabled = true;
    
    public static void addTrade(Trades trade){
        barteringTradeQueue.add(trade);
        finishedTradeQueue.poll(); // Remove a finished trade once a new trade has been added
        saveToFile();
    }
    
    public static int getQueueSize(){
        return barteringTradeQueue.size();
    }
    
    /**
     * This part is implemented like this instead of directly passing the ItemStack because from the enum because when 3 or more piglins trade together, the enum ItemStack is getting overwritten by Air
     * @param index the index for the trade
     * @return Trade ItemStack wrapped in a List or null if empty or disabled
     */
    public static List<ItemStack> getTradeAsList(){
    	
    	if(!isBarteringEnabled) {	// Bartering is not enabled
    		return null;
    	}
    	
        //Thanks to Scribble for this idea.
        Trades trade = barteringTradeQueue.poll();
        if(trade == null) {
        	return null;
        }
        
        List<ItemStack> itemList = new ArrayList<>();
        switch(trade){
//            case POTION_OF_FIRE_RESISTANCE, SPLASH_POTION_OF_FIRE_RESISTANCE, SOUL_SPEED_BOOK, SOUL_SPEED_BOOTS:
            case POTION_OF_FIRE_RESISTANCE:
            case SPLASH_POTION_OF_FIRE_RESISTANCE:
            case SOUL_SPEED_BOOTS:
            case SOUL_SPEED_BOOK:
            	itemList =  Collections.singletonList(trade.barterItem);
            	break;
            default:
                Item tradeItem = trade.barterItem.getItem();
                int count = trade.barterItem.getCount();
                itemList = Collections.singletonList(new ItemStack(tradeItem, count));
                break;
        }
        finishedTradeQueue.add(trade);
        saveToFile();
        return itemList;
    }
    
    /**
     * Resets finished queue and bartering queue
     */
    public static void resetQueue(){
        barteringTradeQueue.clear();
        finishedTradeQueue.clear();
        saveToFile();
    }
    
    /**
     * @return The trades in the bartering queue with formatting
     */
    public static String barterQueueToString() {
    	String out="";
    	Iterator<Trades> iterator = barteringTradeQueue.iterator();
    	
    	int counter = finishedTradeQueue.size(); // Start at the finished trades
    	
    	while (iterator.hasNext()) {
			Trades trade = (Trades) iterator.next();
			counter++;
			out = out.concat(String.format(ChatFormatting.WHITE+"%s."+ ChatFormatting.DARK_AQUA+" %s %s\n", 
					counter, 	// Replacement for the first %s in String.format
					trade, 		// The second %s...
					counter==finishedTradeQueue.size()+1 ?					// if counter==finishedTrades.size()+1
							ChatFormatting.YELLOW+"\u21E0 Next Trade" 	// then do this
							:""));										// else return an empty string
		}
    	return out;
    }
    
    /**
     * @return The trades in the finished trade queue with formatting
     */
    public static String finishedQueueToString() {
    	String out="";
    	Iterator<Trades> iterator = finishedTradeQueue.iterator();
    	
    	int counter = 0;
    	
    	while (iterator.hasNext()) {
    		Trades finishedTrade = (Trades) iterator.next();
			counter++;
			out = out.concat(String.format(ChatFormatting.WHITE+"%s." + ChatFormatting.RED + " %s\n", counter, finishedTrade));
    	}
    	return out;
    }
    
    /**
     * Saves the current queue to saves/worldname/barteringQueue.txt
     */
    private static void saveToFile() {
    	
		List<String> linesToSave = new ArrayList<>();

		// Convert queue to a list of lines
		Iterator<Trades> iterator = barteringTradeQueue.iterator();
		while (iterator.hasNext()) {
			Trades trade = (Trades) iterator.next();
			linesToSave.add(trade.toString());
		}
    	
		// Create the file
		File file;
		try {
			file = getSaveFile();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}


		// Delete the file if it exists
		if (file.exists())
			file.delete();

		// Write
		try {
			FileUtils.writeLines(file, StandardCharsets.UTF_8.toString(), linesToSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * loads the queue from saves/worldname/barteringQueue.txt
     */
    public static void loadSaveFile() {
    	
		if (server == null) {
			Queue.LOGGER.error("Server is null!");
			return;
		}
		
		// Create the file
		File file;
		try {
			file = getSaveFile();
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		if(!file.exists()) {
			Queue.LOGGER.warn("barteringQueue.txt doesn't exist, ignoring it");
			return;
		}
		
		List<String> lines = new ArrayList<>();
    	
    	// Read!
    	try {
			lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
    	
    	barteringTradeQueue.clear();
    	finishedTradeQueue.clear();
    	
    	for(String line : lines) {
    		Trades trade = Trades.fromString(line);
    		if(trade==null)
    			continue;
    		barteringTradeQueue.add(trade);
    	}
    }
    
    private static File getSaveFile() throws Exception {
		if (server == null) {
			throw new Exception("The server is null!");
		}
    	// Get the world folder name from the minecraft server
		String worldFolderName = ((AccessorLevelStorage) server).getStorageSource().getLevelId();
		
    	return new File(server.getServerDirectory(), (server.isDedicatedServer() ? "" : "/saves/") + worldFolderName + "/barteringQueue.txt");
    }
}
