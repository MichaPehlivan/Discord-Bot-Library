# Discord-Bot-Library
Java library that acts as an abstraction of [Discord4J](https://github.com/Discord4J/Discord4J), allowing for quick development of Discord bots

The goal for this project was to greatly simplify the development process of basic and intermidiate level Discord bots using Discord4J, as this is
currently a somewhat cumbersome process, requiring you to set up systems for commands, interactions, etc. yourself before you can actually start development.
This library takes care of most of that setup in the background, leaving you to do the actual development of the bot. Please refer to the [Discord4J docs](https://docs.discord4j.com/) if you if you need help with Discord4J.

It should be sufficient for the most common 
usecases. For more advanced projects however, the main Discord4J api would be a better choice, as you are more directly communicating with Discord, and
this library leaves out a lot of systems present in Discord4J.

**please note: it is highly recommended to have some experience with Discord4J when using this library**


## Features of the libary

- Managing classic chat-based commands

- Full support for Message-, Slash- and Usercommands

- Easy to use system for buttons and select menus 

- A custom console window to output bot information to

- Classes for easily reading and writing to and from Json files

- Easily updating the presence of the Discord bot

## Using the library

```xml
<dependencies>
  <dependency>
    <groupId>io.github.MichaPehlivan</groupId>
    <artifactId>botlibrary</artifactId>
    <version>1.3</version>
  </dependency>
</dependencies>
```
- - - - 

## Tutorial

When using the library, start by creating a `DiscordBot` object:
```java
DiscordBot bot = new DiscordBot("token");
```
Here "token" is the token of your Discord bot, which you can get through the Discord developer portal. You can put this in the main loop of your code, and end the loop with `bot.getGateway().onDisconnect().block();` to keep the bot running until you close the program.

From here you can set the bot's command prefix with:
```java
bot.setPrefix("prefix");
```
And the bots presence with:
```java
bot.status().online().playing("playing status")
```
The library supports the `online`, `offline`, `idle`, `donotdisturb`, `invisible` and `unknown` status,
and the `playing`, `listening`, `streaming`, `watching` and `competing` activities.

To add a classic chat based command, you can use the `addCommand(String name, Command command)` method. Here `name` is the name of the command, which you will use to call the command in Discord, and `command` either a class or a lambda statement implementing the `Command` interface. For example: 
```java
bot.addCommand("ping", event -> 
  event.getMessage().getChannel().block().createMessage("Pong!").then());
``` 
This adds a command that responds with "Pong!" to any message starting with your prefix and "ping".

### Interactions
The library fully supports application commands(Message, Slash and User commands), and components(Buttons and Select menus). To use any interaction, you need to implement the appropriate class. These classes are `MessageCommand`, `SlashCommand`, `UserCommand`, `DiscordButton` and `DiscordSelectMenu`. 

An application command can be added to all Discord servers by using:
```java
bot.getApplicationCommandManager().addGlobalMessageCommand(new MessageCommandExample());
```
Where `MessageCommandExample` is a class implementing the `MessageCommand` interface.

You can also add a command to a specific Discord server by using:
```java
bot.getApplicationCommandManager().addGuildMessageCommand(new MessageCommandExample(), 699536873778780392L);
```
Where the long is the id of the Discord server you want to add the command to.

Using Buttons and Select menus is even easyer. You can simply use a `MessageCreateSpec` and attach the component to it:
```java
MessageCreateSpec.builder()
  .addComponent(ActionRow.of(new ExampleButton().getButton()))
  .build();
``` 
Where `ExampleButton` is a class implementing the `DiscordButton` interface.

An example bot can be found in the `test` folder, and more detailed information on classes and methods can be found in the javadoc.
