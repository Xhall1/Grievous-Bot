package com.xhall.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private List<ICommand> commands = new ArrayList<>();

    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guild : event.getJDA().getGuilds())
        {
            for(ICommand command : commands)
            {
                var commandData = guild.upsertCommand(
                        command.getName(),
                        command.getDescription());

                if(command.getOptions() != null && !command.getOptions().isEmpty())
                {
                    commandData.addOptions(
                            command.getOptions()
                    );
                }

                commandData.queue();
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for(ICommand command : commands)
        {
            if(command.getName().equals(event.getName()))
            {
                command.execute(event);
                return;
            }
        }
    }

    public void add(ICommand command)
    {
        commands.add(command);
    }
}
