package com.xhall.commands.fun;

import com.xhall.commands.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Duel implements ICommand {

    @Override
    public String getName() {
        return "duel";
    }

    @Override
    public String getDescription() {
        return "Have a fantastic duel against Grievous!";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
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
