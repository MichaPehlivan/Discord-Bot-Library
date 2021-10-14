package michapehlivan.discordbotlib.interactions.applicationcommands;

import java.util.HashMap;
import java.util.Map;

import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.ApplicationCommandData;

public class ApplicationCommandManager {
    public Map<String, MessageCommand> messagecommands = new HashMap<>();
    public Map<String, SlashCommand> slashcommands = new HashMap<>();
    public Map<String, UserCommand> usercommands = new HashMap<>();

    private GatewayDiscordClient gateway;

    //constructor
    public ApplicationCommandManager(GatewayDiscordClient gateway){
        this.gateway = gateway;
    }

    //add global message command
    public void addGlobalMessageCommand(MessageCommand command){
        messagecommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    //add guild message command
    public void addGuildMessageCommand(MessageCommand command, long guildId){
        messagecommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    //add global slash command
    public void addGlobalSlashCommand(SlashCommand command){
        slashcommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    //add guild slash command
    public void addGuildSlashCommand(SlashCommand command, long guildId){
        slashcommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    //add global user command
    public void addGlobalUserCommand(UserCommand command){
        usercommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    //add guild user command
    public void addGuildUserCommand(UserCommand command, long guildId){
        usercommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    //remove global command
    public void removeGlobalCommand(String commandName){
        long applicationId = gateway.getRestClient().getApplicationId().block();

        Map<String, ApplicationCommandData> discordCommands = gateway.getRestClient()    
            .getApplicationService()    
            .getGlobalApplicationCommands(applicationId)    
            .collectMap(ApplicationCommandData::name)    
            .block();

        long commandId = Long.parseLong(discordCommands.get(commandName).id());

        gateway.getRestClient().getApplicationService()    
            .deleteGlobalApplicationCommand(applicationId, commandId)    
            .subscribe();
    }

    //remove guild command
    public void removeGuildCommand(String commandName, long guildId){
        long applicationId = gateway.getRestClient().getApplicationId().block();

        Map<String, ApplicationCommandData> discordCommands = gateway.getRestClient()    
            .getApplicationService()    
            .getGuildApplicationCommands(applicationId, guildId)  
            .collectMap(ApplicationCommandData::name)    
            .block();

        long commandId = Long.parseLong(discordCommands.get(commandName).id());

        gateway.getRestClient().getApplicationService()    
            .deleteGlobalApplicationCommand(applicationId, commandId)    
            .subscribe();
    }
}
