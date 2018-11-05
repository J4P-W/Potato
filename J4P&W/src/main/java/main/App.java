package main;

import java.util.Scanner;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import discordListeners.MessageSent;
import discordListeners.UserJoin;


public class App {
	public static DiscordApi api;
	public static MessageSent ms;
	
	public static String[] administrators = {"358794817595113476", "245470898293833729"};
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("Enter bot token: ");
		String token = s.nextLine();
		api = new DiscordApiBuilder().setToken(token).login().join();
		ms = new MessageSent();
		api.addMessageCreateListener((MessageCreateListener) ms);
		api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
		api.updateActivity("----");
		System.out.println(api.createBotInvite());
		System.out.println("Bot initialized");
		s.close();
	}
}
