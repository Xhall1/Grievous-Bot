package com.xhall.integrations.buttons.interfaces;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface IButton {
    String getId();

    void execute(ButtonInteractionEvent event);
}
