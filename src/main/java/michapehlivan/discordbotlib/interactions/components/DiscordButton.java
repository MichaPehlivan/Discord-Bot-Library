package michapehlivan.discordbotlib.interactions.components;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.Button;
import reactor.core.publisher.Mono;

public interface DiscordButton {
    String customId();
    Button getButton();
    Mono<Void> respond(ButtonInteractionEvent event);
}
