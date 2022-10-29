package com.mag.queuemod.Mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorageSource;

@Mixin(MinecraftServer.class)
public interface AccessorLevelStorage {
	@Accessor
	public LevelStorageSource.LevelStorageAccess getStorageSource();
}
