package michapehlivan.discordbotlib.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for managing all standard commands
 * @author Micha Pehlivan
 */
public class CommandManager {

    /**
     * {@link Map} Containing classes implementing the {@link Command} interface, using the command names as keys
     */
    public static Map<String, Command> commands = new HashMap<>();

    /**
     * Add a Standard command to this bot
     * @param name The name of the command
     * @param command A class implementing the {@link Command} interface
     */
    public static void addCommand(String name, Command command){
        commands.put(name, command);
    }
}
