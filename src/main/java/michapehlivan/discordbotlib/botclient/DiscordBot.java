package michapehlivan.discordbotlib.botclient;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.MessageInteractionEvent;
import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.event.domain.interaction.UserInteractionEvent;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import michapehlivan.discordbotlib.commands.Command;
import michapehlivan.discordbotlib.commands.CommandManager;
import michapehlivan.discordbotlib.interactions.applicationcommands.ApplicationCommandManager;
import michapehlivan.discordbotlib.interactions.components.ComponentManager;
import michapehlivan.discordbotlib.util.BotConsole;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Main Class for managing all systems of a Discord bot
 * @author Micha Pehlivan
 */
public class DiscordBot {
    
    private GatewayDiscordClient gateway;
    private BotStatus status;
    private String commandPrefix;

    private ApplicationCommandManager applicationCommandManager;
    private ComponentManager componentManager;

    public DiscordBot(String token){
        BotConsole console = new BotConsole("bot console", 800, 500);
        System.setOut(console.getPrintStream());

        gateway = DiscordClient.create(token).login().block();
        status = new BotStatus(gateway);
        commandPrefix = ".";
        applicationCommandManager = new ApplicationCommandManager(gateway);
        componentManager = new ComponentManager();
        eventSetUp();
    }

    private void eventSetUp(){

        //Ready event
        gateway.on(ReadyEvent.class)
            .subscribe(event -> System.out.println("bot ready"));

        //MessageCreate event
        gateway.on(MessageCreateEvent.class)
            .filter(event -> !event.getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getMessage().getContent())
                .flatMap(content -> Flux.fromIterable(CommandManager.commands.entrySet())
                    .filter(entry -> content.startsWith(commandPrefix + entry.getKey()))
                    .flatMap(entry -> entry.getValue().execute(event))
                    .next()))
            .subscribe();

        //Message command
        gateway.on(MessageInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.messagecommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.messagecommands.get(name).execute(event)))
            .subscribe();     

        //Slash command
        gateway.on(ChatInputInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.slashcommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.slashcommands.get(name).execute(event)))
            .subscribe();  

        //User command
        gateway.on(UserInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCommandName())
                .filter(name -> applicationCommandManager.usercommands.containsKey(name))
                .flatMap(name -> applicationCommandManager.usercommands.get(name).execute(event)))
            .subscribe();
            
        //Button interaction event
        gateway.on(ButtonInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCustomId())
                .filter(id -> componentManager.discordbuttons.containsKey(id))
                .flatMap(id -> componentManager.discordbuttons.get(id).respond(event)))
            .subscribe();

        //SelectMenu interaction event
        gateway.on(SelectMenuInteractionEvent.class)
            .filter(event -> !event.getInteraction().getMember().get().isBot())
            .flatMap(event -> Mono.just(event.getCustomId())
                .filter(id -> componentManager.selectmenus.containsKey(id))
                .flatMap(id -> componentManager.selectmenus.get(id).respond(event)))
            .subscribe();
    }

    //getters and setters

    /**
     * Get the GateWayDiscordClient of this bot
     * @return The GatewayDiscordClient of this bot
     */
    public GatewayDiscordClient getGateway(){
        return gateway;
    }

    /**
     * Get the status of this bot
     * @return The status of this bot
     */
    public BotStatus status(){
        return status;
    }

    /**
     * Get this bot's command prefix
     * @return The command prefix of this bot
     */
    public String getPrefix(){
        return commandPrefix;
    }

    /**
     * Set the command prefix of this bot, if unchanged, it is set to "-" by default
     * @param prefix The new prefix
     */
    public void setPrefix(String prefix){
        commandPrefix = prefix;
    }

    /**
     * Add a standard command to this bot
     * @param name The name of the command, this is the name used for calling the command
     * @param command A class implementing the {@link Command} interface
     */
    public void addCommand(String name, Command command){
        CommandManager.addCommand(name, command);
    }

    /**
     * Get the ApplicationManager of this bot
     * @return The ApplicationManager of this bot, used to manage all application commands
     */
    public ApplicationCommandManager getApplicationCommandManager(){
        return applicationCommandManager;
    }

    /**
     * Get the ComponentManager of this bot
     * @return The ComponentManager of this bot, used to manage all buttons and select menus
     */
    public ComponentManager getComponentManager(){
        return componentManager;
    }
}
