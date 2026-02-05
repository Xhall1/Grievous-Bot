package com.xhall.commands;

import com.xhall.commands.fun.Collect;
import com.xhall.commands.fun.Duel;
import com.xhall.commands.fun.Kill;
import com.xhall.commands.utility.Ping;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private List<ICommand> commands = new ArrayList<>();

    public CommandManager()
    {
        add(new Ping());
        add(new Collect());
        add(new Duel());
        add(new Kill());

    }

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
