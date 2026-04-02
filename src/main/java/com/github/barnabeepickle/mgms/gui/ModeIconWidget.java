package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.widgets.CycleButtonWidget;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ModeIconWidget extends CycleButtonWidget {
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
}
