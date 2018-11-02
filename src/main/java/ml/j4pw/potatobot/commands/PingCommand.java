package ml.j4pw.potatobot.commands;

import ml.j4pw.potatobot.core.CommandEvent;

public class PingCommand extends Command {

  public PingCommand() {
    this.name = "ping";
    this.help = "Returns Bot Latency";
  }

  @Override
  public void execute(CommandEvent event) {
    long start = System.currentTimeMillis();
    event.reply("Pong! Took " + (System.currentTimeMillis() - start) + "ms to respond.");
  }
}
