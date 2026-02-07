package com.xhall;

import com.xhall.commands.CommandManager;
import com.xhall.commands.fun.Collect;
import com.xhall.commands.fun.Duel;
import com.xhall.commands.fun.Kill;
import com.xhall.commands.utility.Ping;
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
                .build();

    }
}
