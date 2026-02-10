package com.xhall.integrations;

import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

public interface ISelectMenu {
    String getId();

    void execute(StringSelectInteractionEvent event);
}
