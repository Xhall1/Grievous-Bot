package com.xhall.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Collect extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("collect")) return;

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
