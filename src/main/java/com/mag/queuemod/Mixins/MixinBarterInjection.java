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

@Mixin(PiglinAi.class)
public abstract class MixinBarterInjection {

    @Inject( at = @At("RETURN"), method = "getBarterResponseItems", cancellable = true)
    private static void injectItemStack(Piglin piglin, CallbackInfoReturnable<List<ItemStack>> cir){
        int index = BarteringManager.getCounter();
        if(index < BarteringManager.stackSize() && BarteringManager.isBarteringEnabled()){
            cir.setReturnValue(BarteringManager.getTradeAsList(index));
            BarteringManager.incrementCounter();
            Queue.LOGGER.info("Injected Barter Item");
        }
    }
//    @Redirect( at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/piglin/PiglinAi;throwItems(Lnet/minecraft/world/entity/monster/piglin/Piglin;Ljava/util/List;)V"), method = "stopHoldingOffHandItem")
//    private static void injectItemStack(Piglin piglin, List<ItemStack> list){
//        int index = BarteringManager.getCounter();
//        if(index < BarteringManager.getBarteringStack().size() && BarteringManager.isBarteringEnabled()) {
//            throwItems(piglin, BarteringManager.getBarteringStack().get(index));
//            Queue.LOGGER.info("Injected Barter Item: "+BarteringManager.getBarteringStack().get(index).get(0));
//            BarteringManager.setCounter(index + 1);
//        }
//        else {
//            throwItems(piglin, getBarterResponseItems(piglin));
//        }
//    @Redirect( at = @At( value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"), method = "throwItemsTowardPos")
//    private static Iterator<ItemStack> injectItemStack(List<ItemStack> instance){
//        int index = BarteringManager.getCounter();
//        if(index < BarteringManager.getStackSIze() && BarteringManager.isBarteringEnabled()) {
//            Queue.LOGGER.info("Trying to inject " + BarteringManager.getTrade(index).get(0));
//            instance = BarteringManager.getTrade(index);
//            Queue.LOGGER.info("Injected  " + BarteringManager.getTrade(index).get(0));
//            BarteringManager.setCounter(index + 1);
//        }
//        return instance.iterator();
//    }
}
