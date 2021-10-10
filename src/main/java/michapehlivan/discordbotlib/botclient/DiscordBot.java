package michapehlivan.discordbotlib.botclient;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import michapehlivan.discordbotlib.commands.Command;
import michapehlivan.discordbotlib.commands.CommandManager;
import michapehlivan.discordbotlib.util.BotConsole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DiscordBot {
    
    private GatewayDiscordClient gateway;
    private BotStatus status;
    private String commandPrefix;
    private static volatile boolean IN_EVENT = false;

    public DiscordBot(String token){
        BotConsole console = new BotConsole("bot console", 800, 500);
        System.setOut(console.getPrintStream());

        gateway = DiscordClient.create(token).login().block();
        status = new BotStatus(gateway);
        commandPrefix = ".";
        eventSetUp();
    }

    public void eventSetUp(){

        gateway.on(ReadyEvent.class)
            .subscribe(event -> System.out.println("bot ready"));

        gateway.on(MessageCreateEvent.class)
            .filter(event -> !event.getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getMessage().getContent())
                .flatMap(content -> Flux.fromIterable(CommandManager.commands.entrySet())
                    .filter(entry -> content.startsWith(commandPrefix + entry.getKey()))
                    .flatMap(entry -> entry.getValue().execute(event))
                    .next()))
            .subscribe();
    }

    public GatewayDiscordClient getGateway(){
        return gateway;
    }

    public BotStatus status(){
        return status;
    }

    public String getPrefix(){
        return commandPrefix;
    }

    public void setPrefix(String prefix){
        commandPrefix = prefix;
    }

    public void addCommand(String name, Command command){
        CommandManager.addCommand(name, command);
    }

    public static boolean getInEvent() {
		return IN_EVENT;
	}
}
