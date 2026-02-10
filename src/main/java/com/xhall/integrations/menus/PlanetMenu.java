package com.xhall.integrations.menus;

import com.xhall.integrations.ISelectMenu;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

import java.awt.*;

public class PlanetMenu implements ISelectMenu {

    @Override
    public String getId() {
        return "planet";
    }

    @Override
    public void execute(StringSelectInteractionEvent event) {
        String planetName = event.getValues().get(0);

        EmbedBuilder embed = EmbedUtils.create();

        embed.setColor(Color.GREEN);
        embed.setTitle("Planet selected");
        embed.setDescription("Traveling to: " + planetName + "...");
        embed.setImage("https://mistergif.com/media/2/halcon-milenario-asteroides-21000.gif");

        event.editMessageEmbeds(embed.build())
                .setComponents()
                .queue();
    }
}
