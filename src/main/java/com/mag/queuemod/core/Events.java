package com.mag.queuemod.core;

import com.mag.queuemod.Queue;
import com.mag.queuemod.Mixins.MixinMinecraftServer;

import net.minecraft.server.MinecraftServer;

/**
 * Various events
 * 
 * @author Scribble
 *
 */
public class Events {
	
	/**
	 * Called when the minecraft server is started
	 * @param server The server variable when it's initialised
	 * @see MixinMinecraftServer#inject_init(org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
	 */
	public static void onStartServer(MinecraftServer server) {
		Queue.LOGGER.info("Calling event on start server");
		BarteringManager.server = server;
		BarteringManager.loadSaveFile();
	}

	/**
	 * Called when the minecraft server is stopped
	 * @param server The server variable when it's stopped
	 * @see MixinMinecraftServer#inject_stopServer(org.spongepowered.asm.mixin.injection.callback.CallbackInfo)
	 */
	public static void onStopServer(MinecraftServer server) {
		Queue.LOGGER.info("Calling event on stop server");
		BarteringManager.server = null;
	}
}
