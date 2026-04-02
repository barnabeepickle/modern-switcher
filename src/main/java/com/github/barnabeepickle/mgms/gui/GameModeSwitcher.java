package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.IThemeApi;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.widget.Widget;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import com.cleanroommc.modularui.widgets.layout.Column;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.github.barnabeepickle.mgms.ModernSwitcherMod;
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

        // RichTextWidget is currently broken and won't center the text correctly
        // this will be fixed in the next modularui update
        RichTextWidget gamemodeText = new RichTextWidget()
                .horizontalCenter()
                .fullWidth()
                .height(10)
                .top(4)
                .alignment(Alignment.CENTER)
                .autoUpdate(true);
        gamemodeText.textBuilder(text -> {
            // Both of these uses translation strings already in Minecraft
            if (selectedMode != GameType.NOT_SET) {
                text.add(IKey.lang("gameMode." + selectedMode.getName()));
            } else {
                text.add(IKey.lang("selectWorld.gameMode"));
            }
        });
        panel.child(gamemodeText);

        // RichTextWidget is currently broken and won't center the text correctly
        // this will be fixed in the next modularui update
        RichTextWidget keybindNextText = new RichTextWidget()
                .horizontalCenter()
                .fullWidth()
                .height(10)
                .bottom(4)
                .alignment(Alignment.CENTER)
                .textBuilder(text -> text.add(IKey.lang("ui.switcher.keybind_next", IKey.lang("ui.switcher.brackets", ModernSwitcherMod.switcherKeybind.getDisplayName()))));
        panel.child(keybindNextText);

        return panel;
    }
}
