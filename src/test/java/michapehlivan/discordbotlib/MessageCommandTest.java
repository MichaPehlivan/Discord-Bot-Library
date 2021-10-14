package michapehlivan.discordbotlib;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.object.command.ApplicationCommand;
import discord4j.discordjson.json.ApplicationCommandRequest;
import michapehlivan.discordbotlib.interactions.applicationcommands.MessageCommand;
import reactor.core.publisher.Mono;

public class MessageCommandTest implements MessageCommand {

    @Override
    public String name() {
        return "Message Test";
    }

    @Override
    public void createGlobal(GatewayDiscordClient gateway) {
        long applicationId = gateway.getRestClient().getApplicationId().block();

        ApplicationCommandRequest commandRequest = ApplicationCommandRequest.builder()
            .name(name())
            .type(ApplicationCommand.Type.MESSAGE.getValue())
            .build();

        gateway.getRestClient().getApplicationService()
            .createGlobalApplicationCommand(applicationId, commandRequest)
            .subscribe();
    }

    @Override
    public void createGuild(GatewayDiscordClient gateway, long guildId) {
        long applicationId = gateway.getRestClient().getApplicationId().block();

        ApplicationCommandRequest commandRequest = ApplicationCommandRequest.builder()
            .name(name())
            .type(ApplicationCommand.Type.MESSAGE.getValue())
            .build();

        gateway.getRestClient().getApplicationService()
            .createGuildApplicationCommand(applicationId, guildId, commandRequest)
            .subscribe();
    }

    @Override
    public Mono<Void> execute(MessageInteractionEvent event) {
        return event.reply("reply to " + name());
    }
    
}
