package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.IThemeApi;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.drawable.ItemDrawable;
import com.cleanroommc.modularui.screen.CustomModularScreen;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.BoolValue;
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

    private final BoolValue creative = new BoolValue(false);
    private final BoolValue survival = new BoolValue(false);
    private final BoolValue adventure = new BoolValue(false);
    private final BoolValue spectator = new BoolValue(false);

    private void updateSelectedModeValues() {
        switch (this.selectedMode) {
            case CREATIVE:
                this.creative.setBoolValue(true);
                this.survival.setBoolValue(false);
                this.adventure.setBoolValue(false);
                this.spectator.setBoolValue(false);
            case SURVIVAL:
                this.creative.setBoolValue(false);
                this.survival.setBoolValue(true);
                this.adventure.setBoolValue(false);
                this.spectator.setBoolValue(false);
            case ADVENTURE:
                this.creative.setBoolValue(false);
                this.survival.setBoolValue(false);
                this.adventure.setBoolValue(true);
                this.spectator.setBoolValue(false);
            case SPECTATOR:
                this.creative.setBoolValue(false);
                this.survival.setBoolValue(false);
                this.adventure.setBoolValue(false);
                this.spectator.setBoolValue(true);
            default:
                this.creative.setBoolValue(false);
                this.survival.setBoolValue(false);
                this.adventure.setBoolValue(false);
                this.spectator.setBoolValue(false);
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
            case SURVIVAL:
                this.selectedMode = GameType.ADVENTURE;
                this.updateSelectedModeValues();
            case ADVENTURE:
                this.selectedMode = GameType.SPECTATOR;
                this.updateSelectedModeValues();
            case SPECTATOR:
                this.selectedMode = GameType.CREATIVE;
                this.updateSelectedModeValues();
            default:
                this.selectedMode = GameType.CREATIVE;
                this.updateSelectedModeValues();
        }
    }

    @Nonnull
    @Override
    public ModularPanel buildUI(ModularGuiContext context) {
        Minecraft minecraft = context.getMC();

        ModularPanel panel = ModularPanel.defaultPanel("switcher_panel").size(125, 75);

        // RichTextWidget is currently broken and won't center the text correctly
        // this will be fixed in the next modularui update
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


        ModeIconWidget creative = new ModeIconWidget()
                .value(this.creative)
                .stateCount(1)
                .size(24)
                .disableHoverBackground()
                .disableHoverOverlay()
                .disableHoverThemeBackground(true)
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .stateOverlay(true, Assets.SWITCHER_SLOT_SELECTED)
                .child(new ItemDrawable(Blocks.GRASS).asWidget().center())
                .onMouseHoverListener(listener -> {
                    this.selectedMode = GameType.CREATIVE;
                    this.updateSelectedModeValues();
                });

        ModeIconWidget survival = new ModeIconWidget()
                .value(this.survival)
                .stateCount(1)
                .size(24)
                .disableHoverBackground()
                .disableHoverOverlay()
                .disableHoverThemeBackground(true)
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .stateOverlay(true, Assets.SWITCHER_SLOT_SELECTED)
                .child(new ItemDrawable(Items.IRON_SWORD).asWidget().center())
                .onMouseHoverListener(listener -> {
                    this.selectedMode = GameType.SURVIVAL;
                    this.updateSelectedModeValues();
                });

        ModeIconWidget adventure = new ModeIconWidget()
                .value(this.adventure)
                .stateCount(1)
                .size(24)
                .disableHoverBackground()
                .disableHoverOverlay()
                .disableHoverThemeBackground(true)
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .stateOverlay(true, Assets.SWITCHER_SLOT_SELECTED)
                .child(new ItemDrawable(Items.MAP).asWidget().center())
                .onMouseHoverListener(listener -> {
                    this.selectedMode = GameType.ADVENTURE;
                    this.updateSelectedModeValues();
                });

        ModeIconWidget spectator = new ModeIconWidget()
                .value(this.spectator)
                .stateCount(1)
                .size(24)
                .disableHoverBackground()
                .disableHoverOverlay()
                .disableHoverThemeBackground(true)
                .disableThemeBackground(true)
                .background(Assets.SWITCHER_SLOT)
                .stateOverlay(true, Assets.SWITCHER_SLOT_SELECTED)
                .child(Assets.SPECTATOR_ICON.asWidget().center())
                .onMouseHoverListener(listener -> {
                    this.selectedMode = GameType.SPECTATOR;
                    this.updateSelectedModeValues();
                });

        Flow modeButtons = Flow.row()
                .paddingLeft(4)
                .paddingRight(4)
                .height(24)
                .center()
                .childPadding(6)
                .mainAxisAlignment(Alignment.MainAxis.CENTER)
                .child(creative)
                .child(survival)
                .child(adventure)
                .child(spectator);
        panel.child(modeButtons);

        panel.onUpdateListener(listener -> {
            if (!Keyboard.isKeyDown(Keyboard.KEY_F3)) {
                this.attemptSwitchMode(minecraft);
                panel.closeIfOpen();
            }
            if (ModernSwitcherMod.switcherKeybind.isPressed()) {
                advanceMode();
            }
        });

        return panel;
    }
}
