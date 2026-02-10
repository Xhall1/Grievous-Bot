package com.xhall.commands.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xhall.commands.ICommand;
import com.xhall.services.SwapiService;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;
import java.util.List;

public class Planet implements ICommand {

    private final SwapiService swapiService = new SwapiService();

    @Override
    public String getName() {
        return "planets";
    }

    @Override
    public String getDescription() {
        return "Choose a planet you would like to visit";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.singletonList(
                new OptionData(
                        OptionType.INTEGER,
                        "page",
                        "Choose a page",
                        true
                )
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        int page = event.getOption("page") != null ? event.getOption("page").getAsInt() : 1;

        JsonArray planets = swapiService.getPlanetsList(page);

        if(planets == null || planets.size() == 0)
        {
            event.getHook().sendMessage("Page doesn't exist").queue();
            return;
        }

        EmbedBuilder embed = EmbedUtils.create();

        embed.setTitle("Planets list: " + page);

        StringBuilder builder = new StringBuilder();

        for(JsonElement element : planets)
        {
            JsonObject planet = element.getAsJsonObject();

            String name = planet.get("name").getAsString();
            String climate = planet.get("climate").getAsString();

            builder.append("Name: ")
                    .append(name + "\n")
                    .append("Climate: ")
                    .append(climate + "\n");
        }

        embed.setDescription(builder.toString());
        event.getHook().sendMessageEmbeds(embed.build()).queue();


    }
}
