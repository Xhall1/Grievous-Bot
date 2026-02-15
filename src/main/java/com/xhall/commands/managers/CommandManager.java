package com.xhall.commands.managers;

import com.xhall.commands.admin.AssignRole;
import com.xhall.commands.admin.Ban;
import com.xhall.commands.admin.CreateRole;
import com.xhall.commands.fun.Collect;
import com.xhall.commands.fun.Duel;
import com.xhall.commands.fun.Kill;
import com.xhall.commands.interfaces.ICommand;
import com.xhall.commands.modals.Feedback;
import com.xhall.commands.swapi.People;
import com.xhall.commands.swapi.Planet;
import com.xhall.commands.swapi.Starship;
import com.xhall.commands.utility.Ping;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        add(new Ping());
        add(new Collect());
        add(new Duel());
        add(new Kill());
        add(new Planet());
        add(new People());
        add(new Starship());
        add(new Feedback());
        add(new CreateRole());
        add(new AssignRole());
        add(new Ban());
    }

    @Override
    public void onReady(ReadyEvent event) {
        List<CommandData> commandDataList = new ArrayList<>();

        for (ICommand command : commands) {
            SlashCommandData data = Commands.slash(command.getName(), command.getDescription());

            if (command.getOptions() != null && !command.getOptions().isEmpty()) {
                data.addOptions(command.getOptions());
            }

            if (command.getPermissions() != null) {
                data.setDefaultPermissions(command.getPermissions());
            }

            commandDataList.add(data);
        }

        for (Guild guild : event.getJDA().getGuilds()) {
            guild.updateCommands().addCommands(commandDataList).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        for (ICommand command : commands) {
            if (command.getName().equals(event.getName())) {
                command.execute(event);
                return;
            }
        }
    }

    public void add(ICommand command) {
        commands.add(command);
    }
}