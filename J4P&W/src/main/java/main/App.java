package main;

import java.io.PrintStream;
import java.util.Scanner;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

import DevPaw.api.PaWAPI;
import DevPaw.api.classes.War.War;
import discordListeners.MessageSent;
import discordListeners.UserJoin;


public class App {
	public static DiscordApi api;
	public static MessageSent ms;
	public static String[] administrators = {"358794817595113476", "245470898293833729"};
	
	public static void main(String[] args) {
		PaWAPI.setBufferSize(50);
		PrintStream p = new PrintStream(System.out) {
		    @Override
		    public void println(String x) {
		        super.println("["+System.currentTimeMillis() + "]: " + x);
		    }
		};
		System.setOut(p);
		War w = PaWAPI.getWar("358623");
		System.out.println(w.war.get(0).date);
		Scanner s = new Scanner(System.in);
		System.out.print("Enter bot token: ");
		String token = s.nextLine();
		api = new DiscordApiBuilder().setToken(token).login().join();
		ms = new MessageSent();
		api.addMessageCreateListener((MessageCreateListener) ms);
		api.addServerMemberJoinListener((ServerMemberJoinListener)new UserJoin());
		api.updateActivity("j!help");
		System.out.println(api.createBotInvite());
		System.out.println("Bot initialized");
		s.close();
	}
}
