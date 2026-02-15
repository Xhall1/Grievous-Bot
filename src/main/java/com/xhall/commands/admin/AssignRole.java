package com.xhall.commands.admin;

import com.xhall.commands.interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

public class AssignRole implements ICommand {

    @Override
    public String getName() {
        return "assign-role";
    }

    @Override
    public String getDescription() {
        return "Assign a role to a specific member";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER, "user", "The member to receive the role", true),
                new OptionData(OptionType.ROLE, "role", "The role to assign", true)
        );
    }

    @Override
    public DefaultMemberPermissions getPermissions() {
        return DefaultMemberPermissions.enabledFor(Permission.MANAGE_ROLES);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Guild guild = event.getGuild();
        if (guild == null) return;

        Member target = event.getOption("user").getAsMember();
        Role role = event.getOption("role").getAsRole();

        if (target == null || role == null) {
            event.reply("Error finding user or role.").setEphemeral(true).queue();
            return;
        }

        if (!guild.getSelfMember().canInteract(role)) {
            event.reply("I cannot assign this role because it is higher than or equal to my own top role.")
                    .setEphemeral(true)
                    .queue();
            return;
        }

        guild.addRoleToMember(target, role).queue(
                success -> event.reply("Successfully assigned " + role.getAsMention() + " to " + target.getAsMention()).queue(),
                error -> event.reply("Failed to assign role. Check my permissions.").setEphemeral(true).queue()
        );
    }
}