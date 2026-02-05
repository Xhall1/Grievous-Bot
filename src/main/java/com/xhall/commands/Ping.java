package com.xhall.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("ping")) return;

        long ping = event.getJDA().getGatewayPing();

        event.reply("Time to process: " + ping + "ms. You are a bold one").queue();
    }
}
