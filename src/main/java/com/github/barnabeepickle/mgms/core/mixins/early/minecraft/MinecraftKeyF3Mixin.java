package com.github.barnabeepickle.mgms.core.mixins.early.minecraft;

import com.github.barnabeepickle.mgms.ModernSwitcherMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MinecraftKeyF3Mixin {

    @Inject(method = "processKeyF3(I)Z", at = @At("RETURN"))
    private void processKeyF3(int auxKey, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            if (auxKey == ModernSwitcherMod.switcherKeybind.getKeyCode()) {

            }
        }
    }
}
