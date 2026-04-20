package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.IThemeApi;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.drawable.ItemDrawable;
import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.widgets.RichTextWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.github.barnabeepickle.mgms.ModernSwitcherMod;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class GameModeSwitcher extends CustomModularScreen {
    private static final ArrayList<GameType> modeHistory = new ArrayList<>(0);
    private GameType selectedMode = GameType.NOT_SET;

    public GameModeSwitcher(String owner) {
        super(owner);
        IThemeApi.get().registerThemeForScreen(owner, "switcher_panel", "vanilla_dark");
    }

    private static GameType getCurrentMode(Minecraft minecraft) {
        return minecraft.playerController.getCurrentGameType();
    }

    private void updateSelectedModeValues() {
        this.modeWidgets[0].disableOverlay();
        this.modeWidgets[1].disableOverlay();
        this.modeWidgets[2].disableOverlay();
        this.modeWidgets[3].disableOverlay();

        switch (this.selectedMode) {
            case CREATIVE:
                this.modeWidgets[0].enableOverlay();
                return;
            case SURVIVAL:
                this.modeWidgets[1].enableOverlay();
                return;
            case ADVENTURE:
                this.modeWidgets[2].enableOverlay();
                return;
            case SPECTATOR:
                this.modeWidgets[3].enableOverlay();
                return;
        }
    }

    private void attemptSwitchMode(Minecraft minecraft) {
        if (minecraft.player.canUseCommand(2, "")) {
            if (this.selectedMode != getCurrentMode(minecraft) && !this.selectedMode.equals(GameType.NOT_SET)) {
                minecraft.player.sendChatMessage("/gamemode " + this.selectedMode.getName());
                modeHistory.add(this.selectedMode);
            }
        } else {
            minecraft.debugFeedbackTranslated("debug.mgms.switcher.error.permissions");
        }
    }

    private void advanceMode() {
        switch (this.selectedMode) {
            case CREATIVE:
                this.selectedMode = GameType.SURVIVAL;
                this.updateSelectedModeValues();
                return;
            case SURVIVAL:
                this.selectedMode = GameType.ADVENTURE;
                this.updateSelectedModeValues();
                return;
            case ADVENTURE:
                this.selectedMode = GameType.SPECTATOR;
                this.updateSelectedModeValues();
                return;
            case SPECTATOR:
                this.selectedMode = GameType.CREATIVE;
                this.updateSelectedModeValues();
                return;
        }
    }

    /**
     * A shortcut method to check if the switcher keybind is pressed.
     */
    private static boolean checkKeybindWrapper() {
        return Keyboard.isKeyDown(ModernSwitcherMod.switcherKeybind.getKeyCode());
    }

    /**
     * A variable used to register key presses only once.
     */
    private static int pressTime = 0;

    private ModeSelectWidget[] modeWidgets;

    @Nonnull
    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        Minecraft minecraft = context.getMC();

        ModularPanel panel = ModularPanel.defaultPanel("switcher_panel").size(125, 75);

        RichTextWidget gamemodeText = new RichTextWidget()
                .horizontalCenter()
                .fullWidth()
                .height(10)
                .top(6)
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

        RichTextWidget keybindNextText = new RichTextWidget()
                .horizontalCenter()
                .fullWidth()
                .height(10)
                .bottom(4)
                .alignment(Alignment.CENTER)
                .textBuilder(text -> text.add(IKey.lang("ui.switcher.keybind_next", IKey.lang("ui.switcher.brackets", ModernSwitcherMod.switcherKeybind.getDisplayName()))));
        panel.child(keybindNextText);

        ModeSelectWidget creative2 = new ModeSelectWidget()
                .size(24)
                .disableHoverOverlay()
                .disableHoverBackground()
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .overlay(Assets.SWITCHER_SLOT_SELECTED)
                .disableOverlay()
                .child(new ItemDrawable(Blocks.GRASS).asWidget().center())
                .onMouseInAreaListener(listener -> {
                    this.selectedMode = GameType.CREATIVE;
                    this.updateSelectedModeValues();
                });

        ModeSelectWidget survival2 = new ModeSelectWidget()
                .size(24)
                .disableHoverOverlay()
                .disableHoverBackground()
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .overlay(Assets.SWITCHER_SLOT_SELECTED)
                .disableOverlay()
                .child(new ItemDrawable(Items.IRON_SWORD).asWidget().center())
                .onMouseInAreaListener(listener -> {
                    this.selectedMode = GameType.SURVIVAL;
                    this.updateSelectedModeValues();
                });

        ModeSelectWidget adventure2 = new ModeSelectWidget()
                .size(24)
                .disableHoverOverlay()
                .disableHoverBackground()
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .overlay(Assets.SWITCHER_SLOT_SELECTED)
                .disableOverlay()
                .child(new ItemDrawable(Items.MAP).asWidget().center())
                .onMouseInAreaListener(listener -> {
                    this.selectedMode = GameType.ADVENTURE;
                    this.updateSelectedModeValues();
                });

        ModeSelectWidget spectator2 = new ModeSelectWidget()
                .size(24)
                .disableHoverOverlay()
                .disableHoverBackground()
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .overlay(Assets.SWITCHER_SLOT_SELECTED)
                .disableOverlay()
                .child(Assets.SPECTATOR_ICON.asWidget().center())
                .onMouseInAreaListener(listener -> {
                    this.selectedMode = GameType.SPECTATOR;
                    this.updateSelectedModeValues();
                });

        this.modeWidgets = new ModeSelectWidget[]{creative2, survival2, adventure2, spectator2};

        Flow modeButtons = Flow.row()
                .paddingLeft(4)
                .paddingRight(4)
                .height(24)
                .center()
                .childPadding(6)
                .mainAxisAlignment(Alignment.MainAxis.CENTER)
                .child(creative2)
                .child(survival2)
                .child(adventure2)
                .child(spectator2);
        panel.child(modeButtons);

        panel.onUpdateListener(listener -> {
            // Using pressTime makes it so the key can only trigger actions once
            if (checkKeybindWrapper()) {
                if (pressTime == 0) {
                    advanceMode();
                    ModernSwitcherMod.LOGGER.info("Key triggered");
                    ModernSwitcherMod.LOGGER.info("Current Selected Gamemode: {}", this.selectedMode.getName());
                }
                pressTime++;
            }
            if (!checkKeybindWrapper()) {
                if (pressTime != 0) {
                    pressTime = 0;
                }
            }
            // All of this is because in this context I can't use the normal keybind querying code
        });

        return panel;
    }
}
