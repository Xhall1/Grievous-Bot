package com.xhall.integrations.menus;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xhall.services.api.SwapiService;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class StarshipMenu {

    private static final SwapiService swapiService = new SwapiService();

    public static MessageEmbed createEmbed(int page)
    {
        JsonArray starships = swapiService.getStarships(page);

        EmbedBuilder embed = EmbedUtils.create();

        if(starships == null || starships.size() == 0) {
            embed.setColor(Color.RED);
            embed.setDescription("Not ships founded");

            return embed.build();
        }

        StringBuilder builder = new StringBuilder();
        for(JsonElement starship : starships)
        {
            JsonObject s = starship.getAsJsonObject();

            String name = s.get("name").getAsString();
            String credits = s.get("cost_in_credits").getAsString();

            builder.append("** Name:** ")
                    .append(name)
                    .append("\n **Total in credits:** ")
                    .append(credits)
                    .append("\n ---------------------------- \n");
        }

        embed.setDescription(builder.toString());

        return embed.build();
    }

    public static ActionRow createButtons(int page)
    {
        Button btnPrev = Button.secondary("ship:prev:" + (page - 1), "Previous");
        Button btnNext = Button.primary("ship:next:" + (page + 1), "Next");

        if(page <= 1) btnPrev =  btnPrev.asDisabled();

        return ActionRow.of(btnPrev, btnNext);
    }


}
