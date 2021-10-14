# Discord-Bot-Library
Java library that acts as an abstraction of https://github.com/Discord4J/Discord4J, allowing for quick development of Discord bots

The goal for this project was to greatly simplify the development process of basic and intermidiate level Discord bots using Discord4j, as this is
currently a somewhat cumbersome process, requiring you to set up systems for commands, interactions, etc. yourself before you can actually start development.
This library takes care of most of that setup in the background, leaving you to do the actual development of the bot. 

It should be sufficient for the most common 
usecases. For more advanced projects however, the main Discord4j api would be a better choice, as you are more directly communicating with Discord, and
this library leaves out a lot of systems present in Discord4J.


Features of the libary include:

- Managing classic chat-based commands

- Full support for Message-, Slash- and Usercommands

- Easy to use system for buttons and select menus 

- A custom console window to output bot information to

- Classes for easily reading and writing to and from Json files

- Easily updating the presence of the Discord bot
