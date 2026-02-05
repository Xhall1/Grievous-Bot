package com.xhall;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listeners extends ListenerAdapter {

//    @Override
//    public void onReady(ReadyEvent event) {
//        JDA jda = event.getJDA();
//        for(Guild guild : jda.getGuilds())
//        {
//            for(TextChannel channel : guild.getTextChannels())
//            {
//                channel.sendMessage("Hello there! :)").queue();
//            }
//        }
//    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getAuthor().isBot()) return;
        MessageChannel msg = event.getChannel();

        msg.sendMessage(event.getMessage().getContentRaw()).queue();
    }
}
