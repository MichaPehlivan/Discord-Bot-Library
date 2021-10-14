package michapehlivan.discordbotlib.interactions.applicationcommands;

import java.util.HashMap;
import java.util.Map;

import discord4j.core.GatewayDiscordClient;
import discord4j.discordjson.json.ApplicationCommandData;

/**
 * Class for managing all application commands 
 * @author Micha Pehlivan
 */
public class ApplicationCommandManager {

    public Map<String, MessageCommand> messagecommands = new HashMap<>();
    public Map<String, SlashCommand> slashcommands = new HashMap<>();
    public Map<String, UserCommand> usercommands = new HashMap<>();

    private GatewayDiscordClient gateway;

    public ApplicationCommandManager(GatewayDiscordClient gateway){
        this.gateway = gateway;
    }

    /**
     * Add a global Message command to this bot
     * @param command a class implementing the {@link MessageCommand} interface
     * @apiNote It can take up to 1 hour for a global command to be added to all servers
     */
    public void addGlobalMessageCommand(MessageCommand command){
        messagecommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    /**
     * Add a guild specific Message command to this bot
     * @param command A class implementing the {@link MessageCommand} interface 
     * @param guildId The id of the guild to add this command to
     */
    public void addGuildMessageCommand(MessageCommand command, long guildId){
        messagecommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    /**
     * Add a global Slash command to this bot
     * @param command A class implementing the {@link SlashCommand} interface
     * @apiNote It can take up to 1 hour for a global command to be added to all servers
     */
    public void addGlobalSlashCommand(SlashCommand command){
        slashcommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    /**
     * Add a guild specific Slash command to this bot
     * @param command A class implementing the {@link SlashCommand} interface
     * @param guildId The id of the guild to add this command to
     */
    public void addGuildSlashCommand(SlashCommand command, long guildId){
        slashcommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    /**
     * Add a global User command to this bot
     * @param command A class implementing the {@link UserCommand} interface
     * @apiNote It can take up to 1 hour for a global command to be added to all servers
     */
    public void addGlobalUserCommand(UserCommand command){
        usercommands.put(command.name(), command);
        command.createGlobal(gateway);
    }

    /**
     * Add a guild specific User Command to this bot
     * @param command A class implementing the {@link UserCommand} interface
     * @param guildId The id of the guild to add this command to
     */
    public void addGuildUserCommand(UserCommand command, long guildId){
        usercommands.put(command.name(), command);
        command.createGuild(gateway, guildId);
    }

    /**
     * Remove a global application command
     * @param commandName The name of the command to be removed
     */
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

    /**
     * Remove a guild specific application command
     * @param commandName The name of the command to be removed
     * @param guildId The id of the guild to remove this command from
     */
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
