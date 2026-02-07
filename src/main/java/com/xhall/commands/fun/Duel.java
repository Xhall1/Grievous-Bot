package com.xhall.commands.fun;

import com.xhall.commands.ICommand;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
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

        EmbedBuilder embedLoading = EmbedUtils.create();

        embedLoading.setTitle("Let's go to fight");
        embedLoading.setDescription("Fighting...");

        event.replyEmbeds(embedLoading.build()).queue();

        int grievousNum = (int) (Math.random() * 10) + 1;

        EmbedBuilder embed = EmbedUtils.create();

        if(grievousNum > 5)
        {
            embed.setColor(Color.RED);
            embed.setTitle("Looser");
            embed.setDescription("Ha,ha. I won, I'll make you suffer, little boy!");
            embed.setImage("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/fb6a00b4-9cdc-401d-9a61-41fdbe9d09ce/dfn96gy-faa49b51-db32-4162-9479-f874052a95c5.png/v1/fill/w_1280,h_1142/general_grievous__render__by_yessing_dfn96gy-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTE0MiIsInBhdGgiOiJcL2ZcL2ZiNmEwMGI0LTljZGMtNDAxZC05YTYxLTQxZmRiZTlkMDljZVwvZGZuOTZneS1mYWE0OWI1MS1kYjMyLTQxNjItOTQ3OS1mODc0MDUyYTk1YzUucG5nIiwid2lkdGgiOiI8PTEyODAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.suYyYd0rUO9DqddgDFm9pNkB-8ZrTpudUJUo52IIFW0");
        }
        else
        {
            embed.setColor(Color.GREEN);
            embed.setTitle("You won");
            embed.setDescription("I won't let you win... (Escape)");
            embed.setImage("https://i.imgflip.com/8p3tvp.png");
        }

        event.getHook().editOriginalEmbeds(
                embed.build()
        ).queueAfter(2, TimeUnit.SECONDS);
    }
}
