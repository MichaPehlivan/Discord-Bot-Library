package michapehlivan.discordbotlib.interactions.applicationcommands;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.UserInteractionEvent;
import reactor.core.publisher.Mono;

/**
 * Interface for a User command
 * @author Micha Pehlivan
 */
public interface UserCommand {
    /**
     * The name of this command
     * @return The name of this command
     */
    String name();

    /**
     * Create a new global User command using the code in this method
     * @param gateway The GateWayDiscordClient of the bot using this command
     */
    void createGlobal(GatewayDiscordClient gateway);

    /**
     * Create a new guild specific User command using the code in this method
     * @param gateway The GateWayDiscordClient of the bot using this command
     * @param guildId The id of the guild to add this command to
     */
    void createGuild(GatewayDiscordClient gateway, long guildId);

    /**
     * Execute this command
     * @param event The {@link UserInteractionEvent} of this command call
     * @return A {@link Mono} that, when subscribed to, executes this command
     */
    Mono<Void> execute(UserInteractionEvent event);
}
