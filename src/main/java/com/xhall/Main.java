package com.xhall;

import com.xhall.commands.Ping;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

public class Main {
    public static void main(String[] args) {
        Dotenv config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        JDA jda = JDABuilder
                .createDefault(token)
                .build();

        jda.addEventListener(new Listeners());
        jda.addEventListener(new Ping());
    }
}
