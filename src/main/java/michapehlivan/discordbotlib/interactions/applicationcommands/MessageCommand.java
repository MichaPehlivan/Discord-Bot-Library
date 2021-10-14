package michapehlivan.discordbotlib.interactions.applicationcommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import reactor.core.publisher.Mono;

public interface MessageCommand {
    String name();
    void createGlobal(GatewayDiscordClient gateway);
    void createGuild(GatewayDiscordClient gateway, long guildId);
    Mono<Void> execute(MessageInteractionEvent event);
}
