package michapehlivan.discordbotlib;

import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.Button;
import michapehlivan.discordbotlib.interactions.components.DiscordButton;
import reactor.core.publisher.Mono;

public class TestButton implements DiscordButton {

    @Override
    public String customId() {
        return "testbutton";
    }

    @Override
    public Button getButton() {
        return Button.primary(customId(), "This is a test button");
    }

    @Override
    public Mono<Void> respond(ButtonInteractionEvent event) {
        return event.reply("You pressed a button!!!");
    }
    
}
