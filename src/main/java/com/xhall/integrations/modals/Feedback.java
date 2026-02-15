package com.xhall.integrations.modals;

import com.xhall.integrations.modals.interfaces.IModal;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class Feedback implements IModal {

    @Override
    public String getId() {
        return "modal:feedback";
    }

    @Override
    public void execute(ModalInteractionEvent event) {
        String feedback = event.getValue("feedback-text").getAsString();

        event.reply("Thank you for your opinion: " + feedback).queue();
    }
}
