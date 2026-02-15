package com.xhall.commands.modals;

import com.xhall.commands.interfaces.ICommand;
import net.dv8tion.jda.api.components.label.Label;
import net.dv8tion.jda.api.components.textinput.TextInput;
import net.dv8tion.jda.api.components.textinput.TextInputStyle;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.modals.Modal;

import java.util.Collections;
import java.util.List;

public class Feedback implements ICommand {

    @Override
    public String getName() {
        return "feedback";
    }

    @Override
    public String getDescription() {
        return "Send a feedback to Grievous bot";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        TextInput subject = TextInput
                .create(
                        "feedback-text",
                        TextInputStyle.PARAGRAPH)
                .setPlaceholder("Type here")
                .setMinLength(10)
                .setMaxLength(100)
                .build();

        Modal modal = Modal
                .create(
                        "modal:feedback",
                        "Send feedback")
                .addComponents(Label.of("Subject", subject))
                .build();

        event.replyModal(modal).queue();
        }
}
