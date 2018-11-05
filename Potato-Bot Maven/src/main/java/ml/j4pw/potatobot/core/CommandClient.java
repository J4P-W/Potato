package ml.j4pw.potatobot.core;

import ml.j4pw.potatobot.Bot;
import ml.j4pw.potatobot.commands.Command;
import ml.j4pw.potatobot.commands.admin.ShutdownCommand;
import ml.j4pw.potatobot.commands.utility.PingCommand;
import ml.j4pw.potatobot.core.constants.StringConstants;
import ml.j4pw.potatobot.core.events.CommandEvent;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.HashMap;

public class CommandClient {
  private static final HashMap<String, Command> commandMap = new HashMap<>();

  static {
    commandMap.put(new PingCommand().name, new PingCommand());
    commandMap.put(new ShutdownCommand().name, new ShutdownCommand());
    Bot.LOGGER.info("Loaded Commands");
  }

  public void executeCommand(MessageCreateEvent event) {
    String message = event.getMessageContent().replaceFirst(Bot.runtimeConstants.getPREFIX(), "");
    Command command = commandMap.get(getCommandString(message));
    if (command != null) {
      if (command.isOwnerOnly) {
        if (Bot.runtimeConstants.getOWNERS().contains(event.getMessageAuthor().getId()))
          command.execute(new CommandEvent(event, getArgs(message)));
        else
          event.getChannel().sendMessage(StringConstants.OWNER_ONLY.getStatement());
      } else if (command.guildOnly) {
        if (event.isPrivateMessage() || event.isGroupMessage())
          event.getChannel().sendMessage(StringConstants.GUILD_ONLY.getStatement());
        else
          command.execute(new CommandEvent(event, getArgs(message)));
      } else
        command.execute(new CommandEvent(event, getArgs(message)));
    }
  }

  private String getCommandString(String message) {
    String[] parts = message.trim().split(" ");
    return parts[0];
  }

  private String getArgs(String message) {
    return message.replaceFirst(getCommandString(message) + " ", "");
  }
}
