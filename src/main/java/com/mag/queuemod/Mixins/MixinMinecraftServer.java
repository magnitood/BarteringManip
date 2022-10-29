package com.mag.queuemod.Mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mag.queuemod.core.Events;

import net.minecraft.server.MinecraftServer;

/**
 * Creates hooks when starting and stopping the server
 * @author Scribble
 *
 */
@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
	
	@Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;updateStatusIcon(Lnet/minecraft/network/protocol/status/ServerStatus;)V"))
	public void inject_init(CallbackInfo ci) {
		Events.onStartServer((MinecraftServer)(Object)this);
	}
	
	@Inject(method = "stopServer", at = @At("HEAD"))
	public void inject_stopServer(CallbackInfo ci) {
		Events.onStopServer((MinecraftServer)(Object)this);
	}
}
