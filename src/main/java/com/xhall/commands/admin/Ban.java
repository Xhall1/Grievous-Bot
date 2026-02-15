package com.xhall.commands.admin;

import com.xhall.commands.interfaces.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ban implements ICommand {

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getDescription() {
        return "Ban a user from the server";
    }

    @Override
    public List<OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER, "user", "The user to ban", true),
                new OptionData(OptionType.STRING, "reason", "The reason for the ban", false),
                new OptionData(OptionType.INTEGER, "days", "Delete messages from past X days (0-7)", false)
        );
    }

    @Override
    public DefaultMemberPermissions getPermissions() {
        return DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS);
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member selfMember = event.getGuild().getSelfMember();
        Member target = event.getOption("user").getAsMember();

        if (target == null) {
            event.reply("User not found in this server.").setEphemeral(true).queue();
            return;
        }

        if (!selfMember.canInteract(target)) {
            event.reply("I cannot ban this user because they have a higher or equal role.").setEphemeral(true).queue();
            return;
        }

        if (!event.getMember().canInteract(target)) {
            event.reply("You cannot ban this user because they have a higher or equal role than you.").setEphemeral(true).queue();
            return;
        }

        OptionMapping reasonOption = event.getOption("reason");
        String reason = reasonOption != null ? reasonOption.getAsString() : "No reason provided";

        OptionMapping daysOption = event.getOption("days");
        int days = daysOption != null ? daysOption.getAsInt() : 0;
        if (days < 0) days = 0;
        if (days > 7) days = 7;

        event.getGuild().ban(target, days, TimeUnit.DAYS).reason(reason).queue(
                success -> event.reply("Banned " + target.getAsMention() + " for: " + reason).queue(),
                error -> event.reply("Failed to ban user.").setEphemeral(true).queue()
        );
    }
}