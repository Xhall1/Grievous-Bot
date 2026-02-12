package com.xhall.commands.fun;

import com.xhall.commands.interfaces.ICommand;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
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

        EmbedBuilder embedIsBot = EmbedUtils.create();

        if(victim.isBot())
        {
            embedIsBot.setDescription("Are you an Ass****??");

            event.replyEmbeds(embedIsBot.build()).queue();

            return;
        }

        EmbedBuilder embedLooking = EmbedUtils.create();

        embedLooking.setDescription("Looking for the victim...");

        event.replyEmbeds(embedLooking.build()).queue();

        String[] killingList =
                {
                        "Target acquired",
                        "Almost there!",
                        "I GOT YOU " + victim.getAsMention() + ". You will die!!"
                };

         int delay = 2;

        for(String list : killingList)
        {
            EmbedBuilder embed = EmbedUtils.create();
            embed.setDescription(list);

            if (list.contains("I GOT YOU"))
            {
                embed.setColor(Color.RED);
                embed.setImage(
                        victim.getEffectiveAvatarUrl()
                );
                embed.setThumbnail(
                        "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/c52cfd32-9473-4939-abb6-1aed39529c1f/dgcvgft-aba81e68-e423-4b44-81e6-f6efe02dae67.png/v1/fill/w_1146,h_698/general_grievous_vector_9_by_homersimpson1983_dgcvgft-pre.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MjUyMCIsInBhdGgiOiJcL2ZcL2M1MmNmZDMyLTk0NzMtNDkzOS1hYmI2LTFhZWQzOTUyOWMxZlwvZGdjdmdmdC1hYmE4MWU2OC1lNDIzLTRiNDQtODFlNi1mNmVmZTAyZGFlNjcucG5nIiwid2lkdGgiOiI8PTQxMzcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.SAZ-uULpR460S_t8OAy6B3enb27oSgYZVX0BeFTskU0"
                );
            }
            else
            {
                embed.setColor(Color.ORANGE);
            }

            event.getHook().editOriginalEmbeds(embed.build()).queueAfter(delay, TimeUnit.SECONDS);

            delay += 2;

        }


    }
}
