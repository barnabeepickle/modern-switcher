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
        // Check if the return value is false so we know no other F3 + keybinds are acting
        if (!cir.getReturnValue()) {
            Minecraft minecraft = Minecraft.getMinecraft();
            // Check if auxKey is the keybind's key code for the switcher
            if (auxKey == ModernSwitcherMod.switcherKeybind.getKeyCode()) {
                // Run a check that the player isn't borked
                if (ModernSwitcherMod.canSwitchGameMode(minecraft)) {
                    // Run a check that the player has permissions to switch gamemodes
                    if (minecraft.player.canUseCommand(2, "")) {
                        // This is where the switcher ui is opened
                    } else {
                        minecraft.debugFeedbackTranslated("debug.mgms.switcher.error.permissions");
                    }
                } else {
                    minecraft.debugFeedbackTranslated("debug.mgms.switcher.error.unable");
                }
                cir.setReturnValue(true);
            }
        }
    }
}
