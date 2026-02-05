package com.xhall.commands.fun;

import com.xhall.commands.ICommand;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Kill implements ICommand {

    @Override
    public String getName() {
        return "kill";
    }

    @Override
    public String getDescription() {
        return "Send Grievous to kill a user!";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();

        data.add(
                new OptionData(
                        OptionType.USER,
                        "user",
                        "type a user who's in the server"
                )
        );
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
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
