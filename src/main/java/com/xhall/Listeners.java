package com.xhall;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class Listeners extends ListenerAdapter {
    Dotenv config = Dotenv.configure().load();
    Long serverID = Long.valueOf(config.get("SERVER_ID"));

    @Override
    public void onReady(ReadyEvent event) {
        Guild guild = event.getJDA().getGuildById(serverID);

        // Ping Command
        guild.upsertCommand("ping", "Bot latency").queue();

        // Collect (item) Command
        guild.upsertCommand(
                Commands.slash("collect", "Add an item to the collection (I specially recommend you a lightsaber)")
                        .addOption(OptionType.STRING, "item", "Lightsaber to collect", true)
        ).queue();
    }
}
