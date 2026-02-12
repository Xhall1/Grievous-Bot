package com.xhall.commands.swapi;

import com.xhall.commands.interfaces.ICommand;
import com.xhall.integrations.menus.StarshipMenu;
import com.xhall.services.api.SwapiService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collections;
import java.util.List;

public class Starship implements ICommand {

    private final SwapiService swapiService = new SwapiService();

    @Override
    public String getName() {
        return "starships";
    }

    @Override
    public String getDescription() {
        return "Look every single starship in the universe";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        event.getHook()
                .sendMessageEmbeds(StarshipMenu.createEmbed(1))
                .setComponents(StarshipMenu.createButtons(1))
                .queue();

    }
}
