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

public class People implements ICommand {

    private final SwapiService swapiService = new SwapiService();

    @Override
    public String getName() {
        return "peoples";
    }

    @Override
    public String getDescription() {
        return "Select a character to fight against Grievous";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        JsonArray peoples = swapiService.getPeopleList(2);

        if(peoples == null)
        {
            event.getHook().sendMessage("Page doesn't exist").queue();
            return;
        }

        StringSelectMenu.Builder menu = StringSelectMenu.create("people");

        menu.setPlaceholder("Choose a character");
        menu.setMinValues(1);
        menu.setMaxValues(1);

        for(JsonElement element : peoples)
        {
                JsonObject people = element.getAsJsonObject();
                String name = people.get("name").getAsString();
                String gender = people.get("gender").getAsString();

                menu.addOption(name, name, gender);
        }

        event.getHook()
                .sendMessage("Select the character")
                .addComponents(ActionRow.of(menu.build()))
                .queue();
    }
}
