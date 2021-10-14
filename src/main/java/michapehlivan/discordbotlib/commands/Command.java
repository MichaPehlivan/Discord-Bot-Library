package michapehlivan.discordbotlib.commands;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

/**
 * Interface for a standard command
 * @author Micha Pehlivan
 */
public interface Command {
    /**
     * Execute this command
     * @param event The MessageCreate event of the message calling the command
     * @return a {@link Mono} that, when subscribed to, executes the command
     */
    Mono<Void> execute(MessageCreateEvent event);
}
