package com.xhall.commands.admin;

import com.xhall.commands.interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class CreateRole implements ICommand {
    @Override
    public String getName() {
        return "create-role";
    }

    @Override
    public String getDescription() {
        return "Create a new role in the universe";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(
                        OptionType.STRING,
                        "name",
                        "Role name",
                        true)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String name = event.getOption("name").getAsString();

        if (!event.getMember().hasPermission(Permission.MANAGE_ROLES)) {
            event.reply("You don't have high enough rank to give orders here, soldier.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        event.getGuild().createRole()
                .setName(name)
                .setHoisted(true)
                .queue(role -> {
                    event.reply("Role created: " + role.getAsMention()).queue();
                }, error -> {
                    event.reply("You cannot create a role. Verify permissions.").queue();
                });
    }

    @Override
    public DefaultMemberPermissions getPermissions() {
        return DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR);
    }
}
