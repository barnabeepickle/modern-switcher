package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.widget.Interactable;
import com.cleanroommc.modularui.screen.viewport.ModularGuiContext;
import com.cleanroommc.modularui.theme.WidgetThemeEntry;
import com.cleanroommc.modularui.widget.ParentWidget;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModeSelectWidget extends ParentWidget<ModeSelectWidget> implements Interactable {

    // This section is for running a Consumer if the user's mouse goes over the widget

    @Nullable
    private Consumer<ModeSelectWidget> onMouseInAreaListener;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isEnabled() && this.isBelowMouse()) {
            if (this.onMouseInAreaListener != null) {
                this.onMouseInAreaListener.accept(this);
            }
        }
    }

    @Override
    public @Nonnull Interactable.Result onMousePressed(int mouseButton) {
        return Interactable.Result.IGNORE;
    }

    @Nullable
    public Consumer<ModeSelectWidget> getOnMouseInAreaListener() {
        return this.onMouseInAreaListener;
    }

    public ModeSelectWidget onMouseInAreaListener(Consumer<ModeSelectWidget> listener) {
        return onMouseInAreaListener(listener, false);
    }

    public ModeSelectWidget onMouseInAreaListener(Consumer<ModeSelectWidget> listener, boolean merge) {
        if (merge && this.onMouseInAreaListener != null) {
            final Consumer<ModeSelectWidget> oldListener = this.onMouseInAreaListener;
            if (listener != null) {
                this.onMouseInAreaListener = w -> {
                    oldListener.accept(w);
                    listener.accept(w);
                };
            }
        } else {
            this.onMouseInAreaListener = listener;
        }
        return this;
    }

    // This section is for enabling/disabling the overlay

    private boolean overlayEnabled = true;

    public ModeSelectWidget disableOverlay() {
        this.overlayEnabled = false;
        return this;
    }

    public ModeSelectWidget enableOverlay() {
        this.overlayEnabled = true;
        return this;
    }

    public ModeSelectWidget setOverlay(boolean enable) {
        this.overlayEnabled = enable;
        return this;
    }

    public boolean isOverlayEnabled() {
        return this.overlayEnabled;
    }

    @Override
    public void drawOverlay(ModularGuiContext context, WidgetThemeEntry<?> widgetTheme) {
        if (this.overlayEnabled) {
            super.drawOverlay(context, widgetTheme);
        }
    }
}
