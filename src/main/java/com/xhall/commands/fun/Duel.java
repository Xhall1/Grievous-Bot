package com.xhall.commands.fun;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class Duel extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getName().equals("duel")) return;

        event.reply("Fighting...").queue();

        int grievousNum = (int) (Math.random() * 10) + 1;

        if(grievousNum > 5)
        {
            event.getHook().editOriginal("Ha,ha. I won, I'll make you suffer, little boy!")
                    .queueAfter(2, TimeUnit.SECONDS);
        }
        else
        {
            event.getHook().editOriginal("I won't let you win... (Escape)")
                    .queueAfter(2, TimeUnit.SECONDS);
        }
    }
}
