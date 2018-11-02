package ml.j4pw.potatobot;

import ml.j4pw.potatobot.core.constants.RuntimeConstants;
import ml.j4pw.potatobot.listeners.MessageListener;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.util.Scanner;
import java.util.logging.Logger;

public class Bot {
  public static final Logger LOGGER = Logger.getLogger(Bot.class.getName());
  public static RuntimeConstants runtimeConstants = new RuntimeConstants();

  public static void main(String[] args) {
    LOGGER.info("Starting Bot");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Token:");
    String token = scanner.nextLine();
    System.out.println("Enter Prefix:");
    String prefix = scanner.nextLine();
    scanner.close();
    runtimeConstants.setPREFIX(prefix);
    runtimeConstants.setTOKEN(token);
    DiscordApi discordApi = new DiscordApiBuilder().setToken(runtimeConstants.getTOKEN()).login().join();
    discordApi.addMessageCreateListener(new MessageListener());
  }
}
