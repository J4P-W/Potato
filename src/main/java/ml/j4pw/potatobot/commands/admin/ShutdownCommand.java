package ml.j4pw.potatobot.commands.admin;

import ml.j4pw.potatobot.Bot;
import ml.j4pw.potatobot.commands.Command;
import ml.j4pw.potatobot.core.events.CommandEvent;

public class ShutdownCommand extends Command {

  public ShutdownCommand() {
    this.name = "shutdown";
    this.isOwnerOnly = true;
    this.help = "Evaluate Java Commands";
    this.arguments = new String[]{"statement"};
  }

  @Override
  public void execute(CommandEvent event) {
    event.reply("Shutting Down").join();
    Bot.LOGGER.info("Shutting Down");
    event.getApi().disconnect();
    System.exit(1);
  }
}
