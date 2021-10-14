package michapehlivan.discordbotlib;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.UserInteractionEvent;
import discord4j.core.object.command.ApplicationCommand;
import discord4j.discordjson.json.ApplicationCommandRequest;
import michapehlivan.discordbotlib.interactions.applicationcommands.UserCommand;
import reactor.core.publisher.Mono;

public class UserCommandTest implements UserCommand {

    @Override
    public String name() {
        return "User Test";
    }

    @Override
    public void createGlobal(GatewayDiscordClient gateway) {
        long applicationId = gateway.getRestClient().getApplicationId().block();

        ApplicationCommandRequest commandRequest = ApplicationCommandRequest.builder()
            .name(name())
            .type(ApplicationCommand.Type.USER.getValue())
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
            .type(ApplicationCommand.Type.USER.getValue())
            .build();

        gateway.getRestClient().getApplicationService()
            .createGuildApplicationCommand(applicationId, guildId, commandRequest)
            .subscribe();
    }

    @Override
    public Mono<Void> execute(UserInteractionEvent event) {
        return event.reply("User command executed...");
    }
    
}
