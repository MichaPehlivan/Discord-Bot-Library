package michapehlivan.discordbotlib.interactions.components;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.Button;
import reactor.core.publisher.Mono;

/**
 * Interface for a Discord button
 * @author Micha Pehlivan
 */
public interface DiscordButton {
    /**
     * The customId of this button, acting like a name
     * @return The customId of this button
     */
    String customId();

    /**
     * Returns the {@link Button} object created from the code in this method
     * @return The button created through this method
     */
    Button getButton();

    /**
     * Respond to this button being clicked
     * @param event The {@link ButtonInteractionEvent} fired when this button is clicked
     * @return A {@link Mono} that, when subscribed to, responds to this button being clicked
     */
    Mono<Void> respond(ButtonInteractionEvent event);
}
