package com.xhall.managers;

import com.xhall.integrations.buttons.StarshipButton;
import com.xhall.integrations.buttons.interfaces.IButton;
import com.xhall.integrations.menus.StarshipMenu;
import com.xhall.integrations.menus.interfaces.ISelectMenu;
import com.xhall.integrations.menus.PeopleMenu;
import com.xhall.integrations.menus.PlanetMenu;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Map;

public class InteractionManager extends ListenerAdapter {

    private final Map<String, ISelectMenu> menus = new HashMap<>();
    private final Map<String, IButton> buttons = new HashMap<>();

    public InteractionManager()
    {
        addMenu(new PlanetMenu());
        addMenu(new PeopleMenu());

        addButton(new StarshipButton());
    }

    private void addMenu(ISelectMenu menu)
    {
        menus.put(menu.getId(), menu);
    }

    private void addButton(IButton button)
    {
        buttons.put(button.getId(), button);
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

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        String[] idParts = event.getComponentId().split(":");

        String prefix = idParts[0];

        if (buttons.containsKey(prefix))
        {
            buttons.get(prefix).execute(event);
        }
        else
        {
            event.reply("Error: Button doesn't load...").setEphemeral(true).queue();
        }

    }
}
