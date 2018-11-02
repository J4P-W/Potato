package ml.j4pw.potatobot.core;

import ml.j4pw.potatobot.Bot;
import ml.j4pw.potatobot.commands.Command;
import ml.j4pw.potatobot.commands.PingCommand;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.HashMap;

public class CommandClient {
  private static final HashMap<String, Command> commandMap = new HashMap<>();

  static {
    commandMap.put(new PingCommand().name, new PingCommand());
    Bot.LOGGER.info("Loaded Commands");
  }

  public void executeCommand(MessageCreateEvent event) {
    String message = event.getMessageContent().replaceFirst("p!", "");
    Command command = commandMap.get(getCommandString(message));
    if (command != null) {
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
