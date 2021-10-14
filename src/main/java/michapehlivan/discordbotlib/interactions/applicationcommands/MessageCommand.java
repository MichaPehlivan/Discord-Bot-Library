package michapehlivan.discordbotlib.interactions.applicationcommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import reactor.core.publisher.Mono;

/**
 * Interface for a Message command
 * @author Micha Pehlivan
 */
public interface MessageCommand {
    /**
     * The name of this command
     * @return The name of this command
     */
    String name();

    /**
     * Create a new global Message command using the code in this method
     * @param gateway The GateWayDiscordClient of the bot using this command
     */
    void createGlobal(GatewayDiscordClient gateway);

    /**
     * Create a new guild specific Message command using the code in this method
     * @param gateway The GateWayDiscordClient of the bot using this command
     * @param guildId The id of the guild to add this command to
     */
    void createGuild(GatewayDiscordClient gateway, long guildId);

    /**
     * Execute this command
     * @param event The {@link MessageInteractionEvent} of this command call
     * @return A {@link Mono} that, when subscribed to, executes this command
     */
    Mono<Void> execute(MessageInteractionEvent event);
}
