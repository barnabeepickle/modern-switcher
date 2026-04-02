package com.github.barnabeepickle.mgms.gui;

import com.cleanroommc.modularui.drawable.UITexture;

public class Assets {
    public static UITexture SWITCHER_SLOT = UITexture.builder()
            .location("minecraft", "textures/gui/widgets.png")
            .imageSize(256, 256)
            .iconColorType()
            .nonOpaque()
            .name("switcher_slot")
            .subAreaXYWH(59, 22, 24, 24)
            .build();

    public static UITexture SWITCHER_SLOT_SELECTED = UITexture.builder()
            .location("minecraft", "textures/gui/widgets.png")
            .imageSize(256, 256)
            .iconColorType()
            .nonOpaque()
            .name("switcher_slot_selected")
            .subAreaXYWH(0, 22, 24, 24)
            .build();

    public static UITexture SPECTATOR_ICON = UITexture.builder()
            .location("minecraft", "textures/gui/spectator_widgets.png")
            .imageSize(256, 256)
            .iconColorType()
            .name("spectator_icon")
            .subAreaXYWH(0, 0, 16, 16)
            .build();
}
