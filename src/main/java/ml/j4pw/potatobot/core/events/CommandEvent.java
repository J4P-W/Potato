package ml.j4pw.potatobot.core.events;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.Channel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.concurrent.CompletableFuture;

public class CommandEvent {
  private final MessageCreateEvent messageCreateEvent;
  private final String args;

  public CommandEvent(MessageCreateEvent messageCreateEvent, String args) {
    this.messageCreateEvent = messageCreateEvent;
    this.args = args;
  }

  public CompletableFuture<Message> reply(String content) {
    return messageCreateEvent.getChannel().sendMessage(content);
  }

  public CompletableFuture<Message> reply(EmbedBuilder embed) {
    return messageCreateEvent.getChannel().sendMessage(embed);
  }

  public CompletableFuture<Void> edit(String content) {
    return messageCreateEvent.editMessage(content);
  }

  public CompletableFuture<Void> edit(EmbedBuilder embed) {
    return messageCreateEvent.editMessage(embed);
  }

  public CompletableFuture<Void> edit(String content, EmbedBuilder embed) {
    return messageCreateEvent.editMessage(content, embed);
  }

  public CompletableFuture<Void> delete() {
    return messageCreateEvent.deleteMessage();
  }

  public Message getMessage() {
    return messageCreateEvent.getMessage();
  }

  public Channel getChannel() {
    return messageCreateEvent.getChannel();
  }

  public Server getServer() {
    return messageCreateEvent.getServer().isPresent() ? messageCreateEvent.getServer().get() : null;
  }

  public MessageCreateEvent getMessageCreateEvent() {
    return messageCreateEvent;
  }

  public DiscordApi getApi() {
    return messageCreateEvent.getApi();
  }

  public String getArgs() {
    return args;
  }
}
