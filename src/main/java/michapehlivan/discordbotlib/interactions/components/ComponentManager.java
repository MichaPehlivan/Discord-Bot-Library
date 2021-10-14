package michapehlivan.discordbotlib.interactions.components;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {
    public Map<String, DiscordButton> discordbuttons = new HashMap<>();
    public Map<String, DiscordSelectMenu> selectmenus = new HashMap<>();

    //add button
    public void addButton(DiscordButton button){
        discordbuttons.put(button.customId(), button);
    }

    //remove button through object
    public void removeButton(DiscordButton button){
        discordbuttons.remove(button.customId());
    }

    //remove button through id
    public void removeButton(String customId){
        discordbuttons.remove(customId);
    }

    //add select menu
    public void addSelectMenu(DiscordSelectMenu menu){
        selectmenus.put(menu.customId(), menu);
    }

    //remove select menu through object
    public void removeSelectMenu(DiscordSelectMenu menu){
        selectmenus.remove(menu.customId());
    }

    //remove select menu through id
    public void removeSelectMenu(String customId){
        selectmenus.remove(customId);
    }
}
