package michapehlivan.discordbotlib;

import java.util.List;

import discord4j.core.event.domain.interaction.SelectMenuInteractionEvent;
import discord4j.core.object.component.SelectMenu;
import michapehlivan.discordbotlib.interactions.components.DiscordSelectMenu;
import reactor.core.publisher.Mono;

public class TestSelectMenu implements DiscordSelectMenu {

    @Override
    public String customId() {
        return "testselectmenu";
    }

    @Override
    public SelectMenu getSelectMenu() {
        SelectMenu menu = SelectMenu.of(customId(), 
            SelectMenu.Option.of("label1", "label1"),
            SelectMenu.Option.of("label2", "label2"),
            SelectMenu.Option.of("label3", "label3")
        ).withMaxValues(1);

        return menu;
    }

    @Override
    public Mono<Void> respond(SelectMenuInteractionEvent event) {
        List<String> lables = event.getValues();
        return event.reply("you selected the following label: " 
            + lables.iterator().next().replaceAll("[\\]\\[]", ""));
    }
    
}
