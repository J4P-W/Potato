package ml.j4pw.potatobot.listeners;

import ml.j4pw.potatobot.Bot;
import ml.j4pw.potatobot.core.CommandClient;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MessageListener implements MessageCreateListener {
  private CommandClient commandClient = new CommandClient();

  @Override
  public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
    if (messageCreateEvent.getMessageContent().startsWith(Bot.runtimeConstants.getPREFIX())) {
      commandClient.executeCommand(messageCreateEvent);
    }
  }
}
