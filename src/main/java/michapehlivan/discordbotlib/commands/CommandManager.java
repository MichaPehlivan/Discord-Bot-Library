package michapehlivan.discordbotlib.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    public static Map<String, Command> commands = new HashMap<>();

    public static void addCommand(String name, Command command){
        commands.put(name, command);
    }
}
