package com.mag.queue.Mixins;

import com.mag.queue.core.BarteringQueueCommand;
import com.mojang.brigadier.AmbiguityConsumer;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Commands.class)
public abstract class MixinCommandRegistration {
    @Shadow
    @Final
    private static Logger LOGGER;
    @Redirect(at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;findAmbiguities(Lcom/mojang/brigadier/AmbiguityConsumer;)V"), method = "<init>")
    public void inject_init(CommandDispatcher<CommandSourceStack> dispatcher, AmbiguityConsumer<CommandSourceStack> ambiguityConsumer) {
        BarteringQueueCommand.register(dispatcher);
        dispatcher.findAmbiguities((parent, child, sibling, inputs) -> LOGGER.warn("Ambiguity between arguments {} and {} with inputs: {}", (Object)dispatcher.getPath(child), (Object)dispatcher.getPath(sibling), (Object)inputs));
    }
}
