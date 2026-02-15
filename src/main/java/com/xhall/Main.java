package com.xhall;

import com.xhall.commands.managers.CommandManager;
import com.xhall.integrations.managers.InteractionManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {
    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDA jda = JDABuilder
                .createDefault(token)
                .addEventListeners(new CommandManager())
                .addEventListeners(new InteractionManager())
                .build();

    }
}
