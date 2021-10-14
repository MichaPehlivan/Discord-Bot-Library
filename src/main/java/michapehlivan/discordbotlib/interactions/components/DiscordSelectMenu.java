package michapehlivan.discordbotlib.interactions.components;

import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.SelectMenu;
import reactor.core.publisher.Mono;

public interface DiscordSelectMenu {
    String customId();
    SelectMenu getSelectMenu();
    Mono<Void> respond(SelectMenuInteractionEvent event);
}
