package com.xhall.integrations.buttons;

import com.xhall.commands.swapi.Starship;
import com.xhall.integrations.buttons.interfaces.IButton;
import com.xhall.integrations.menus.StarshipMenu;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class StarshipButton implements IButton {

    @Override
    public String getId() {
        return "ship";
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        String[] idParts = event.getComponentId().split(":");

        int targetPage = Integer.parseInt(idParts[2]);

        event.deferEdit().queue();

        event.getHook()
                .editOriginalEmbeds(StarshipMenu.createEmbed(targetPage))
                .setComponents(StarshipMenu.createButtons(targetPage))
                .queue();

    }
}
