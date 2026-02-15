package com.xhall;

import com.xhall.commands.managers.CommandManager;
import com.xhall.integrations.managers.InteractionManager;
import com.xhall.integrations.welcome.WelcomeListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDA jda = JDABuilder
                .createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new CommandManager())
                .addEventListeners(new InteractionManager())
                .addEventListeners(new WelcomeListener())
                .build();

    }
}
