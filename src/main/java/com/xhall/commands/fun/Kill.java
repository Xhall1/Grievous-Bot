package com.xhall.commands.fun;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Kill extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("kill")) return;

        User victim = event.getOption("user").getAsUser();

        if(victim.isBot())
        {
            event.reply(
                    "Are you an Ass****??"
            ).queue();

            return;
        }

        event.reply("Looking for the victim...").queue();

        event.getHook()
                .editOriginal("Almost there!")
                .queueAfter(2, TimeUnit.SECONDS);

        event.getHook().editOriginal(
                "I got you " + victim.getAsMention() + ". You will die!!"
        ).queueAfter(3, TimeUnit.SECONDS);

    }
}
