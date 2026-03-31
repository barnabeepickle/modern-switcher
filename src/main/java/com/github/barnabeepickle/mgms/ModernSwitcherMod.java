package com.github.barnabeepickle.mgms;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MODID, name = Tags.MOD_NAME, version = Tags.VERSION)
@Mod.EventBusSubscriber
public class ModernSwitcherMod {
    @Mod.Instance(Tags.MODID)
    public static ModernSwitcherMod INSTANCE;

    @SuppressWarnings("unused")
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.EventHandler
    public void preLoadEvent(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void loadEvent(FMLInitializationEvent event) {

    }
}
