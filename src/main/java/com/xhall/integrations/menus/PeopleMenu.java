package com.xhall.integrations.menus;

import com.xhall.integrations.menus.interfaces.ISelectMenu;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;

import java.awt.*;

public class PeopleMenu implements ISelectMenu {

    @Override
    public String getId() {
        return "people";
    }

    @Override
    public void execute(StringSelectInteractionEvent event) {
        String peopleName = event.getValues().get(0);

        EmbedBuilder embed = EmbedUtils.create();

        embed.setColor(Color.GREEN);
        embed.setTitle("Character selected");
        embed.setDescription("Fighting against " + peopleName);
        embed.setImage("https://i.pinimg.com/originals/33/9e/55/339e557dbf828c6f3911752d11c04d00.gif");

        event.editMessageEmbeds(embed.build())
                .setComponents()
                .queue();
    }
}
