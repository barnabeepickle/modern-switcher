package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;

import javax.annotation.Nonnull;

public class GameModeSwitcher extends CustomModularScreen {
    public GameModeSwitcher(String owner) {
        super(owner);
    }

    @Nonnull
    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        ModularPanel panel = ModularPanel.defaultPanel("switcher_panel");
        return panel;
    }
}
