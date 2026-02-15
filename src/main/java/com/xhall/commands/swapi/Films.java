package com.xhall.commands.swapi;

import com.google.gson.JsonArray;
import com.xhall.commands.interfaces.ICommand;
import com.xhall.services.api.SwapiService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;
import java.util.List;

public class Films implements ICommand {

    private final SwapiService swapiService = new SwapiService();
    @Override
    public String getName() {
        return "films";
    }

    @Override
    public String getDescription() {
        return "Star Wars films";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply();

        JsonArray films = swapiService.getFilms(1);

        if(films == null)
        {
            event.getHook().sendMessage("Page doesn't found");

            return;
        }
    }
}
