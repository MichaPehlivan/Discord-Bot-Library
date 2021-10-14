package michapehlivan.discordbotlib;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommand;
import discord4j.discordjson.json.ApplicationCommandRequest;
import michapehlivan.discordbotlib.interactions.applicationcommands.SlashCommand;
import reactor.core.publisher.Mono;

public class SlashCommandTest implements SlashCommand {

    @Override
    public String name() {
        return "testcommand";
    }

    @Override
    public void createGlobal(GatewayDiscordClient gateway) {
        long applicationId = gateway.getRestClient().getApplicationId().block();

        ApplicationCommandRequest commandRequest = ApplicationCommandRequest.builder()
            .name(name())
            .description("a test command")
            .type(ApplicationCommand.Type.CHAT_INPUT.getValue())
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
            .description("a test command")
            .type(ApplicationCommand.Type.CHAT_INPUT.getValue())
            .build();

        gateway.getRestClient().getApplicationService()
            .createGuildApplicationCommand(applicationId, guildId, commandRequest)
            .subscribe();
    }

    @Override
    public Mono<Void> execute(ChatInputInteractionEvent event) {
        return event.reply("Test!!!");
    }
    
}
