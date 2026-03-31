package com.github.barnabeepickle.mgms;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = Tags.MODID, name = Tags.MOD_NAME, version = Tags.VERSION)
@Mod.EventBusSubscriber
public class ModernSwitcherMod {
    @Mod.Instance(Tags.MODID)
    public static ModernSwitcherMod INSTANCE;

    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    public static KeyBinding switcherKeybind;

    @Mod.EventHandler
    public void preLoadEvent(FMLPreInitializationEvent event) {
        switcherKeybind = new KeyBinding("key." + Tags.MODID + ".switcher", KeyConflictContext.IN_GAME, Keyboard.KEY_F4, "key.category." + Tags.MODID);
        ClientRegistry.registerKeyBinding(switcherKeybind);
    }

    @Mod.EventHandler
    public void loadEvent(FMLInitializationEvent event) {

    }
}
