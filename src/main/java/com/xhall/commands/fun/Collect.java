package com.xhall.commands.fun;

import com.xhall.commands.interfaces.ICommand;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
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

        if(option == null)
        {
            EmbedBuilder embedError = EmbedUtils.create();

            embedError.setColor(Color.RED);
            embedError.setTitle("X Error");
            embedError.setDescription("What! You should tell me what to collect.");

            event.replyEmbeds(embedError.build()).queue();
        }

        String item = option.getAsString();

        EmbedBuilder embed = EmbedUtils.create();

        if(!item.toLowerCase().contains("lightsaber"))
        {
            embed.setColor(Color.RED);
            embed.setDescription("I dont want that s***. I want lightsabers ");

            event.replyEmbeds(embed.build()).queue();
        }
        else
        {
            embed.setColor(Color.GREEN);
            embed.setDescription(
                    "A fine addition to my collection... I'll save this "
                    + item + " !"
            );

            event.replyEmbeds(embed.build()).queue();
        }
    }
}
