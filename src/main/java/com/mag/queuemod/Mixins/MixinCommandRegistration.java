package com.mag.queuemod.Mixins;

import com.mag.queuemod.core.BarteringQueueCommand;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.commands.ExecuteCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Commands.class)
public abstract class MixinCommandRegistration {
    @Redirect( at = @At( value = "INVOKE", target = "Lnet/minecraft/server/commands/ExecuteCommand;register(Lcom/mojang/brigadier/CommandDispatcher;)V") , method = "<init>")
    private void injectCommand(CommandDispatcher<CommandSourceStack> commandDispatcher){
        BarteringQueueCommand.register(commandDispatcher);
        ExecuteCommand.register(commandDispatcher);
    }
}
