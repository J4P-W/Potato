package ml.j4pw.potatobot;

import ml.j4pw.potatobot.listeners.MessageListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.Scanner;
import java.util.logging.Logger;

public class Bot {
  public static final Logger LOGGER = Logger.getLogger(Bot.class.getName());

  public static void main(String[] args) {
    LOGGER.info("Starting Bot");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Token:");
    String token = scanner.nextLine();
    scanner.close();
    DiscordApi discordApi = new DiscordApiBuilder().setToken(token).login().join();
    discordApi.addMessageCreateListener(new MessageListener());
  }
}
