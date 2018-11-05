package ml.j4pw.potatobot.commands.utility;

import ml.j4pw.potatobot.commands.Command;
import ml.j4pw.potatobot.core.events.CommandEvent;
import org.javacord.api.entity.message.Message;

public class PingCommand extends Command {

  public PingCommand() {
    this.name = "ping";
    this.help = "Returns Bot Latency";
  }

  @Override
  public void execute(CommandEvent event) {
    long start = System.currentTimeMillis();
    Message message = event.reply("pinging").join();
    long end = System.currentTimeMillis();
    message.delete();
    event.reply("Pong! Took " + (end - start) + "ms to respond.");
  }
}
