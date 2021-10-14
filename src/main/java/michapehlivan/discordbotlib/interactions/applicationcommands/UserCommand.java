package michapehlivan.discordbotlib.interactions.applicationcommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.UserInteractionEvent;
import reactor.core.publisher.Mono;

public interface UserCommand {
    String name();
    void createGlobal(GatewayDiscordClient gateway);
    void createGuild(GatewayDiscordClient gateway, long guildId);
    Mono<Void> execute(UserInteractionEvent event);
}
