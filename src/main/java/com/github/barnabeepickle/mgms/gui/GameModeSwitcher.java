package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;

import javax.annotation.Nonnull;

@SuppressWarnings({"UnstableApiUsage", "deprecation"})
public class GameModeSwitcher extends CustomModularScreen {
    @Nonnull
    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        ModularPanel panel = ModularPanel.defaultPanel("switcher_panel");
        return panel;
    }
}
