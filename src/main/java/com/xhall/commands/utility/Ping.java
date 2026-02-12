package com.xhall.commands.utility;

import com.xhall.commands.interfaces.ICommand;
import com.xhall.utils.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class Ping implements ICommand {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Bot latency";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

        EmbedBuilder embed = EmbedUtils.create();

        long ping = event.getJDA().getGatewayPing();

        embed.setDescription(
                "Time to process: "
                        + ping
                        + "ms. You are a bold one"
        );

        event.replyEmbeds(embed.build()).queue();

    }
}
