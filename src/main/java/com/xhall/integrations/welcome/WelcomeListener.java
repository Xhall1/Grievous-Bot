package com.xhall.integrations.welcome;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.Color;
import java.time.Instant;
import java.util.List;

public class WelcomeListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        Guild guild = event.getGuild();
        Member member = event.getMember();

        List<TextChannel> channels = guild.getTextChannelsByName("welcome", true);
        if (channels.isEmpty()) {
            channels = guild.getTextChannelsByName("general", true);
        }

        if (channels.isEmpty()) return;

        TextChannel channel = channels.get(0);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("New Member Arrived!");
        embed.setDescription(
                        "Welcome " +
                        member.getAsMention() +
                        " to " + guild.getName() + "!");
        embed.setThumbnail(member.getUser().getAvatarUrl());
        embed.setColor(Color.CYAN);
        embed.setTimestamp(Instant.now());
        embed.setFooter("Member count: " + guild.getMemberCount());

        channel.sendMessageEmbeds(embed.build()).queue();
    }


}
