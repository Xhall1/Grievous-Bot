package com.xhall.utils;


import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class EmbedUtils {

    public static EmbedBuilder create()
    {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.LIGHT_GRAY);
        builder.setFooter("Created by: Xhall1");
        return builder;
    }

}
