package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.api.drawable.IDrawable;
import com.cleanroommc.modularui.api.value.IIntValue;
import com.cleanroommc.modularui.screen.RichTooltip;
import com.cleanroommc.modularui.widgets.AbstractCycleButtonWidget;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModeIconWidget extends AbstractCycleButtonWidget<ModeIconWidget> {
    @Nullable
    private Consumer<ModeIconWidget> onMouseEnterHandler;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.onMouseEnterHandler != null) {
            this.onMouseEnterHandler.accept(this);
        }
    }

    @Override
    public @Nonnull Result onMousePressed(int mouseButton) {
        return Result.IGNORE;
    }

    @Override
    public void onMouseEnterArea() {
        super.onMouseEnterArea();

    }

    @Nullable
    public Consumer<ModeIconWidget> getOnMouseEnterHandler() {
        return this.onMouseEnterHandler;
    }

    public ModeIconWidget onMouseEnterHandler(Consumer<ModeIconWidget> listener) {
        return onMouseEnterHandler(listener, false);
    }

    public ModeIconWidget onMouseEnterHandler(Consumer<ModeIconWidget> listener, boolean merge) {
        if (merge && this.onMouseEnterHandler != null) {
            final Consumer<ModeIconWidget> oldListener = this.onMouseEnterHandler;
            if (listener != null) {
                this.onMouseEnterHandler = w -> {
                    oldListener.accept(w);
                    listener.accept(w);
                };
            }
        } else {
            this.onMouseEnterHandler = listener;
        }
        return this;
    }

    // The following is just from CycleButtonWidget

    @Override
    public ModeIconWidget value(IIntValue<?> value) {
        return super.value(value);
    }

    public ModeIconWidget stateBackground(int state, IDrawable drawable) {
        this.background = addToArray(this.background, drawable, state);
        return disableThemeBackground(true);
    }

    public ModeIconWidget stateHoverBackground(int state, IDrawable drawable) {
        this.hoverBackground = addToArray(this.hoverBackground, drawable, state);
        return disableHoverThemeBackground(true);
    }

    public ModeIconWidget stateOverlay(int state, IDrawable drawable) {
        this.overlay = addToArray(this.overlay, drawable, state);
        return getThis();
    }

    public ModeIconWidget stateHoverOverlay(int state, IDrawable drawable) {
        this.hoverOverlay = addToArray(this.hoverOverlay, drawable, state);
        return getThis();
    }

    public ModeIconWidget stateBackground(boolean state, IDrawable drawable) {
        return stateBackground(state ? 1 : 0, drawable);
    }

    public ModeIconWidget stateHoverBackground(boolean state, IDrawable drawable) {
        return stateHoverBackground(state ? 1 : 0, drawable);
    }

    public ModeIconWidget stateOverlay(boolean state, IDrawable drawable) {
        return stateOverlay(state ? 1 : 0, drawable);
    }

    public ModeIconWidget stateHoverOverlay(boolean state, IDrawable drawable) {
        return stateHoverOverlay(state ? 1 : 0, drawable);
    }

    public <T extends Enum<T>> ModeIconWidget stateBackground(T state, IDrawable drawable) {
        return stateBackground(state.ordinal(), drawable);
    }

    public <T extends Enum<T>> ModeIconWidget stateHoverBackground(T state, IDrawable drawable) {
        return stateHoverBackground(state.ordinal(), drawable);
    }

    public <T extends Enum<T>> ModeIconWidget stateOverlay(T state, IDrawable drawable) {
        return stateOverlay(state.ordinal(), drawable);
    }

    public <T extends Enum<T>> ModeIconWidget stateHoverOverlay(T state, IDrawable drawable) {
        return stateHoverOverlay(state.ordinal(), drawable);
    }

    @Override
    public ModeIconWidget addTooltip(int state, String tooltip) {
        return super.addTooltip(state, tooltip);
    }

    @Override
    public ModeIconWidget addTooltip(int state, IDrawable tooltip) {
        return super.addTooltip(state, tooltip);
    }

    public ModeIconWidget length(int length) {
        return stateCount(length);
    }

    @Override
    public ModeIconWidget stateCount(int stateCount) {
        return super.stateCount(stateCount);
    }

    @Override
    public ModeIconWidget tooltip(int index, Consumer<RichTooltip> builder) {
        return super.tooltip(index, builder);
    }

    @Override
    public ModeIconWidget tooltipBuilder(int index, Consumer<RichTooltip> builder) {
        return super.tooltipBuilder(index, builder);
    }
}
