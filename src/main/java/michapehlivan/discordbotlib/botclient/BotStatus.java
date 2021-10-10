package michapehlivan.discordbotlib.botclient;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.core.object.presence.Status;

public class BotStatus {

    GatewayDiscordClient gateway;
    Status status;
    ClientPresence presence;

    public BotStatus(GatewayDiscordClient gateway){
        this.gateway = gateway;
    }

    //regular Discord status

    //online
    public BotStatus online(){
        status = Status.ONLINE;
        return this;
    }

    //offline
    public BotStatus offline(){
        status = Status.OFFLINE;
        return this;
    }

    //idle
    public BotStatus idle(){
        status = Status.IDLE;
        return this;
    }

    //donotdisturb
    public BotStatus doNotDisturb(){
        status = Status.DO_NOT_DISTURB;
        return this;
    }

    //invisible
    public BotStatus invisible(){
        status = Status.INVISIBLE;
        return this;
    }

    //unknown
    public BotStatus unknown(){
        status = Status.UNKNOWN;
        return this;
    }

    //bot activity

    //no activity
    public void noActivity(){
        presence = ClientPresence.of(status, null);
        gateway.updatePresence(presence).block();
    }

    //playing
    public void playing(String playing){
        presence = ClientPresence.of(status, ClientActivity.playing(playing));
        gateway.updatePresence(presence).block();
    }

    //listening
    public void listening(String listening){
        presence = ClientPresence.of(status, ClientActivity.listening(listening));
        gateway.updatePresence(presence).block();
    }

    //streaming
    public void streaming(String streaming, String streamURL){
        presence = ClientPresence.of(status, ClientActivity.streaming(streaming, streamURL));
        gateway.updatePresence(presence).block();
    }

    //watching 
    public void watching(String watching){
        presence = ClientPresence.of(status, ClientActivity.watching(watching));
        gateway.updatePresence(presence).block();
    }

    //competing
    public void competing(String competing){
        presence = ClientPresence.of(status, ClientActivity.competing(competing));
        gateway.updatePresence(presence).block();
    }

    //getter
    public ClientPresence getPresence(){
        return presence;
    }
    
}
