package michapehlivan.discordbotlib.botclient;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.core.object.presence.Status;

/**
 * Class for managing the status and activity of a Discord bot
 * @author Micha Pehlivan
 */
public class BotStatus {

    GatewayDiscordClient gateway;
    Status status;
    ClientPresence presence;

    /**
     * Constructor for {@link BotStatus}
     * @param gateway The {@link GatewayDiscordClient} of to which this object belongs
     */
    public BotStatus(GatewayDiscordClient gateway){
        this.gateway = gateway;
    }

    /**
     * Sets the status of this Discord bot to online
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus online(){
        status = Status.ONLINE;
        return this;
    }

    /**
     * Sets the status of this Discord bot to offline
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus offline(){
        status = Status.OFFLINE;
        return this;
    }

    /**
     * Sets the status of this Discord bot to idle
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus idle(){
        status = Status.IDLE;
        return this;
    }

    /**
     * Sets the status of this Discord bot to donotdisturb
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus doNotDisturb(){
        status = Status.DO_NOT_DISTURB;
        return this;
    }

    /**
     * Sets the status of this Discord bot to invisible
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus invisible(){
        status = Status.INVISIBLE;
        return this;
    }

    /**
     * Sets the status of this Discord bot to unknown
     * @return The BotStatus object of this Discord bot
     */
    public BotStatus unknown(){
        status = Status.UNKNOWN;
        return this;
    }

    /**
     * Sets this Discord bot to have no activity
     */
    public void noActivity(){
        presence = ClientPresence.of(status, null);
        gateway.updatePresence(presence).block();
    }

    /**
     * Sets the activity of this bot to playing
     * @param playing The activity name
     */
    public void playing(String playing){
        presence = ClientPresence.of(status, ClientActivity.playing(playing));
        gateway.updatePresence(presence).block();
    }

    /**
     * Sets the activity of this bot to listening
     * @param listening The activity name
     */
    public void listening(String listening){
        presence = ClientPresence.of(status, ClientActivity.listening(listening));
        gateway.updatePresence(presence).block();
    }

    /**
     * Sets the activity of this bot to streaming
     * @param streaming The activity name
     * @param streamURL Url to the stream
     */
    public void streaming(String streaming, String streamURL){
        presence = ClientPresence.of(status, ClientActivity.streaming(streaming, streamURL));
        gateway.updatePresence(presence).block();
    }

    /**
     * Sets the activity of this bot to Watching
     * @param watching The activity name
     */
    public void watching(String watching){
        presence = ClientPresence.of(status, ClientActivity.watching(watching));
        gateway.updatePresence(presence).block();
    }

    /**
     * Sets the activity of this bot to competing
     * @param competing The activity name
     */
    public void competing(String competing){
        presence = ClientPresence.of(status, ClientActivity.competing(competing));
        gateway.updatePresence(presence).block();
    }

    /**
     * Get the current presence
     * @return The presence of the bot associated with this object
     */
    public ClientPresence getPresence(){
        return presence;
    }
    
}
