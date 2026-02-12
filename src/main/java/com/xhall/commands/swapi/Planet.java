package com.xhall.commands.swapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xhall.commands.interfaces.ICommand;
import com.xhall.services.api.SwapiService;
import net.dv8tion.jda.api.components.actionrow.ActionRow;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
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
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        JsonArray planets = swapiService.getPlanetsList(1);

        if(planets == null)
        {
            event.getHook().sendMessage("Page doesn't exist").queue();
            return;
        }


        StringSelectMenu.Builder menu = StringSelectMenu.create("planet");
        menu.setPlaceholder("Choose a destination...");
        menu.setMinValues(1);
        menu.setMaxValues(1);

        for(JsonElement element : planets)
        {
            JsonObject planet = element.getAsJsonObject();
            String name = planet.get("name").getAsString();
            String terrain = planet.get("terrain").getAsString();

            if(terrain.length() > 100 ) terrain = terrain.substring(0, 97) + "...";

            menu.addOption(name, name, terrain);
        }

        event.getHook()
                .sendMessage("Select the planet:")
                .addComponents(ActionRow.of(menu.build()))
                .queue();

    }
}
