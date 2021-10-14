package michapehlivan.discordbotlib.interactions.components;

import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.SelectMenu;
import reactor.core.publisher.Mono;

/**
 * Interface for a Discord select menu
 * @author Micha Pehlivan
 */
public interface DiscordSelectMenu {
    /**
     * The customId of this select menu, acting like a name
     * @return The customId of this select menu
     */
    String customId();

    /**
     * Returns the {@link SelectMenu} object created from the code in this method
     * @return The select menu created through this method
     */
    SelectMenu getSelectMenu();

    /**
     * Respond to this select menu being used
     * @param event The {@link SelectMenuInteractionEvent} fired when this select menu is used
     * @return A {@link Mono} that, when subscribed to, responds to this select menu being used
     */
    Mono<Void> respond(SelectMenuInteractionEvent event);
}
