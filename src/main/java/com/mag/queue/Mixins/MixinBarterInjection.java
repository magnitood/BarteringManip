package com.mag.queue.Mixins;

import com.mag.queue.Queue;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PiglinAi.class)
public abstract class MixinBarterInjection {
    @Inject( at = @At("RETURN"), method = "getBarterResponseItems", cancellable = true)
    private static void injectItemStack(Piglin piglin, CallbackInfoReturnable<List<ItemStack>> cir){
        if(Queue.stackCounter.hasNext()){
            cir.setReturnValue(Queue.stackCounter.next());
        }
    }
}
