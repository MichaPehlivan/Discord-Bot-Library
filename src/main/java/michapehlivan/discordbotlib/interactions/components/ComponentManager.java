package michapehlivan.discordbotlib.interactions.components;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing all Buttons and Select Menus
 * @author Micha Pehlivan
 */
public class ComponentManager {

    /**
     * {@link Map} containing classes implementing the {@link DiscordButton} interface, using the customIds of the buttons as keys
     */
    public Map<String, DiscordButton> discordbuttons = new HashMap<>();

    /**
     * {@link Map} containing classes implementing the {@link DiscordSelectMenu} interface, using the customIds of the menus as keys
     */
    public Map<String, DiscordSelectMenu> selectmenus = new HashMap<>();

    /**
     * Add a button to this bot
     * @param button A class implementing the {@link DiscordButton} interface
     */
    public void addButton(DiscordButton button){
        discordbuttons.put(button.customId(), button);
    }

    /**
     * Remove a button from this bot
     * @param button A class implementing the {@link DiscordButton} interface
     */
    public void removeButton(DiscordButton button){
        discordbuttons.remove(button.customId());
    }

    /**
     * Remove a button from this bot
     * @param customId The customId of the button to be removed
     */
    public void removeButton(String customId){
        discordbuttons.remove(customId);
    }

    /**
     * Add a select menu to this bt
     * @param menu A class implementing the {@link DiscordSelectMenu} interface
     */
    public void addSelectMenu(DiscordSelectMenu menu){
        selectmenus.put(menu.customId(), menu);
    }

    /**
     * Remove a select menu from this bot
     * @param menu A class implementing the {@link DiscordSelectMenu} interface
     */
    public void removeSelectMenu(DiscordSelectMenu menu){
        selectmenus.remove(menu.customId());
    }

    /**
     * Remove a select menu from this bot
     * @param customId The customId of the select menu to be removed
     */
    public void removeSelectMenu(String customId){
        selectmenus.remove(customId);
    }
}
