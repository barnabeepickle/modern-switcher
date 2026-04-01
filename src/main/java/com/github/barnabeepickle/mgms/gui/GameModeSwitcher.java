package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.IThemeApi;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import net.minecraft.world.GameType;

import javax.annotation.Nonnull;

public class GameModeSwitcher extends CustomModularScreen {
    private static final GameType[] modeHistory = new GameType[0];
    private static final GameType selectedMode = GameType.NOT_SET;

    public GameModeSwitcher(String owner) {
        super(owner);
        IThemeApi.get().registerThemeForScreen(owner, "switcher_panel", "vanilla_dark");
    }

    @Nonnull
    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        ModularPanel panel = ModularPanel.defaultPanel("switcher_panel").size(125, 75);

        RichTextWidget gamemodeText = new RichTextWidget()
                .horizontalCenter()
                .fullWidth()
                .height(10)
                .top(4)
                .alignment(Alignment.CENTER);
        gamemodeText.textBuilder(text -> {
            // Both of these uses translation strings already in Minecraft
            if (selectedMode != GameType.NOT_SET) {
                text.addLine(IKey.lang("gameMode." + selectedMode.getName()));
            } else {
                text.addLine(IKey.lang("selectWorld.gameMode"));
            }
        });
        panel.child(gamemodeText);

        return panel;
    }
}
