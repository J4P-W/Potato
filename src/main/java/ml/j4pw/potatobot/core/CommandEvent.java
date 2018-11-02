package ml.j4pw.potatobot.core;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.File;

public class CommandEvent {

  private final MessageCreateEvent event;
  private String args;

  CommandEvent(MessageCreateEvent event, String args) {
    this.event = event;
    this.args = args;
  }

  public String getArgs() {
    return args;
  }

  public void reply(String message) {
    event.getChannel().sendMessage(message);
  }

  public void reply(EmbedBuilder embed) {
    event.getChannel().sendMessage(embed);
  }

  public void reply(String message, EmbedBuilder embed) {
    event.getChannel().sendMessage(message, embed);
  }

  public void reply(File... files) {
    event.getChannel().sendMessage(files);
  }

  public MessageAuthor getAuthor() {
    return event.getMessageAuthor();
  }

  public TextChannel getChannel() {
    return event.getChannel();
  }

  public Server getGuild() {
    return event.getServer().isPresent() ? event.getServer().get() : null;
  }

  public long getMessageId() {
    return event.getMessageId();
  }

  public Message getMessage() {
    return event.getMessage();
  }

  public DiscordApi getApi() {
    return event.getApi();
  }
}
