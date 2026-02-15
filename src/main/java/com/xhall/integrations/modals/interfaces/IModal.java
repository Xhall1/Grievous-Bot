package com.xhall.integrations.modals.interfaces;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public interface IModal {
    String getId();

    void execute(ModalInteractionEvent event);
}
