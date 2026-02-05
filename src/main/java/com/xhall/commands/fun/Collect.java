package com.xhall.commands.fun;

import com.xhall.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class Collect implements ICommand {

    @Override
    public String getName() {
        return "collect";
    }

    @Override
    public String getDescription() {
        return "Add an item to the collection (I specially recommend you a lightsaber)";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();

        data.add(
                new OptionData(
                        OptionType.STRING,
                        "item",
                        "Lightsaber to collect",
                        true
                )
        );

        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping option = event.getOption("item");

        if(option == null) event
                .reply("What! You should tell me what to collect.")
                .setEphemeral(true)
                .queue();

        String item = option.getAsString();

        if(!item.toLowerCase().contains("lightsaber"))
        {
            event.reply("I dont want that s***. I want lightsabers ").queue();
        }
        else
        {
            event.reply("A fine addition to my collection... I'll save this "
                    + item + " !").queue();
        }
    }
}
