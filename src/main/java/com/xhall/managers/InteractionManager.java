package com.xhall.managers;

import com.xhall.integrations.ISelectMenu;
import com.xhall.integrations.menus.PeopleMenu;
import com.xhall.integrations.menus.PlanetMenu;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class InteractionManager extends ListenerAdapter {

    private final Map<String, ISelectMenu> menus = new HashMap<>();

    public InteractionManager()
    {
        addMenu(new PlanetMenu());
        addMenu(new PeopleMenu());
    }

    private void addMenu(ISelectMenu menu)
    {
        menus.put(menu.getId(), menu);
    }

    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        String id = event.getComponentId();

        if(menus.containsKey(id))
        {
            menus.get(id).execute(event);
        }
        else
        {
            event.reply("Error").setEphemeral(true).queue();
        }
    }
}
