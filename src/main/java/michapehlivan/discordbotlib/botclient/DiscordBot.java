package michapehlivan.discordbotlib.botclient;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.event.domain.interaction.UserInteractionEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import michapehlivan.discordbotlib.commands.Command;
import michapehlivan.discordbotlib.commands.CommandManager;
import michapehlivan.discordbotlib.interactions.applicationcommands.ApplicationCommandManager;
import michapehlivan.discordbotlib.util.BotConsole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DiscordBot {
    
    private GatewayDiscordClient gateway;
    private BotStatus status;
    private String commandPrefix;
    private ApplicationCommandManager applicationCommandManager;

    public DiscordBot(String token){
        BotConsole console = new BotConsole("bot console", 800, 500);
        System.setOut(console.getPrintStream());

        gateway = DiscordClient.create(token).login().block();
        status = new BotStatus(gateway);
        commandPrefix = ".";
        applicationCommandManager = new ApplicationCommandManager(gateway);
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

        gateway.on(MessageInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.messagecommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.messagecommands.get(name).execute(event)))
            .subscribe();     

        gateway.on(ChatInputInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.slashcommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.slashcommands.get(name).execute(event)))
            .subscribe();  

        gateway.on(UserInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.usercommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.usercommands.get(name).execute(event)))
            .subscribe();  
    }

    //getters and setters
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

    public ApplicationCommandManager getApplicationCommandManager(){
        return applicationCommandManager;
    }
}
