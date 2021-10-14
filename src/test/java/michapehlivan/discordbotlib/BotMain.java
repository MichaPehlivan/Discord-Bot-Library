package michapehlivan.discordbotlib;

import java.security.KeyException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonElement;

import discord4j.core.object.component.ActionRow;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;
import michapehlivan.discordbotlib.botclient.DiscordBot;
import michapehlivan.discordbotlib.util.json.JsonReader;
import michapehlivan.discordbotlib.util.json.JsonWriter;

public class BotMain {
    public static void main(String[] args) {

        DiscordBot bot = new DiscordBot("token");
        bot.setPrefix("-");
        bot.status().online().playing(bot.getPrefix() + "help");
        
        JsonWriter writer = new JsonWriter("src/test/java/michapehlivan/discordbotlib/Test.json");
        JsonReader reader = new JsonReader("src/test/java/michapehlivan/discordbotlib/Test.json");
        List<String> testList = Arrays.asList("entry1", "entry2");
        
        try {
            writer.clearJson();
            writer.addObject("Key", "Value");
            writer.addArray("Array", testList);
            String value = reader.getValueAsString("Key");
            JsonElement array = reader.getValueAsJsonElement("Array");
            System.out.println(value);
            System.out.println(array);
        } catch (KeyException e) {
            e.printStackTrace();
        }
        
        EmbedCreateSpec embed = EmbedCreateSpec.builder()
            .title("test")
            .footer("footer of test", null)
            .addField("test", "test", false)
            .build();

        bot.addCommand("test", event -> 
            event.getMessage().getChannel().block().createMessage(embed).then());

        TestButton button = new TestButton();
        TestSelectMenu menu = new TestSelectMenu();
        bot.getComponentManager().addButton(button);
        bot.getComponentManager().addSelectMenu(menu);

        bot.addCommand("button", event ->
            event.getMessage().getChannel().block().createMessage(MessageCreateSpec.builder()
                .content("here is your button:")
                .addComponent(ActionRow.of(button.getButton()))
                .build()).then());

        bot.addCommand("select", event ->
            event.getMessage().getChannel().block().createMessage(MessageCreateSpec.builder()
                .content("here is your button:")
                .addComponent(ActionRow.of(menu.getSelectMenu()))
                .build()).then());

        bot.getApplicationCommandManager().addGuildMessageCommand(new MessageCommandTest(), 699539873773780992L);
        bot.getApplicationCommandManager().addGuildSlashCommand(new SlashCommandTest(), 699539873773780992L);
        bot.getApplicationCommandManager().addGuildUserCommand(new UserCommandTest(), 699539873773780992L);

        bot.getGateway().onDisconnect().block();
    }
}
