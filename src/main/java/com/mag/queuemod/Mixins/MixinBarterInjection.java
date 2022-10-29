package com.mag.queuemod.Mixins;

import com.mag.queuemod.Queue;
import com.mag.queuemod.core.BarteringManager;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * Mixin to inject Trade from the bartering queue into the loot generation
 * method in PiglinAi.class
 */
@Mixin(PiglinAi.class)
public abstract class MixinBarterInjection {

	@Inject(at = @At("RETURN"), method = "getBarterResponseItems", cancellable = true)
	private static void injectItemStack(Piglin piglin, CallbackInfoReturnable<List<ItemStack>> cir) {
		List<ItemStack> injectedItem = BarteringManager.getTradeAsList();
		if (injectedItem != null) {
			cir.setReturnValue(injectedItem);
			Queue.LOGGER.info("Injected Barter Item: {}", injectedItem.get(0));
		}
	}
}
